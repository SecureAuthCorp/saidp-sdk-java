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
    private char[] currentPassword = new char[0];
    private char[] newPassword = new char[0];
    private char[] password = new char[0];

    public String getCurrentPassword() {
        return new String(currentPassword);
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword.toCharArray();
    }

    public String getNewPassword() {
        return new String(newPassword);
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword.toCharArray();
    }

    public String getPassword() {
        return new String(password);
    }

    public void setPassword(String password) {
        this.password = password.toCharArray();
    }
}