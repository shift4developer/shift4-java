package com.shift4.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.shift4.util.AmountDeserializer;

import java.util.List;

@JsonDeserialize(using = AmountDeserializer.class)
public class Amount {

    private Integer value;
    private List<Integer> options;
    private CustomAmount custom;

    public Amount(Integer value) {
        this.value = value;
    }

    public Amount(List<Integer> options, CustomAmount custom) {
        this.options = options;
        this.custom = custom;
    }

    public Integer getValue() {
        return value;
    }

    public List<Integer> getOptions() {
        return options;
    }

    public CustomAmount getCustom() {
        return custom;
    }

    public static class CustomAmount {

        private Integer min;
        private Integer max;

        public Integer getMin() {
            return min;
        }

        public Integer getMax() {
            return max;
        }
    }
}
