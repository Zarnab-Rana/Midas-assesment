package com.midas.app.patmentgateway.service;

import com.midas.app.models.Account;
import com.midas.generated.model.CreateAccountDto;
import com.midas.generated.model.UpdateAccountDto;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Slf4j
@Service
public class StripeGatewayImpl implements StripeGateway {
  @Override
  public Customer createCustomer(CreateAccountDto account) {
    try {
      log.info("Creating customer at stripe");
      return Customer.create(createCustomerReq(account));
    } catch (StripeException e) {
      log.error("unable to create customer at stripe due to: {}", e.getMessage());
      throw new RuntimeException(e);
    }
  }

  @Override
  public Customer updateCustomer(Account account, UpdateAccountDto updateAccountDto) {
    try {
      Customer currentCustomer = Customer.retrieve(account.getProviderId());
      return currentCustomer.update(createUpdateCustomerParams(currentCustomer, updateAccountDto));
    } catch (StripeException e) {
      log.error("unable to update customer at stripe due to: {}", e.getMessage());
      throw new RuntimeException(e);
    }
  }

  private static Map<String, Object> createCustomerReq(CreateAccountDto account) {
    Map<String, Object> createCustomerReq = new HashMap<>();
    createCustomerReq.put(
        "name", account.getFirstName().strip().concat(" " + account.getLastName().strip()));
    createCustomerReq.put("email", account.getEmail());
    log.info("CreateCustomer request : {}", createCustomerReq);
    return createCustomerReq;
  }

  private Map<String, Object> createUpdateCustomerParams(
      Customer resource, UpdateAccountDto account) {
    Map<String, Object> createCustomerReq = new HashMap<>();
    if (StringUtils.hasText(account.getFirstName()) || StringUtils.hasText(account.getLastName())) {
      String newName = account.getFirstName().concat(" " + account.getLastName());
      if (!newName.equals(resource.getName())) {
        createCustomerReq.put("name", account.getFirstName().concat(" " + account.getLastName()));
      }
    }
    if (StringUtils.hasText(account.getEmail())) {
      if (!account.getEmail().equals(resource.getEmail())) {
        createCustomerReq.put("email", account.getEmail());
      }
    }
    log.info("CreateCustomer request : {}", createCustomerReq);
    return createCustomerReq;
  }
}
