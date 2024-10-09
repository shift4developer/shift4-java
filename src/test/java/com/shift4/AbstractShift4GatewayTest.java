package com.shift4;

import io.github.cdimascio.dotenv.Dotenv;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public abstract class AbstractShift4GatewayTest {

    private static final Dotenv dotenv = Dotenv.configure()
            .ignoreIfMissing()
            .load();

    protected final Shift4Gateway gateway;

    public AbstractShift4GatewayTest() {
        this.gateway = createGateway("SECRET_KEY");
    }

    public Shift4Gateway createGateway(String secretKeyParamName) {
        return createGateway(secretKeyParamName, false);
    }

    public Shift4Gateway createGateway(String secretKeyParamName, boolean withExplicitMerchant) {
        String secretKey = dotenv.get(secretKeyParamName);
        assertNotNull(secretKey, String.format("specify %s as environment variable or through .env file", secretKeyParamName));

        String explicitMerchant = null;
        if (withExplicitMerchant) {
            explicitMerchant = dotenv.get("EXPLICIT_MERCHANT");
            assertNotNull(explicitMerchant, "specify EXPLICIT_MERCHANT as environment variable or through .env file");
        }

        Shift4Gateway shift4Gateway = new Shift4Gateway(secretKey, explicitMerchant);

        String apiUrl = dotenv.get("API_URL");
        if (apiUrl != null) {
            shift4Gateway.setEndpoint(apiUrl);
        }

        String uploadsUrl = dotenv.get("UPLOADS_URL");
        if (uploadsUrl != null) {
            shift4Gateway.setUploadsEndpoint(uploadsUrl);
        }

        return shift4Gateway;
    }
}
