package org.secureauth.sarestapi.filters;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.Response;
import java.io.IOException;

/**
 * Created by rrowcliffe on 5/6/16.
 */
public class SACheckRequestFilter implements ClientRequestFilter {

    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        if (requestContext.getHeaders().get("Authorization") == null) {
            requestContext.abortWith(Response.status(Response.Status.BAD_REQUEST).entity("Authorization header must be defined.").build());
        }
        if (requestContext.getHeaders().get("X-SA-Date") == null) {
            requestContext.abortWith(Response.status(Response.Status.BAD_REQUEST).entity("X-SA-Date header must be defined.").build());
        }
    }
}
