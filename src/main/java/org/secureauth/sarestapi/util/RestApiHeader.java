package org.secureauth.sarestapi.util;


import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.binary.Base64;
import org.secureauth.sarestapi.data.*;
import org.secureauth.sarestapi.resources.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author rrowcliffe@secureauth.com

Copyright (c) 2015, SecureAuth
All rights reserved.

Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.

2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.

3. Neither the name of the copyright holder nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
public class RestApiHeader {

    private static Logger logger = LoggerFactory.getLogger(RestApiHeader.class);
    public RestApiHeader(){}


    //Payload in header
    public static String getAuthorizationHeader(SAAuth saAuth , String requestMethod, String uriPath, Object object, String ts){

        //Build our string for the AuthHeader
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(requestMethod).append("\n")
                .append(ts).append("\n")
                .append(saAuth.getApplicationID()).append("\n")
                .append(Resource.SLASH + uriPath).append("\n")
                .append(JSONUtil.convertObjectToJSON(object));


        String authHeader = "";
        //Create a SHA256 Hash
        String base64Sha = "";
        try {
            base64Sha = new String(Base64.encodeBase64(HMACUtil.encode(saAuth.getApplicationKey(), stringBuilder.toString())));
        }catch(Exception e){
            logger.error("Exception occurred while generating Authorization Header\n" + e.getMessage() + "\n", e);
        }

        String appId = saAuth.getApplicationID() + ":" + base64Sha;
        logger.trace("Auth Header before second encoding  " + appId + "\n");
        try {
            authHeader = "Basic " + Base64.encodeBase64String(appId.getBytes("UTF-8"));
        }catch(UnsupportedEncodingException uee){
            logger.error("Exception Encoding\n" + uee.getMessage() + "\n", uee);
        }

        return authHeader;
    }

    //No Payload in header
    public static String getAuthorizationHeader(SAAuth saAuth, String requestMethod, String uriPath, String ts){
        //Build our string for the AuthHeader
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(requestMethod).append("\n")
                .append(ts).append("\n")
                .append(saAuth.getApplicationID()).append("\n")
                .append(Resource.SLASH + uriPath);

        String authHeader = "";
        //Create a SHA256 Hash
        String base64Sha = "";
        try {
            base64Sha = new String(Base64.encodeBase64(HMACUtil.encode(saAuth.getApplicationKey(), stringBuilder.toString())));
        }catch(Exception e){
            logger.error(new StringBuilder().append("Exception occurred while generating Authorization Header\n").append(e.getMessage()).append("\n").toString(), e);
        }

        String appId = saAuth.getApplicationID() + ":" + base64Sha;
        logger.trace(new StringBuilder("Auth Header before second encoding  ").append(appId).append("\n").toString());
        authHeader = "Basic " + Base64.encodeBase64String(appId.getBytes(StandardCharsets.UTF_8));
        
        return authHeader;
    }

}
