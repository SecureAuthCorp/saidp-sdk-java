package org.secureauth.sarestapi.data.Requests;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by rrowcliffe on 5/3/16.
 */
@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserPasswordRequest {
    /* Object to Support both Password Reset and Change Password Requests */
    private String currentPassword;
    private String newPassword;
    private String password;

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
