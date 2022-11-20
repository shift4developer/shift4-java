package com.shift4.testdata;

import java.time.Instant;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

public abstract class Emails {

    private Emails() {
    }

    public static String email() {
        long timestamp = Instant.now().getEpochSecond();
        return "testEmail+shift4-java." + timestamp + "." + randomAlphanumeric(5) + "@dev.shift4.com";

    }
}
