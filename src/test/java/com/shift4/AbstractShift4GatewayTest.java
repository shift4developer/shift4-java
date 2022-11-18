package com.shift4;

import io.github.cdimascio.dotenv.Dotenv;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public abstract class AbstractShift4GatewayTest {

    private static final Dotenv dotenv = Dotenv.configure()
            .ignoreIfMissing()
            .load();

    protected final Shift4Gateway gateway;

    public AbstractShift4GatewayTest() {
        String secretKey = dotenv.get("SECRET_KEY");
        assertNotNull(secretKey, "specify SECRET_KEY as environment variable or through .env file");
        this.gateway = new Shift4Gateway(secretKey);

        String apiUrl = dotenv.get("API_URL");
        if (apiUrl != null) {
            gateway.setEndpoint(apiUrl);
        }

        String uploadsUrl = dotenv.get("UPLOADS_URL");
        if (uploadsUrl != null) {
            gateway.setUploadsEndpoint(uploadsUrl);
        }
    }
}
