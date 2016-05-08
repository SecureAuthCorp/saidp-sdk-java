package org.secureauth.sarestapi.interfaces;

import org.secureauth.sarestapi.SAAccess;
import org.secureauth.sarestapi.data.Response.GroupAssociationResponse;
import org.secureauth.sarestapi.data.Response.ResponseObject;
import org.secureauth.sarestapi.data.Response.UserProfileResponse;
import org.secureauth.sarestapi.data.UserProfile.NewUserProfile;
import org.secureauth.sarestapi.data.UserProfile.UserToGroups;
import org.secureauth.sarestapi.data.UserProfile.UsersToGroup;

/**
 * Created by rrowcliffe on 5/8/16.
 */
public interface IDMInterface {

    ResponseObject createUser(SAAccess saAccess, NewUserProfile newUserProfile);

    ResponseObject updateUser(SAAccess saAccess, NewUserProfile newUserProfile);

    GroupAssociationResponse addUserToGroup(SAAccess saAccess, String userid, String groupName);

    GroupAssociationResponse addUserToGroup(SAAccess saAccess, UsersToGroup usersToGroup, String groupName);

    GroupAssociationResponse addGroupToUser(SAAccess saAccess, String groupName, String userid);

    GroupAssociationResponse addUserToGroups(SAAccess saAccess, String userId, UserToGroups userToGroups);

    UserProfileResponse getUserProfile(SAAccess saAccess, String userid);

    ResponseObject passwordReset(SAAccess saAccess, String userid, String password);

    ResponseObject passwordChange(SAAccess saAccess, String userid, String currentPassword, String newPassword);


}
