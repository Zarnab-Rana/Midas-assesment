package com.midas.app.workflows;

import com.midas.app.activities.AccountActivity;
import com.midas.app.models.Account;
import com.midas.generated.model.UpdateAccountDto;
import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.workflow.Workflow;
import java.time.Duration;

public class UpdateAccountWorkFlowImpl implements UpdateAccountWorkflow {

  private final RetryOptions retryoptions =
      RetryOptions.newBuilder()
          .setInitialInterval(Duration.ofSeconds(1))
          .setMaximumInterval(Duration.ofSeconds(100))
          .setBackoffCoefficient(2)
          .setMaximumAttempts(50000)
          .build();
  private final ActivityOptions options =
      ActivityOptions.newBuilder()
          .setStartToCloseTimeout(Duration.ofSeconds(30))
          .setRetryOptions(retryoptions)
          .build();
  private final AccountActivity activity = Workflow.newActivityStub(AccountActivity.class, options);

  boolean isAccountPersisted = false;

  @Override
  public Account updateAccount(UpdateAccountDto details, Account existingAccount) {
    Account account = activity.updateAccount(details, existingAccount);
    return activity.saveAccount(account);
  }
}
