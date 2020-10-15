package org.secureauth.sarestapi.data.Requests;

import org.secureauth.sarestapi.resources.Resource;

public class LinkToAcceptFactory {

  public static AuthRequest createEmailToAcceptAuthRequest(String userId, String factorId) {
    AuthRequest authRequest = new AuthRequest();

    authRequest.setUser_id(userId);
    authRequest.setType(Resource.EMAIL_LINK);
    authRequest.setFactor_id(factorId);
    return authRequest;
  }
}
