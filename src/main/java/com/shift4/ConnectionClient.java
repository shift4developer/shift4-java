package com.shift4;

import com.shift4.connection.Connection;
import com.shift4.connection.Response;
import com.shift4.exception.Shift4Exception;
import com.shift4.request.Expand;
import com.shift4.request.RequestOptions;
import com.shift4.request.RetrieveRequest;
import com.shift4.response.ErrorResponse;
import com.shift4.response.ListResponse;
import com.shift4.util.ObjectSerializer;

import java.io.File;
import java.io.IOException;
import java.util.Map;

class ConnectionClient {

    private final ObjectSerializer objectSerializer = ObjectSerializer.INSTANCE;
    private Shift4GatewayHeadersFactory headersFactory = new Shift4GatewayHeadersFactory();
    private Connection connection;
    private String secretKey;
    private String merchantId;
    private String endpoint;

    ConnectionClient(Connection connection, String secretKey, String endpoint) {
        this.connection = connection;
        this.secretKey = secretKey;
        this.endpoint = endpoint;
    }

    ConnectionClient(Connection connection, String secretKey, String merchantId, String endpoint) {
        this(connection, secretKey, endpoint);
        this.merchantId = merchantId;
    }

    <T> T get(String path, Class<T> responseClass) {
        return get(path, responseClass, null);
    }

    <T> T get(String path, Class<T> responseClass, Expand expand) {
        return get(path, responseClass, expand, null);
    }

    <T> T get(String path, Class<T> responseClass, Expand expand, RequestOptions options) {
        RetrieveRequest request = new RetrieveRequest().expand(expand);
        String url = buildQueryString(endpoint + path, request);
        Response response = connection.get(url, buildHeaders(options));
        ensureSuccess(response);
        return objectSerializer.deserialize(response.getBody(), responseClass);
    }

    <T> T post(String path, Object request, Class<T> responseClass) {
        String requestBody = objectSerializer.serialize(request);
        Response response = connection.post(endpoint + path, requestBody, buildHeaders());
        ensureSuccess(response);
        return objectSerializer.deserialize(response.getBody(), responseClass);
    }

    public <T> T post(String path, Object request, RequestOptions options, Class<T> responseClass) {
        String requestBody = objectSerializer.serialize(request);
        Response response = connection.post(endpoint + path, requestBody, buildHeaders(options));
        ensureSuccess(response);
        return objectSerializer.deserialize(response.getBody(), responseClass);
    }

    <T> T multipart(String path, Map<String, File> files, Map<String, String> form, Class<T> responseClass) {
        Response response = connection.multipart(endpoint + path, files, form, buildHeaders());
        ensureSuccess(response);
        return objectSerializer.deserialize(response.getBody(), responseClass);
    }

    <T> ListResponse<T> list(String path, Class<T> elementClass) {
        return list(path, null, elementClass);
    }

    <T> ListResponse<T> list(String path, Object request, Class<T> elementClass) {
        return list(path, request, null, elementClass);
    }

    <T> ListResponse<T> list(String path, Object request, RequestOptions options, Class<T> elementClass) {
        String url = buildQueryString(endpoint + path, request);
        Response response = connection.get(url, buildHeaders(options));
        ensureSuccess(response);
        return objectSerializer.deserializeList(response.getBody(), elementClass);
    }

    <T> T delete(String path, Class<T> responseClass) {
        return delete(path, null, responseClass);
    }

    <T> T delete(String path, Object request, Class<T> responseClass) {
        String url = buildQueryString(endpoint + path, request);
        Response response = connection.delete(url, buildHeaders());
        ensureSuccess(response);
        return objectSerializer.deserialize(response.getBody(), responseClass);
    }

    private void ensureSuccess(Response response) {
        if (response.getStatus() != 200) {
            ErrorResponse error = objectSerializer.deserialize(response.getBody(), ErrorResponse.class);
            throw new Shift4Exception(error);
        }
    }

    private String buildQueryString(String url, Object request) {
        if (request == null) {
            return url;
        }

        return url + objectSerializer.serializeToQueryString(request);
    }

    protected Map<String, String> buildHeaders() {
        return headersFactory.create(secretKey, merchantId);
    }

    protected Map<String, String> buildHeaders(RequestOptions requestOptions) {
        return headersFactory.create(secretKey, merchantId, requestOptions);
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public void setHeadersFactory(Shift4GatewayHeadersFactory headersFactory) {
        this.headersFactory = headersFactory;
    }

    void close() throws IOException {
        if (connection != null) {
            connection.close();
        }
    }
}
