package com.info7255.ebl.entity;

import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.UUID;

@RedisHash("User")
public class User implements Serializable {

    String id;
    String firstName;
    String lastName;
    String emailAddress;
    String userType;
    String companyName;
    String websiteLink;
    int approxTEUs;
    String phoneNumber;

    public User() {
        id = String.valueOf(UUID.randomUUID());
    }

    public User(String firstName, String lastName, String emailAddress, String userType, String companyName, String websiteLink, int approxTEUs, String phoneNumber){
        id = String.valueOf(UUID.randomUUID());
        firstName = this.firstName;
        lastName = this.lastName;
        emailAddress = this.emailAddress;
        userType = this.userType;
        companyName = this.companyName;
        websiteLink = this.websiteLink;
        approxTEUs = this.approxTEUs;
        phoneNumber = this.phoneNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getWebsiteLink() {
        return websiteLink;
    }

    public void setWebsiteLink(String websiteLink) {
        this.websiteLink = websiteLink;
    }

    public int getApproxTEUs() {
        return approxTEUs;
    }

    public void setApproxTEUs(int approxTEUs) {
        this.approxTEUs = approxTEUs;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
