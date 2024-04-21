package com.midas.app.mappers;

import com.midas.app.models.Account;
import com.midas.generated.model.AccountDto;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
  // Prevent instantiation
  private Mapper() {}

  /**
   * toAccountDto maps an account to an account dto.
   *
   * @param account is the account to be mapped
   * @return AccountDto
   */
  public AccountDto toAccountDto(Account account) {
    var accountDto = new AccountDto();

    accountDto
        .id(account.getId())
        .firstName(account.getFirstName())
        .lastName(account.getLastName())
        .email(account.getEmail())
        .providerId(account.getProviderId())
        .providerType(account.getProviderType())
        .createdAt(account.getCreatedAt())
        .updatedAt(account.getUpdatedAt());

    return accountDto;
  }
}
