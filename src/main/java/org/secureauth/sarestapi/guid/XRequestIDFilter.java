package org.secureauth.sarestapi.guid;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;

public class XRequestIDFilter implements ClientRequestFilter {

    private GUIDStrategy guidStrategy;

    public XRequestIDFilter(GUIDStrategy guidStrategy) {
        this.guidStrategy = guidStrategy;
    }

    @Override
    public void filter(ClientRequestContext requestContext) {
        requestContext.getHeaders().putSingle( "X-Request-ID", this.guidStrategy.generateRequestID() );
    }
}
