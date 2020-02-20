package rocks.kreig.transfers.service;

import net.jodah.failsafe.internal.util.Assert;
import rocks.kreig.transfers.repository.AccountRepository;
import rocks.kreig.transfers.repository.TransferRepository;
import rocks.kreig.transfers.repository.entity.Account;
import rocks.kreig.transfers.resource.Status;
import rocks.kreig.transfers.resource.Transfer;
import rocks.kreig.transfers.resource.TransferStatus;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;
import java.util.Optional;

import static rocks.kreig.transfers.repository.entity.Transfer.aTransfer;

@Named
@ApplicationScoped
public class TransferService {
    final TransferRepository transferRepository;
    final AccountRepository accountRepository;

    @Inject
    public TransferService(@Named("h2TransferRepository") final TransferRepository transferRepository, final AccountRepository accountRepository) {
        this.transferRepository = transferRepository;
        this.accountRepository = accountRepository;
    }

    public Optional<TransferStatus> status(final long id) {
        // final Optional<rocks.kreig.transfers.repository.entity.Transfer> transfer = transferRepository.findById(id);

        //  return transfer.map(Transfer::getStatus);
        throw new UnsupportedOperationException("Not implemented");
    }

    public Optional<Transfer> transfer(final Transfer transfer) {
        validateTransferResource(transfer);

        final rocks.kreig.transfers.repository.entity.Transfer transferEntity = transform(transfer);
        final Optional<rocks.kreig.transfers.repository.entity.Transfer> createdTransfer = transferRepository.create(transferEntity);

        if (createdTransfer.isPresent()) {
            return transform(createdTransfer.get());
        }
        return Optional.empty();
    }

    // TODO delegate transformers into collaborators
    private Optional<Transfer> transform(final rocks.kreig.transfers.repository.entity.Transfer transfer) {
        final rocks.kreig.transfers.resource.Account origin = new rocks.kreig.transfers.resource.Account(transfer.getOriginAccount().getId(),
                                                                                                         transfer.getOriginAccount().getName(),
                                                                                                         transfer.getOriginAccount().getNumber(),
                                                                                                         transfer.getOriginAccount().getBalance());
        final rocks.kreig.transfers.resource.Account destination = new rocks.kreig.transfers.resource.Account(transfer.getDestinationAccoutn().getId(),
                                                                                                              transfer.getDestinationAccoutn().getName(),
                                                                                                              transfer.getDestinationAccoutn().getNumber(),
                                                                                                              transfer.getDestinationAccoutn().getBalance());
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
        Assert.state(account.getBalance().subtract(amount).compareTo(BigDecimal.ZERO) < 0, "Insufficient funds in origination account.");
    }

    private void validateTransferResource(final Transfer transfer) {
        // This can be accomplished with JSR-305 too, but will keep it as assertions as for now
        Assert.notNull(transfer, "Transfer should not be null.");

        Assert.notNull(transfer.getOrigin(), "Origin account should not be null.");
        Assert.notNull(transfer.getDestination(), "Destination account should not be null.");

        Assert.notNull(transfer.getAmount(), "Amount should not be null.");
    }
}
