package org.secureauth.sarestapi.util;


import com.sun.jersey.api.json.JSONConfiguration;
import org.apache.commons.codec.binary.Base64;
import org.secureauth.sarestapi.data.AuthRequest;
import org.secureauth.sarestapi.data.IPEvalRequest;
import org.secureauth.sarestapi.data.SAAuth;
import org.secureauth.sarestapi.resources.s;

import java.io.UnsupportedEncodingException;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * @author rrowcliffe@secureauth.com
 *
 * <p>
 * Copyright 2015 SecureAuth Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */
public class RestApiHeader {

    private String authHeader;
    private StringBuilder stringBuilder;
    private static Logger logger=Logger.getLogger(RestApiHeader.class.getName());
    public RestApiHeader(){}

    public String getAuthorizationHeader(SAAuth saAuth , String requestMethod, String uriPath, AuthRequest authRequest,String ts){


        //Build our string for the AuthHeader
        stringBuilder = new StringBuilder();
        stringBuilder.append(requestMethod).append("\n")
                .append(ts).append("\n")
                .append(saAuth.getApplicationID()).append("\n")
                .append( s.SLASH + uriPath).append("\n")
                .append(JSONUtil.getJSONStringFromObject(authRequest));

        //System.out.println("\nRequest: \n" + stringBuilder + "\n");

        //System.out.println("Auth JSON : " + JSONUtil.getJSONStringFromObject(authRequest));



        //Create a SHA256 Hash
        String base64Sha = "";
        try {
            base64Sha = new String(Base64.encodeBase64(HMACUtil.encode(saAuth.getApplicationKey(), stringBuilder.toString())));
        }catch(Exception e){
            logger.log(Level.SEVERE,new StringBuilder().append("Exception occurred while generating Authorization Header\n").append(e.getMessage()).append("\n").toString());
        }

        String appId = saAuth.getApplicationID() + ":" + base64Sha;
        logger.log(Level.FINEST,new StringBuilder("Auth Header before second encoding  ").append(appId).append("\n").toString());
        try {
            authHeader = "Basic " + Base64.encodeBase64String(appId.getBytes("UTF-8"));
        }catch(UnsupportedEncodingException uee){
            logger.log(Level.SEVERE, new StringBuilder().append("Exception Encoding\n").append(uee.getMessage()).append("\n").toString());
        }

        return authHeader;
    }


    public String getAuthorizationHeader(SAAuth saAuth , String requestMethod, String uriPath, IPEvalRequest ipEvalRequest, String ts){

        //Build our string for the AuthHeader
        stringBuilder = new StringBuilder();
        stringBuilder.append(requestMethod).append("\n")
                .append(ts).append("\n")
                .append(saAuth.getApplicationID()).append("\n")
                .append(s.SLASH + uriPath).append("\n")
                .append(JSONUtil.getJSONStringFromObject(ipEvalRequest));



        //Create a SHA256 Hash
        String base64Sha = "";
        try {
            base64Sha = new String(Base64.encodeBase64(HMACUtil.encode(saAuth.getApplicationKey(), stringBuilder.toString())));
        }catch(Exception e){
            logger.log(Level.SEVERE,new StringBuilder().append("Exception occurred while generating Authorization Header\n").append(e.getMessage()).append("\n").toString());
        }

        String appId = saAuth.getApplicationID() + ":" + base64Sha;
        logger.log(Level.FINEST,new StringBuilder("Auth Header before second encoding  ").append(appId).append("\n").toString());
        try {
            authHeader = "Basic " + Base64.encodeBase64String(appId.getBytes("UTF-8"));
        }catch(UnsupportedEncodingException uee){
            logger.log(Level.SEVERE, new StringBuilder().append("Exception Encoding\n").append(uee.getMessage()).append("\n").toString());
        }

        return authHeader;
    }

    public String getAuthorizationHeader(SAAuth saAuth, String requestMethod, String uriPath, String ts){
        //Build our string for the AuthHeader
        stringBuilder = new StringBuilder();
        stringBuilder.append(requestMethod).append("\n")
                .append(ts).append("\n")
                .append(saAuth.getApplicationID()).append("\n")
                .append(s.SLASH + uriPath);

        //System.out.println("\nRequest: \n" + stringBuilder + "\n");

        //Create a SHA256 Hash
        String base64Sha = "";
        try {
            base64Sha = new String(Base64.encodeBase64(HMACUtil.encode(saAuth.getApplicationKey(), stringBuilder.toString())));
        }catch(Exception e){
            logger.log(Level.SEVERE,new StringBuilder().append("Exception occurred while generating Authorization Header\n").append(e.getMessage()).append("\n").toString());
        }

        String appId = saAuth.getApplicationID() + ":" + base64Sha;
        logger.log(Level.FINEST, new StringBuilder("Auth Header before second encoding  ").append(appId).append("\n").toString());
        try {
            authHeader = "Basic " + Base64.encodeBase64String(appId.getBytes("UTF-8"));
        }catch(UnsupportedEncodingException uee){
            logger.log(Level.SEVERE, new StringBuilder().append("Exception Encoding\n").append(uee.getMessage()).append("\n").toString());
        }

        return authHeader;
    }

}
