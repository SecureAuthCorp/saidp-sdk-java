package org.secureauth.restapi.test.Impl;

import org.secureauth.sarestapi.SAAccess;
import org.secureauth.sarestapi.data.IPEval;
import org.secureauth.sarestapi.data.Response.ResponseObject;
import org.secureauth.sarestapi.interfaces.AdaptiveAuthenticationInterface;

/**
 * Created by rrowcliffe on 5/14/16.
 */
public class AdaptiveAuthenticationImpl implements AdaptiveAuthenticationInterface {
    public AdaptiveAuthenticationImpl(){}

    @Override
    public IPEval ipThreatCheck(SAAccess saAccess, String userid, String ip_address) {
        IPEval ipEval = saAccess.iPEvaluation(userid, ip_address);
        //System.out.println("Start IPEvaluation  +++++++++++++++++");
        if (ipEval != null) {
            System.out.println(ipEval.toString());
        } else {
            System.out.println("If you See this then Something went Wrong!!");
        }
       // System.out.println("End IPEvaluation  +++++++++++++++++");
        return ipEval;
    }

    @Override
    public ResponseObject updateAccessHistories(SAAccess saAccess, String user, String ip_address) {
        System.out.println("START Access Histories test +++++++++");
        ResponseObject ahistories = saAccess.accessHistory(user, ip_address);
        System.out.println(ahistories.toString());
        System.out.println("END Access Histories test +++++++++++");
        return ahistories;
    }

    @Override
    public ResponseObject submitAdaptiveAuth(SAAccess saAccess, String user, String ipAddress) {
        System.out.println("Start Adaptive Test ++++++++++++++++");
        ResponseObject responseObject = saAccess.adaptiveAuthQuery(user,ipAddress);
        System.out.println(responseObject.toString());
        System.out.println("End Adaptive Test ++++++++++++++++++");
        return responseObject;
    }
}
