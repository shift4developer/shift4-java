package com.shift4.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shift4.response.Amount;

import java.io.IOException;
import java.util.List;

public class AmountDeserializer extends JsonDeserializer<Amount> {

    @Override
    public Amount deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);

        if (node.isInt()) {
            return new Amount(node.asInt());
        } else if (node.isObject()) {
            ObjectMapper mapper = (ObjectMapper) p.getCodec();
            return new Amount(
                    node.has("options") ? mapper.convertValue(node.get("options"), new TypeReference<List<Integer>>() {}) : null,
                    node.has("custom") ? mapper.convertValue(node.get("custom"), Amount.CustomAmount.class) : null
            );
        }

        return null;
    }
}