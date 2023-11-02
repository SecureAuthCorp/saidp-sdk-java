package org.secureauth.sarestapi.data.Requests;

import org.secureauth.sarestapi.data.PushAcceptDetails;
import org.secureauth.sarestapi.resources.Resource;

public class PushToAcceptRequestsFactory {

    public static PushToAcceptBiometricsRequest createPushToAcceptBiometricRequest(String biometricType, String userId, String factorId, String endUserIP, String clientCompany, String clientDescription) {
        PushToAcceptBiometricsRequest req = new PushToAcceptBiometricsRequest();
        req.setUser_id(userId);
        req.setType(Resource.PUSH_ACCEPT_BIOMETRIC);
        req.setFactor_id(factorId);
        req.setBiometricType( biometricType );
        PushAcceptDetails pad = new PushAcceptDetails();
        pad.setEnduser_ip(endUserIP);
        if (clientCompany != null) {
            pad.setCompany_name(clientCompany);
        }
        if (clientDescription != null) {
            pad.setApplication_description(clientDescription);
        }
        req.setPush_accept_details(pad);
        return req;
    }

    public static PushToAcceptRequest createPushToAcceptRequest(String userId, String factor_id, String endUserIP, String clientCompany, String clientDescription, String type) {
        PushToAcceptRequest req = new PushToAcceptRequest();
        req.setUser_id(userId);
        req.setType(type);
        req.setFactor_id(factor_id);
        PushAcceptDetails pad = new PushAcceptDetails();
        pad.setEnduser_ip(endUserIP);
        if (clientCompany != null) {
            pad.setCompany_name(clientCompany);
        }
        if (clientDescription != null) {
            pad.setApplication_description(clientDescription);
        }
        req.setPush_accept_details(pad);
        return req;
    }
}
