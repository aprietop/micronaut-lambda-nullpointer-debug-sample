package com.aprieto.sample;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.internal.testutils.MockLambdaContext;
import com.amazonaws.services.lambda.runtime.Context;
import io.micronaut.function.aws.proxy.MicronautLambdaHandler;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BaseControllerTest extends BaseMongoDataTest {

    protected static MicronautLambdaHandler handler;
    protected static Context lambdaContext = new MockLambdaContext();

    @BeforeAll
    void setupSpec() {
        try {
            handler = new MicronautLambdaHandler(application.getApplicationContext());
        } catch (ContainerInitializationException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    void cleanupSpec() {
        handler.getApplicationContext().close();
    }

}
