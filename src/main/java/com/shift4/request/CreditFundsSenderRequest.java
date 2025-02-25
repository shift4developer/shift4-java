package com.shift4.request;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.shift4.enums.CreditSourceOfFunds;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreditFundsSenderRequest {

    private CardRequest card;
    private String accountNumber;
    private CreditSourceOfFunds source;

    @JsonIgnore
    private final Map<String, Object> other = new HashMap<>();

    public CreditFundsSenderRequest() {
    }

    public CreditFundsSenderRequest(CardRequest card, CreditSourceOfFunds source) {
        this.card = card;
        this.source = source;
    }

    public CreditFundsSenderRequest(String accountNumber, CreditSourceOfFunds source) {
        this.accountNumber = accountNumber;
        this.source = source;
    }

    public CardRequest getCard() {
        return card;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public CreditSourceOfFunds getSource() {
        return source;
    }

    public CreditFundsSenderRequest card(CardRequest card) {
        this.card = card;
        return this;
    }

    public CreditFundsSenderRequest accountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public CreditFundsSenderRequest source(CreditSourceOfFunds source) {
        this.source = source;
        return this;
    }

    @JsonAnyGetter
    private Map<String, Object> getOtherMap() {
        return other;
    }

    @JsonAnySetter
    public CreditFundsSenderRequest set(String name, Object value) {
        other.put(name, value);
        return this;
    }
}
