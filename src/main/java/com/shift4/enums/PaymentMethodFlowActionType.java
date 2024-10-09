package com.shift4.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PaymentMethodFlowActionType {

  NONE("none"),
  THREE_D_SECURE("three_d_secure"),

  UNRECOGNIZED("unrecognized")
  ;

  private final String value;

  PaymentMethodFlowActionType(String value) {
    this.value = value;
  }

  @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
  public static PaymentMethodFlowActionType fromValue(String value) {
    if (value == null) {
      return null;
    }
    for (PaymentMethodFlowActionType type : values()) {
      if (type.value.equalsIgnoreCase(value)) {
        return type;
      }
    }

    return UNRECOGNIZED;
  }

  @JsonValue
  public String getValue() {
    return value;
  }
}
