package com.midas.app.services;

import com.midas.app.config.WorkFlowConfig;
import com.midas.app.exceptions.ResourceAlreadyExistsException;
import com.midas.app.exceptions.ResourceNotFoundException;
import com.midas.app.models.Account;
import com.midas.app.repositories.AccountRepository;
import com.midas.app.workflows.CreateAccountWorkflow;
import com.midas.app.workflows.UpdateAccountWorkflow;
import com.midas.generated.model.CreateAccountDto;
import com.midas.generated.model.UpdateAccountDto;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.workflow.Workflow;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl<T> implements AccountService {
  private final Logger logger = Workflow.getLogger(AccountServiceImpl.class);

  private final WorkflowClient workflowClient;

  private final AccountRepository accountRepository;

  private final WorkFlowConfig workFlowConfig;

  /**
   * createAccount creates a new account in the system or provider.
   *
   * @param details is the details of the account to be created.
   * @return Account
   */
  @Override
  public Account createAccount(CreateAccountDto details) {
    logger.info("Create account request received: {}", details);
    checkAccountExistByEmail(details);
    workFlowConfig.startCreateAccountWorker();
    CreateAccountWorkflow createAccountWorkflow =
        workflowClient.newWorkflowStub(
            CreateAccountWorkflow.class,
            WorkflowOptions.newBuilder()
                .setWorkflowId(details.getEmail())
                .setTaskQueue(CreateAccountWorkflow.QUEUE_NAME)
                .build());
    return createAccountWorkflow.createAccount(details);
  }

  /**
   * getAccounts returns a list of accounts.
   *
   * @return List<Account>
   */
  @Override
  public List<Account> getAccounts() {
    return accountRepository.findAll();
  }

  @Override
  public Account updateAccount(UpdateAccountDto details, String accountId) {
    log.info(
        "Update account request received with account Id {} and account details {}",
        accountId,
        details);
    Account account = fetchAccountById(accountId);
    workFlowConfig.startUpdateAccountWorker();
    UpdateAccountWorkflow updateAccountWorkflow =
        workflowClient.newWorkflowStub(
            UpdateAccountWorkflow.class,
            WorkflowOptions.newBuilder()
                .setWorkflowId(details.getEmail())
                .setTaskQueue(UpdateAccountWorkflow.QUEUE_NAME)
                .build());
    return updateAccountWorkflow.updateAccount(details, account);
  }

  private Account fetchAccountById(String accountId) {
    return Optional.ofNullable(accountRepository.findById(Long.valueOf(accountId)))
        .get()
        .orElseThrow(
            () -> {
              log.error("No user found against given id: {}", accountId);
              throw new ResourceNotFoundException("No User found against the Id : {}", accountId);
            });
  }

  public void checkAccountExistByEmail(CreateAccountDto details) {
    Optional.ofNullable(accountRepository.findAccountByEmail(details.getEmail()))
        .get()
        .map(
            account -> {
              log.error(
                  "unable to add user because user already exist with email: {}",
                  details.getEmail());
              throw new ResourceAlreadyExistsException(
                  "User with email {}  already exist", details.getEmail());
            });
  }
}
