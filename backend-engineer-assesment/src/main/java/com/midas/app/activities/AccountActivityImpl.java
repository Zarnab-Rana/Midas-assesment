package com.midas.app.activities;

import com.midas.app.exceptions.ApiException;
import com.midas.app.models.Account;
import com.midas.app.patmentgateway.dto.ProviderType;
import com.midas.app.patmentgateway.service.CustomerService;
import com.midas.app.repositories.AccountRepository;
import com.midas.generated.model.CreateAccountDto;
import com.midas.generated.model.UpdateAccountDto;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

@Slf4j
@RequiredArgsConstructor
public class AccountActivityImpl implements AccountActivity {

  private final AccountRepository accountRepository;

  private final CustomerService customerService;

  @Override
  public Account createAccount(CreateAccountDto details) {
    Optional<Account> accountOpt =
        Optional.ofNullable(customerService.createCustomer(details))
            .map(
                customerResponseDto -> {
                  Account account =
                      Account.builder()
                          .email(customerResponseDto.getEmail())
                          .firstName(details.getFirstName())
                          .lastName(details.getLastName())
                          .providerId(customerResponseDto.getId())
                          .providerType(ProviderType.STRIPE.getType())
                          .build();
                  return account;
                });
    return accountOpt.orElse(null);
  }

  @Override
  public Account updateAccount(UpdateAccountDto details, Account existingAccount) {
    customerService.updateCustomer(existingAccount, details);
    existingAccount.setFirstName(
        isValueUpdated(existingAccount.getFirstName(), details.getFirstName())
            ? details.getFirstName()
            : existingAccount.getFirstName());
    existingAccount.setLastName(
        isValueUpdated(existingAccount.getLastName(), details.getLastName())
            ? details.getLastName()
            : existingAccount.getLastName());
    existingAccount.setEmail(
        isValueUpdated(existingAccount.getEmail(), details.getEmail())
            ? details.getEmail()
            : existingAccount.getEmail());
    return existingAccount;
  }

  @Override
  public Account saveAccount(Account account) {
    try {
      return accountRepository.save(account);
    } catch (DataAccessException e) {
      throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
  }

  private boolean isValueUpdated(String oldVal, String newVal) {
    return (StringUtils.hasText(newVal) && !newVal.strip().equals(oldVal));
  }
}
