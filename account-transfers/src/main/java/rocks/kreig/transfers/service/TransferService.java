package rocks.kreig.transfers.service;

import net.jodah.failsafe.internal.util.Assert;
import rocks.kreig.transfers.repository.AccountRepository;
import rocks.kreig.transfers.repository.TransferRepository;
import rocks.kreig.transfers.repository.entity.Account;
import rocks.kreig.transfers.resource.Status;
import rocks.kreig.transfers.resource.Transfer;
import rocks.kreig.transfers.resource.TransferStatus;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Optional;

import static rocks.kreig.transfers.repository.entity.Transfer.aTransfer;

/**
 * Service for transfer operations
 */
@Named
@RequestScoped
public class TransferService {
    final TransferRepository transferRepository;
    final AccountRepository accountRepository;

    @Inject
    public TransferService(final TransferRepository transferRepository, final AccountRepository accountRepository) {
        this.transferRepository = transferRepository;
        this.accountRepository = accountRepository;
    }

    @Transactional
    public Optional<Transfer> transfer(final Transfer transfer) {
        validateTransferResource(transfer);

        final rocks.kreig.transfers.repository.entity.Transfer transferEntity = transform(transfer);
        final Optional<rocks.kreig.transfers.repository.entity.Transfer> createdTransfer = transferRepository.create(transferEntity);

        return createdTransfer.map(this::operate).flatMap(this::transform);
    }

    private rocks.kreig.transfers.repository.entity.Transfer operate(final rocks.kreig.transfers.repository.entity.Transfer transfer) {
        transfer.setStatus(Status.IN_PROGRESS);

        debit(transfer.getOriginAccount(), transfer.getAmount());
        credit(transfer.getDestinationAccount(), transfer.getAmount());

        transfer.setStatus(Status.COMPLETED);
        return transfer;
    }

    private void credit(final Account account, final BigDecimal amount) {
        account.setBalance(account.getBalance().add(amount));
    }

    private void debit(final Account account, final BigDecimal amount) {
        account.setBalance(account.getBalance().subtract(amount));
    }

    // TODO delegate transformers into collaborators
    private Optional<Transfer> transform(final rocks.kreig.transfers.repository.entity.Transfer transfer) {
        final rocks.kreig.transfers.resource.Account origin = new rocks.kreig.transfers.resource.Account(transfer.getOriginAccount().getId(),
                                                                                                         transfer.getOriginAccount().getName(),
                                                                                                         transfer.getOriginAccount().getNumber(),
                                                                                                         transfer.getOriginAccount().getBalance());
        final rocks.kreig.transfers.resource.Account destination = new rocks.kreig.transfers.resource.Account(transfer.getDestinationAccount().getId(),
                                                                                                              transfer.getDestinationAccount().getName(),
                                                                                                              transfer.getDestinationAccount().getNumber(),
                                                                                                              transfer.getDestinationAccount().getBalance());
        final TransferStatus transferStatus = new TransferStatus(transfer.getStatus(), transfer.getStatusReason());

        return Optional.of(new Transfer(transfer.getId(), origin, destination, transfer.getAmount(), transferStatus));

    }

    private rocks.kreig.transfers.repository.entity.Transfer transform(final Transfer transfer) {
        final Optional<rocks.kreig.transfers.repository.entity.Account> origin = accountRepository.findByNumber(transfer.getOrigin().getNumber());
        final Optional<rocks.kreig.transfers.repository.entity.Account> destination = accountRepository.findByNumber(transfer.getDestination().getNumber());

        Assert.isTrue(origin.isPresent(), "Origin account does not exists for this number");
        Assert.isTrue(destination.isPresent(), "Destination account does not exists for this number");

        validateFunds(origin.get(), transfer.getAmount());

        return aTransfer().withOriginAccount(origin.get()).withDestinationAccoutn(destination.get()).withAmount(transfer.getAmount()).withStatus(Status.INITIATED).build();

    }

    private void validateFunds(final Account account, final BigDecimal amount) {
        Assert.state(account.getBalance().subtract(amount).compareTo(BigDecimal.ZERO) >= 0, "Insufficient funds in origination account.");
    }

    private void validateTransferResource(final Transfer transfer) {
        // This can be accomplished with JSR-305 too, but will keep it as assertions as for now
        Assert.notNull(transfer, "Transfer should not be null.");

        Assert.notNull(transfer.getOrigin(), "Origin account should not be null.");
        Assert.notNull(transfer.getDestination(), "Destination account should not be null.");

        Assert.notNull(transfer.getAmount(), "Amount should not be null.");
    }
}
