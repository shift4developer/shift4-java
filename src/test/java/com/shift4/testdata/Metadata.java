package com.shift4.testdata;

import java.time.Instant;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
public abstract class Metadata {

    private Metadata() {
    }

    public static Map<String, String> metadata() {
        return Collections.singletonMap("key", "value");
    }
}