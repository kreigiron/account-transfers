package rocks.kreig.transfers.service;

import rocks.kreig.transfers.repository.AccountRepository;
import rocks.kreig.transfers.resource.Account;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Optional;

@Named
@ApplicationScoped
public class AccountService {

    final AccountRepository accountRepository;

    @Inject
    public AccountService(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Optional<Account> findById(final long id) {
        final rocks.kreig.transfers.repository.entity.Account account = accountRepository.findById(id);

        return transform(account);
    }

    private Optional<Account> transform(final rocks.kreig.transfers.repository.entity.Account account) {
        if (account == null) {
            return Optional.empty();
        }

        return Optional.of(new Account(account.getId(), account.getName(), account.getNumber(), account.getBalance()));
    }
}
