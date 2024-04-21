package com.midas.app.patmentgateway.service;

import com.midas.app.exceptions.ApiException;
import com.midas.app.models.Account;
import com.midas.app.patmentgateway.dto.CustomerResponseDto;
import com.midas.generated.model.CreateAccountDto;
import com.midas.generated.model.UpdateAccountDto;
import com.stripe.Stripe;
import com.stripe.model.Customer;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class CustomerService {

  @Value("${stripe.api-key}")
  private String apiKey;

  private final StripeGateway stripeGateway;

  @PostConstruct
  private void init() {
    Stripe.apiKey = this.apiKey;
  }

  public CustomerResponseDto createCustomer(CreateAccountDto account) {
    try {
      log.info("Creating customer at stripe with request : {}", account);
      Customer customer = stripeGateway.createCustomer(account);
      return CustomerResponseDto.builder()
          .id(customer.getId())
          .email(customer.getEmail())
          .name(customer.getName())
          .build();
    } catch (Exception e) {
      log.error("Unable to create account at payment gateway due to: {}", e.getMessage());
      throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
    }
  }

  public CustomerResponseDto updateCustomer(Account account, UpdateAccountDto updateAccountDto) {
    try {
      Customer customer = stripeGateway.updateCustomer(account, updateAccountDto);
      return CustomerResponseDto.builder()
          .id(customer.getId())
          .email(customer.getEmail())
          .name(customer.getName())
          .build();
    } catch (Exception e) {
      log.error("Unable to update account at payment gateway due to: {}", e.getMessage());
      throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
    }
  }
}
