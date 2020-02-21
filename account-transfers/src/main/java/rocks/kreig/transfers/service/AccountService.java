package rocks.kreig.transfers.service;

import rocks.kreig.transfers.repository.AccountRepository;
import rocks.kreig.transfers.resource.Account;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Optional;

/**
 * Service for account operations
 */
@Named
@RequestScoped
public class AccountService {

    final AccountRepository accountRepository;

    @Inject
    public AccountService(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Optional<Account> findById(final long id) {
        final Optional<rocks.kreig.transfers.repository.entity.Account> account = accountRepository.findById(id);

        return transform(account);
    }

    private Optional<Account> transform(final Optional<rocks.kreig.transfers.repository.entity.Account> accountOptional) {
        return accountOptional.map(account -> new Account(account.getId(), account.getName(), account.getNumber(), account.getBalance()));
    }
}
