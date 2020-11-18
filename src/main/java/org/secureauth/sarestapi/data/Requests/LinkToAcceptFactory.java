package org.secureauth.sarestapi.data.Requests;

import org.secureauth.sarestapi.resources.Resource;

public class LinkToAcceptFactory {

  public static AuthRequest createLinkToAcceptAuthRequest(String userId, String factorId, String type) {
    AuthRequest authRequest = new AuthRequest();

    authRequest.setUser_id(userId);
    authRequest.setType(type);
    authRequest.setFactor_id(factorId);
    return authRequest;
  }
}
