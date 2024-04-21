package accounts.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.midas.app.controllers.AccountController;
import com.midas.app.mappers.Mapper;
import com.midas.app.services.AccountService;
import com.midas.generated.model.AccountDto;
import com.midas.generated.model.CreateAccountDto;
import com.midas.generated.model.UpdateAccountDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class AccountControllerTest {

  @InjectMocks private AccountController accountController;

  @Mock private AccountService accountService;

  @Mock private Mapper mapper;

  @Test
  public void createAccountWithSuccess() {
    CreateAccountDto createAccountDto = new CreateAccountDto();
    when(mapper.toAccountDto(any())).thenReturn(new AccountDto());
    ResponseEntity<AccountDto> responseEntity =
        accountController.createUserAccount(createAccountDto);
    Assertions.assertNotNull(responseEntity);
    Assertions.assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
  }

  @Test
  public void updateAccountWithSuccess() {
    UpdateAccountDto updateAccountDto = new UpdateAccountDto();
    String accountId = "1";
    when(mapper.toAccountDto(any())).thenReturn(new AccountDto());
    ResponseEntity<AccountDto> responseEntity =
        accountController.updateUserAccount(accountId, updateAccountDto);
    Assertions.assertNotNull(responseEntity);
    Assertions.assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
  }
}
