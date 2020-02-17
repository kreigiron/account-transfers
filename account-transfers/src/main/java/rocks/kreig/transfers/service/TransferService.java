package rocks.kreig.transfers.service;

import rocks.kreig.transfers.repository.TransferRepository;
import rocks.kreig.transfers.resource.Transfer;
import rocks.kreig.transfers.resource.TransferStatus;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Optional;

@Named
@ApplicationScoped
public class TransferService {
    final TransferRepository transferRepository;

    @Inject
    public TransferService(final TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }


    public Optional<TransferStatus> status(final long id) {
        final Optional<Transfer> transfer = transferRepository.get(id);

        return transfer.map(Transfer::getStatus);
    }

    public Optional<Transfer> transfer(final Transfer transfer) {
        return transferRepository.create(transfer);
    }
}
