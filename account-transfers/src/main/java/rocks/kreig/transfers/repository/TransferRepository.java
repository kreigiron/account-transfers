package rocks.kreig.transfers.repository;

import rocks.kreig.transfers.resource.Transfer;

import java.util.Optional;

public interface TransferRepository {
    Optional<Transfer> findById(long id);

    Optional<Transfer> create(Transfer transfer);

}
