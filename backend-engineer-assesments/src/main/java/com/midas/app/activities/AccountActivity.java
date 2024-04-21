package com.midas.app.activities;

import com.midas.app.models.Account;
import com.midas.generated.model.CreateAccountDto;
import com.midas.generated.model.UpdateAccountDto;
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface AccountActivity {

  /**
   * createAccount saves an account in the paymentGateway.
   *
   * @param accountDto is the account to be created
   * @return Account
   */
  @ActivityMethod
  Account createAccount(CreateAccountDto accountDto);

  /**
   * updateAccount update the account in the paymentGateway.
   *
   * @param details is the account details needs to be updated
   * @param existingAccount is the existing account needs to compare which fields needs to change.
   * @return Account
   */
  @ActivityMethod
  Account updateAccount(UpdateAccountDto details, Account existingAccount);

  /**
   * saveAccount saves an account in the data store.
   *
   * @param account is the account to be saved
   * @return Account
   */
  @ActivityMethod
  Account saveAccount(Account account);
}
