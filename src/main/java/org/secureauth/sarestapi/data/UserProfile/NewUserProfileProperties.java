package org.secureauth.sarestapi.data.UserProfile;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;


/**
 * Created by rrowcliffe on 5/1/16.
 */
@XmlRootElement(name="properties")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NewUserProfileProperties {

    private String firstName;
    private String lastName;
    private String phone1;
    private String phone2;
    private String phone3;
    private String phone4;
    private String email1;
    private String email2;
    private String email3;
    private String email4;
    private String auxId1;
    private String auxId2;
    private String auxId3;
    private String auxId4;
    private String auxId5;
    private String auxId6;
    private String auxId7;
    private String auxId8;
    private String auxId9;
    private String auxId10;
    private String pinHash;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getPhone3() {
        return phone3;
    }

    public void setPhone3(String phone3) {
        this.phone3 = phone3;
    }

    public String getPhone4() {
        return phone4;
    }

    public void setPhone4(String phone4) {
        this.phone4 = phone4;
    }

    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String getEmail3() {
        return email3;
    }

    public void setEmail3(String email3) {
        this.email3 = email3;
    }

    public String getEmail4() {
        return email4;
    }

    public void setEmail4(String email4) {
        this.email4 = email4;
    }

    public String getAuxId1() {
        return auxId1;
    }

    public void setAuxId1(String auxId1) {
        this.auxId1 = auxId1;
    }

    public String getAuxId2() {
        return auxId2;
    }

    public void setAuxId2(String auxId2) {
        this.auxId2 = auxId2;
    }

    public String getAuxId3() {
        return auxId3;
    }

    public void setAuxId3(String auxId3) {
        this.auxId3 = auxId3;
    }

    public String getAuxId4() {
        return auxId4;
    }

    public void setAuxId4(String auxId4) {
        this.auxId4 = auxId4;
    }

    public String getAuxId5() {
        return auxId5;
    }

    public void setAuxId5(String auxId5) {
        this.auxId5 = auxId5;
    }

    public String getAuxId6() {
        return auxId6;
    }

    public void setAuxId6(String auxId6) {
        this.auxId6 = auxId6;
    }

    public String getAuxId7() {
        return auxId7;
    }

    public void setAuxId7(String auxId7) {
        this.auxId7 = auxId7;
    }

    public String getAuxId8() {
        return auxId8;
    }

    public void setAuxId8(String auxId8) {
        this.auxId8 = auxId8;
    }

    public String getAuxId9() {
        return auxId9;
    }

    public void setAuxId9(String auxId9) {
        this.auxId9 = auxId9;
    }

    public String getAuxId10() {
        return auxId10;
    }

    public void setAuxId10(String auxId10) {
        this.auxId10 = auxId10;
    }

    public String getPinHash() {
        return pinHash;
    }

    public void setPinHash(String pinHash) {
        this.pinHash = pinHash;
    }
}
