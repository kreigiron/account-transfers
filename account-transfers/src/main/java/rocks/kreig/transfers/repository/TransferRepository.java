package rocks.kreig.transfers.repository;

import rocks.kreig.transfers.resource.Transfer;

import java.util.Optional;

public interface TransferRepository {
    Optional<Transfer> get(long id);

    Optional<Transfer> create(Transfer transfer);

}
