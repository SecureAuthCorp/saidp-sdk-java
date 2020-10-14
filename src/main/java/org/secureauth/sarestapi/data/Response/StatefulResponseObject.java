package org.secureauth.sarestapi.data.Response;

import javax.ws.rs.core.NewCookie;

public class StatefulResponseObject extends ResponseObject {

    private NewCookie sessionAffinityCookie;

    public NewCookie getSessionAffinityCookie() {
        return sessionAffinityCookie;
    }

    public void setSessionAffinityCookie(NewCookie sessionAffinityCookie) {
        this.sessionAffinityCookie = sessionAffinityCookie;
    }
}
