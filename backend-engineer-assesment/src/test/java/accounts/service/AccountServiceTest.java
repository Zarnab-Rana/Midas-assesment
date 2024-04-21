package accounts.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.midas.app.config.WorkFlowConfig;
import com.midas.app.exceptions.ResourceAlreadyExistsException;
import com.midas.app.exceptions.ResourceNotFoundException;
import com.midas.app.models.Account;
import com.midas.app.repositories.AccountRepository;
import com.midas.app.services.AccountServiceImpl;
import com.midas.app.workflows.CreateAccountWorkFlowImpl;
import com.midas.app.workflows.CreateAccountWorkflow;
import com.midas.generated.model.CreateAccountDto;
import com.midas.generated.model.UpdateAccountDto;
import io.temporal.client.WorkflowClient;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

  @InjectMocks private AccountServiceImpl accountServiceImpl;

  @Mock private CreateAccountWorkflow createAccountWorkflow;

  @Mock private CreateAccountWorkFlowImpl createAccountWorkflowImpl;

  @Mock private AccountRepository accountRepository;

  @Mock private WorkflowClient workflowClient;

  @Mock private WorkFlowConfig workFlowConfig;

  @Test
  public void createAccountWithDuplicateEmail() {
    try {
      CreateAccountDto createAccountDto =
          new CreateAccountDto().firstName("abc").lastName("xyz").email("abc@gmail.com");
      when(accountRepository.findAccountByEmail(createAccountDto.getEmail()))
          .thenReturn(Optional.of(new Account()));
      accountServiceImpl.createAccount(createAccountDto);
    } catch (ResourceAlreadyExistsException e) {
      Assertions.assertEquals(e.getApiError().getCode(), HttpStatus.CONFLICT);
    }
  }

  @Test
  public void updateAccountWithInvalidId() {
    try {
      UpdateAccountDto updateAccountDto =
          new UpdateAccountDto().firstName("abc").lastName("xyz").email("abc@gmail.com");
      when(accountRepository.findById(any())).thenReturn(Optional.empty());
      when(accountServiceImpl.updateAccount(updateAccountDto, "1")).thenThrow();
    } catch (ResourceNotFoundException e) {
      Assertions.assertEquals(e.getApiError().getCode(), HttpStatus.NOT_FOUND);
    }
  }
}
