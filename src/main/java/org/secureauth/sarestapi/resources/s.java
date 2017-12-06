package org.secureauth.sarestapi.resources;

/**
 /**
 * @author rrowcliffe@secureauth.com
 *
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


public class s {

    public static final String API_VERSION = "v1";
    public static final String HTTPS = "https://";
    public static final String HTTP = "http://";
    public static final String COLON = ":";
    public static final String SLASH = "/";
    public static final String APPLIANCE_AUTH="/api/"+ API_VERSION +"/auth";
    public static final String APPLIANCE_AAUTH="/api/"+ API_VERSION +"/adaptauth";
    public static final String APPLIANCE_USERS="/api/"+ API_VERSION + "/users/";
    public static final String APPLIANCE_IDM_USERS="/users/";
    public static final String APPLIANCE_IDM_USERS_PASSWD_RESET="/resetpwd";
    public static final String APPLIANCE_IDM_USERS_PASSWD_CHANGE="/changepwd";
    public static final String APPLIANCE_IDM_USERS_GROUPS="/groups/";
    public static final String APPLIANCE_IDM_GROUPS="/api/" + API_VERSION + "/groups/";
    public static final String APPLIANCE_FACTORS="/factors";
    public static final String APPLIANCE_IPEVAL="/api/"+ API_VERSION + "/ipeval";
    public static final String APPLIANCE_DFP="/api/" + API_VERSION + "/dfp";
    public static final String APPLIANCE_DFP_JS=APPLIANCE_DFP + "/js";
    public static final String APPLIANCE_DFP_VALIDATE=APPLIANCE_DFP + "/validate";
    public static final String APPLIANCE_DFP_CONFIRM=APPLIANCE_DFP + "/confirm";
    public static final String APPLIANCE_ACCESSHISTORY="/api/" + API_VERSION + "/accesshistory";
    public static final String APPLIANCE_BEHAVEBIO="/api/" + API_VERSION + "/behavebio";
    public static final String APPLIANCE_BEHAVEBIO_JS=APPLIANCE_BEHAVEBIO + "/js";
    public static final String APPLIANCE_NUMBERPROFILE="/api/" + API_VERSION + "/numberprofile";
    public static final String APPLIANCE_OTP="/api/" + API_VERSION + "/otp";
    public static final String APPLIANCE_OTP_VALIDATE=APPLIANCE_OTP + "/validate";


    public static final String STATUS_INVALID="invalid";
    public static final String STATUS_SERVER_ERROR="server_error";
    public static final String STATUS_VALID="valid";
    public static final String STATUS_FOUND="found";
    public static final String STATUS_VERIFIED="verified";
    public static final String STATUS_NOT_FOUND="not_found";
    public static final String STATUS_FAILED="failed";
    public static final String STATUS_ERROR="error";

    public static final String PHONES="Phones";
    public static final String PHONE="Phone";
    public static final String KBQS="KBQS";
    public static final String KBQ="KBQ";
    public static final String HELPDESKS="HelpDesks";
    public static final String HELPDESK="HelpDesk";
    public static final String OATHS="Oaths";
    public static final String OATH="Oath";
    public static final String PUSHS="Pushs";
    public static final String PUSH="Push";
    public static final String FACTORS="factors";
    public static final String FACTOR="factor";
    public static final String GEOLOC="GeoLoc";








}
