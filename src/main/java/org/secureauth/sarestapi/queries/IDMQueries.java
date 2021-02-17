package org.secureauth.sarestapi.queries;

import org.secureauth.sarestapi.resources.Resource;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author rrowcliffe@secureauth.com
 *
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

public final class IDMQueries {

    private IDMQueries(){}

    /*
    User Creation
     */
    public static String queryUsers(String realm){
        return realm + Resource.APPLIANCE_USERS;
    }
    /*
    Users Profile
    */
    public static String queryUserProfile(String realm, String userName){
        return realm + Resource.APPLIANCE_USERS + userName;
    }

    /*
    Users Profile. This method supports special characters for userId since it uses QP (Query Params) in order to create the request.
    */
    public static String queryUserProfileQP(String realm){
        return realm + removeLastChar(Resource.APPLIANCE_USERS);
    }

    /*
    Reset User Password
     */
    public static String queryUserResetPwd(String realm, String userName){
        return realm + Resource.APPLIANCE_USERS + userName + Resource.APPLIANCE_IDM_USERS_PASSWD_RESET;
    }

    /*
    Reset User Password. This method supports special characters for userId since it uses QP (Query Params) in order to create the request.
     */
    public static String queryUserResetPwdQP(String realm){
        return realm + removeLastChar(Resource.APPLIANCE_USERS) + Resource.APPLIANCE_IDM_USERS_PASSWD_RESET;
    }

    /*
    User Self-Service Change Password
     */
    public static String queryUserChangePwd(String realm, String userName){
        return realm + Resource.APPLIANCE_USERS + userName + Resource.APPLIANCE_IDM_USERS_PASSWD_CHANGE;
    }

    /*
    User Self-Service Change Password. This method supports special characters for userId since it uses QP (Query Params) in order to create the request.
     */
    public static String queryUserChangePwdQP(String realm){
        return realm + removeLastChar(Resource.APPLIANCE_USERS) + Resource.APPLIANCE_IDM_USERS_PASSWD_CHANGE;
    }

    /*
    Single User to Single Group
     */
    public static String queryUserToGroup(String realm, String userName, String groupId){
        return realm + Resource.APPLIANCE_USERS + userName + Resource.APPLIANCE_IDM_USERS_GROUPS + encodeGroup(groupId);
    }

    /*
     Single User to Single Group. This method supports special characters for userId since it uses QP (Query Params) in order to create the request.
      */
    public static String queryUserToGroupQP(String realm){
        return realm + removeLastChar(Resource.APPLIANCE_USERS) + removeLastChar(Resource.APPLIANCE_IDM_USERS_GROUPS);
    }

    /*
    Single Group to Multiple Users
     */
    public static String queryGroupToUsers(String realm, String groupId){
        return realm + Resource.APPLIANCE_IDM_GROUPS + encodeGroup(groupId) + "/users";
    }

    /*
    Single Group to Single User
     */
    public static String queryGroupToUser(String realm, String userName, String groupId){
        return realm + Resource.APPLIANCE_IDM_GROUPS + encodeGroup(groupId) + Resource.APPLIANCE_IDM_USERS + userName;
    }

    /*
    Single Group to Single User. This method supports special characters for userId since it uses QP (Query Params) in order to create the request.
     */
    public static String queryGroupToUserQP(String realm){
        return realm + removeLastChar(Resource.APPLIANCE_IDM_USERS) + Resource.APPLIANCE_IDM_GROUPS;
    }

    /*
    Single User to Multiple Groups
     */

    public static String queryUserToGroups(String realm, String userName){
        return realm + Resource.APPLIANCE_USERS + userName + Resource.APPLIANCE_IDM_USERS_GROUPS;
    }

    private static String encodeGroup(String groupId){
        String encodedGroupId = null;
        try{
            encodedGroupId = URLEncoder.encode(groupId,"UTF-8").replace("+", "%20");
        }catch(UnsupportedEncodingException uee){
            uee.printStackTrace();
        }
        return encodedGroupId;
    }

    private static String removeLastChar (String query){
        return query.substring(0, query.length()-1);
    }
}
