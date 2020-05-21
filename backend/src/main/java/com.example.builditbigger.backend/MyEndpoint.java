package com.example.builditbigger.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import javax.annotation.Nonnull;
import javax.inject.Named;

/** An endpoint class we are exposing */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.builditbigger.example.com",
                ownerName = "backend.builditbigger.example.com"
        )
)
public class MyEndpoint {

    /** A simple endpoint method that takes a name and says Hi back */
    @Nonnull
    @ApiMethod(name = "sayHi")
    public final MyBean sayHi(@Named("name") final String name) {
        final MyBean response = new MyBean();
        response.setData("Hi, " + name);

        return response;
    }
}