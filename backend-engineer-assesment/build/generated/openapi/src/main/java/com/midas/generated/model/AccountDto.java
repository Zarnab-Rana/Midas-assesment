package com.midas.generated.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.time.OffsetDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * AccountDto
 */

@JsonTypeName("account")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-21T21:37:05.820424200+05:00[Asia/Karachi]")
public class AccountDto {

  private Long id = null;

  private String firstName;

  private String lastName;

  private String email;

  private String providerType;

  private String providerId;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime createdAt;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime updatedAt;

  public AccountDto id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * User's ID
   * @return id
  */
  
  @Schema(name = "id", accessMode = Schema.AccessMode.READ_ONLY, example = "fba5b240-0e68-4e64-aca6-f12022dc4be3", description = "User's ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public AccountDto firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  /**
   * User's first name
   * @return firstName
  */
  
  @Schema(name = "firstName", example = "John", description = "User's first name", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("firstName")
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public AccountDto lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  /**
   * User's last name
   * @return lastName
  */
  
  @Schema(name = "lastName", example = "Doe", description = "User's last name", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("lastName")
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public AccountDto email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Email of user
   * @return email
  */
  
  @Schema(name = "email", example = "john@doe.com", description = "Email of user", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("email")
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public AccountDto providerType(String providerType) {
    this.providerType = providerType;
    return this;
  }

  /**
   * PaymentGateway
   * @return providerType
  */
  
  @Schema(name = "providerType", example = "Stripe", description = "PaymentGateway", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("providerType")
  public String getProviderType() {
    return providerType;
  }

  public void setProviderType(String providerType) {
    this.providerType = providerType;
  }

  public AccountDto providerId(String providerId) {
    this.providerId = providerId;
    return this;
  }

  /**
   * relevant account Id stored at PaymentGateway
   * @return providerId
  */
  
  @Schema(name = "providerId", example = "cus_NffrFeUfNV2Hib", description = "relevant account Id stored at PaymentGateway", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("providerId")
  public String getProviderId() {
    return providerId;
  }

  public void setProviderId(String providerId) {
    this.providerId = providerId;
  }

  public AccountDto createdAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  /**
   * Prospect creation time
   * @return createdAt
  */
  @Valid 
  @Schema(name = "createdAt", accessMode = Schema.AccessMode.READ_ONLY, example = "2022-04-13T01:52:40.690387Z", description = "Prospect creation time", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("createdAt")
  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public AccountDto updatedAt(OffsetDateTime updatedAt) {
    this.updatedAt = updatedAt;
    return this;
  }

  /**
   * Prospect last updated time
   * @return updatedAt
  */
  @Valid 
  @Schema(name = "updatedAt", accessMode = Schema.AccessMode.READ_ONLY, example = "2022-04-13T01:52:40.690387Z", description = "Prospect last updated time", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("updatedAt")
  public OffsetDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(OffsetDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AccountDto account = (AccountDto) o;
    return Objects.equals(this.id, account.id) &&
        Objects.equals(this.firstName, account.firstName) &&
        Objects.equals(this.lastName, account.lastName) &&
        Objects.equals(this.email, account.email) &&
        Objects.equals(this.providerType, account.providerType) &&
        Objects.equals(this.providerId, account.providerId) &&
        Objects.equals(this.createdAt, account.createdAt) &&
        Objects.equals(this.updatedAt, account.updatedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName, email, providerType, providerId, createdAt, updatedAt);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccountDto {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    providerType: ").append(toIndentedString(providerType)).append("\n");
    sb.append("    providerId: ").append(toIndentedString(providerId)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    updatedAt: ").append(toIndentedString(updatedAt)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

