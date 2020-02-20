package rocks.kreig.transfers.repository;

import rocks.kreig.transfers.repository.entity.Transfer;

import java.util.Optional;

public interface TransferRepository {
    Optional<Transfer> findById(long id);

    Optional<Transfer> create(Transfer transfer);

}
