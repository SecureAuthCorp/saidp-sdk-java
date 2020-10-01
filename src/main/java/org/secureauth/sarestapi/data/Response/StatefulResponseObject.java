package org.secureauth.sarestapi.data.Response;

import javax.ws.rs.core.NewCookie;

public class StatefulResponseObject extends ResponseObject {

    private NewCookie ingressCookie;

    public NewCookie getIngressCookie() {
        return ingressCookie;
    }

    public void setIngressCookie(NewCookie ingressCookie) {
        this.ingressCookie = ingressCookie;
    }
}
