package org.secureauth.restapi.test.Impl;

import org.secureauth.sarestapi.SAAccess;
import org.secureauth.sarestapi.data.Response.GroupAssociationResponse;
import org.secureauth.sarestapi.data.Response.ResponseObject;
import org.secureauth.sarestapi.data.Response.UserProfileResponse;
import org.secureauth.sarestapi.data.UserProfile.NewUserProfile;
import org.secureauth.sarestapi.data.UserProfile.UserToGroups;
import org.secureauth.sarestapi.data.UserProfile.UsersToGroup;
import org.secureauth.sarestapi.interfaces.IDMInterface;

/**
 * Created by rrowcliffe on 5/14/16.
 */
public class IDMImpl implements IDMInterface {

    public IDMImpl(){}

    @Override
    public ResponseObject createUser(SAAccess saAccess, NewUserProfile newUserProfile) {
        System.out.println("START create user " + newUserProfile.getUserId() + " Request ++++++++");
        ResponseObject createUser = saAccess.createUser(newUserProfile);
        if(createUser != null){
            System.out.println(createUser.toString());
            return createUser;
        }else{
            System.out.println("Null Object you messed up");
        }
        System.out.println("END Create User " + newUserProfile.getUserId() + " Request ++++++++");
        return null;

    }

    @Override
    public ResponseObject updateUser(SAAccess saAccess, String userId, NewUserProfile newUserProfile) {
        System.out.println("START Update user " + userId + " Request ++++++++");
        ResponseObject updateUser = saAccess.updateUser(userId,newUserProfile);
        if(updateUser != null){
            System.out.println(updateUser.toString());
            System.out.println("END Update User " + userId + " Request ++++++++");
            return updateUser;
        }else{
            System.out.println("Null Object you messed up");
        }
        System.out.println("END Update User " + userId + " Request ++++++++");
        return null;
    }

    @Override
    public ResponseObject addUserToGroup(SAAccess saAccess, String userId, String groupName) {
        System.out.println("START Update user " + userId + " Request ++++++++");
        ResponseObject groupAssociationResponse = saAccess.addUserToGroup(userId,groupName);
        if(groupAssociationResponse != null){
            System.out.println(groupAssociationResponse.toString());
            System.out.println("END Update User " + userId + " Request ++++++++");
            return groupAssociationResponse;
        }else{
            System.out.println("Null Object you messed up");
        }
        System.out.println("END Update User " + userId + " Request ++++++++");
        return null;
    }

    @Override
    public GroupAssociationResponse addUsersToGroup(SAAccess saAccess, UsersToGroup usersToGroup, String groupName) {
        System.out.println("START Update group " + groupName + " Request ++++++++");
        GroupAssociationResponse groupAssociationResponse = saAccess.addUsersToGroup(usersToGroup,groupName);
        if(groupAssociationResponse != null){
            System.out.println(groupAssociationResponse.toString());
            System.out.println("END Update Group " + groupName + " Request ++++++++");
            return groupAssociationResponse;
        }else{
            System.out.println("Null Object you messed up");
        }
        System.out.println("END Update Group " + groupName + " Request ++++++++");
        return null;
    }

    @Override
    public GroupAssociationResponse addGroupToUser(SAAccess saAccess, String groupName, String userId) {
        System.out.println("START Update user " + userId + " Request ++++++++");
        GroupAssociationResponse groupAssociationResponse = saAccess.addGroupToUser(groupName,userId);
        if(groupAssociationResponse != null){
            System.out.println(groupAssociationResponse.toString());
            System.out.println("END Update User " + userId + " Request ++++++++");
            return groupAssociationResponse;
        }else{
            System.out.println("Null Object you messed up");
        }
        System.out.println("END Update User " + userId + " Request ++++++++");
        return null;
    }

    @Override
    public GroupAssociationResponse addUserToGroups(SAAccess saAccess, String userId, UserToGroups userToGroups) {
        System.out.println("START Update user " + userId + " Request ++++++++");
        GroupAssociationResponse groupAssociationResponse = saAccess.addUserToGroups(userId,userToGroups);
        if(groupAssociationResponse != null){
            System.out.println(groupAssociationResponse.toString());
            System.out.println("END Update User " + userId + " Request ++++++++");
            return groupAssociationResponse;
        }else{
            System.out.println("Null Object you messed up");
        }
        System.out.println("END Update User " + userId + " Request ++++++++");
        return null;
    }

    @Override
    public UserProfileResponse getUserProfile(SAAccess saAccess, String userId) {
        System.out.println("START Get User Profile for " + userId + " Request ++++++++");
        UserProfileResponse userProfileResponse = saAccess.getUserProfile(userId);
        if(userProfileResponse != null){
            System.out.println(userProfileResponse.toString());
            System.out.println("END Get User Profile for " + userId + " Request ++++++++");
            return userProfileResponse;
        }else{
            System.out.println("Null Object you messed up");
        }
        System.out.println("END Get User Profile for " + userId + " Request ++++++++");
        return null;
    }

    @Override
    public ResponseObject passwordReset(SAAccess saAccess, String userId, String password) {
        System.out.println("Start Password Reset " + userId + " Request ++++++++");
        ResponseObject resetPassword = saAccess.passwordReset(userId,password);
        if(resetPassword != null){
            System.out.println(resetPassword.toString());
        }else{
            System.out.println("Failed");
        }
        System.out.println("END Password Reset " + userId + " Request ++++++++");
        return null;
    }

    @Override
    public ResponseObject passwordResetQP(SAAccess saAccess, String userId, String password) {
        System.out.println("Start Password Reset " + userId + " Request ++++++++");
        ResponseObject resetPassword = saAccess.passwordResetQP(userId,password);
        if(resetPassword != null){
            System.out.println(resetPassword.toString());
        }else{
            System.out.println("Failed");
        }
        System.out.println("END Password Reset " + userId + " Request ++++++++");
        return null;
    }

    @Override
    public ResponseObject passwordChange(SAAccess saAccess, String userId, String currentPassword, String newPassword) {
        System.out.println("Start Password Change " + userId + " Request ++++++++");
        ResponseObject passwordChange = saAccess.passwordChange(userId,currentPassword,newPassword);
        if(passwordChange != null){
            System.out.println(passwordChange.toString());
        }else{
            System.out.println("Failed");
        }
        System.out.println("END Password Change " + userId + " Request ++++++++");
        return null;
    }

    @Override
    public ResponseObject passwordChangeQP(SAAccess saAccess, String userId, String currentPassword, String newPassword) {
        System.out.println("Start Password Change " + userId + " Request ++++++++");
        ResponseObject passwordChange = saAccess.passwordChangeQP(userId,currentPassword,newPassword);
        if(passwordChange != null){
            System.out.println(passwordChange.toString());
        }else{
            System.out.println("Failed");
        }
        System.out.println("END Password Change " + userId + " Request ++++++++");
        return null;
    }

}
