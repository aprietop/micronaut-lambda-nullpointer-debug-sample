package com.aprieto.sample.controller;

import com.aprieto.sample.model.Roles;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller("/commands")
public class CommandController {

    private static final Logger LOG = LoggerFactory.getLogger(CommandController.class);


    @Post("/{id}")
    @Secured({Roles.ADMIN, Roles.CLIENT_APP})
    public HttpResponse<?> post(HttpRequest<?> request, String id) {
        LOG.info("Hello world {}", id);
        return HttpResponse.ok();
    }
}
