package com.midas.app.patmentgateway.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CustomerResponseDto {

  private String id;
  private String name;
  private String email;
}
