package rocks.kreig.transfers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rocks.kreig.transfers.resource.Account;
import rocks.kreig.transfers.service.AccountService;

import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.Optional;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

class AccountResourceTest {

    private AccountService accountService = mock(AccountService.class);
    private AccountResource accountResource;

    @BeforeEach
    public void init() {
        accountResource = new AccountResource(accountService);
    }

    @Test
    public void testAccount() {

        final Long id = 1L;
        Optional<Account> account = Optional.of(new Account(id, "name", "number", BigDecimal.ZERO));

        given(accountService.findById(id)).willReturn(account);

        final Response response = accountResource.account(id);

        then(accountService).should().findById(id);

        assertNotNull(response);
        final Account returnedAccount = (Account) response.getEntity();

        assertAll("response",
                () -> assertNotNull(returnedAccount),
                () -> assertEquals(200, response.getStatus()),
                () -> assertEquals(id, returnedAccount.getId())
        );
    }

    @Test
    public void testAccount_NotFound() {

        final Long id = 1L;
        Optional<Account> account = Optional.empty();

        given(accountService.findById(id)).willReturn(account);

        final Response response = accountResource.account(id);

        then(accountService).should().findById(id);

        assertNotNull(response);
        assertAll("response",
                () -> assertNotNull(response),
                () -> assertEquals(NOT_FOUND.getStatusCode(), response.getStatus()),
                () -> assertNull(response.getEntity())
        );
    }
}