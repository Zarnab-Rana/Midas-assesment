package com.midas.app.patmentgateway.service;

import com.midas.app.models.Account;
import com.midas.generated.model.CreateAccountDto;
import com.midas.generated.model.UpdateAccountDto;
import com.stripe.model.Customer;

public interface StripeGateway {

  Customer createCustomer(CreateAccountDto account);

  Customer updateCustomer(Account account, UpdateAccountDto updateAccountDto);
}
