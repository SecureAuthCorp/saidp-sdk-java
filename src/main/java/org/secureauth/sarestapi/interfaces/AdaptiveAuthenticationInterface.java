package org.secureauth.sarestapi.interfaces;

import org.secureauth.sarestapi.SAAccess;
import org.secureauth.sarestapi.data.IPEval;
import org.secureauth.sarestapi.data.Response.ResponseObject;

/**
 * Created by rrowcliffe on 5/7/16.
 */
public interface AdaptiveAuthenticationInterface {

    IPEval ipThreatCheck(SAAccess saAccess, String userid, String ip_address);

    ResponseObject updateAccessHistories(SAAccess saAccess, String user, String ip_address);

    ResponseObject submitAdaptiveAuth(SAAccess saAccess, String user, String ipAddress);
}
