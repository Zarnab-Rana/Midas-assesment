package com.midas.app.workflows;

import com.midas.app.models.Account;
import com.midas.generated.model.UpdateAccountDto;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface UpdateAccountWorkflow {
  String QUEUE_NAME = "update-account-workflow";

  /**
   * UpdateAccount updates the existing account in the system or provider.
   *
   * @param details is the details of the account to be updated.
   * @param existingAccount is already persisting account in the system.
   * @return Account
   */
  @WorkflowMethod
  Account updateAccount(UpdateAccountDto details, Account existingAccount);
}
