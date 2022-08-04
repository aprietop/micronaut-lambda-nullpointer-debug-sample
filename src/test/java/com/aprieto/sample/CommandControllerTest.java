package com.aprieto.sample;

import com.amazonaws.serverless.proxy.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micronaut.http.HttpStatus;
import jakarta.inject.Inject;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommandControllerTest extends BaseControllerTest {

    private static final String DEVICE_ID = "8627710409503814";
    public static final String REQUEST_ID = "REQ-8965ABC";

    @Inject
    ObjectMapper mapper;


    @Test
    void verify_forbidden()  {
        AwsProxyRequest request = getAwsProxyRequest();

        var authorizerContext = new ApiGatewayAuthorizerContext();
        var requestContext = new AwsProxyRequestContext();
        CognitoAuthorizerClaims cClaims = new CognitoAuthorizerClaims();
        cClaims.setSubject("some-subject");
        authorizerContext.setClaims(cClaims);
        requestContext.setAuthorizer(authorizerContext);
        request.setRequestContext(requestContext);
        AwsProxyResponse response = handler.handleRequest(request, lambdaContext);
        assertEquals(HttpStatus.FORBIDDEN.getCode(), response.getStatusCode());

    }

    @NotNull
    private AwsProxyRequest getAwsProxyRequest() {
        var request = new AwsProxyRequest();
        var requestContext = new AwsProxyRequestContext();
        requestContext.setRequestId(REQUEST_ID);
        request.setRequestContext(requestContext);
        request.setHttpMethod("POST");
        request.setPath("/commands/" + DEVICE_ID);
        return request;
    }



}
