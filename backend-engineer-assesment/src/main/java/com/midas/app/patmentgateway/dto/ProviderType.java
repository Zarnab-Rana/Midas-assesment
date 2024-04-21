package com.midas.app.patmentgateway.dto;

import lombok.Getter;

public enum ProviderType {
  STRIPE("Stripe", "PaymentGateway stripe");

  @Getter private String type;
  @Getter private String desc;

  ProviderType(String type, String des) {
    this.type = type;
    this.desc = des;
  }
}
