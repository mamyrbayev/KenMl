package com.ereport.master.config.security.models.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthResponse {
    private String accessToken;
    private String tokenType;
    private long expiresIn;
    private String refreshToken;
    private String userName;
    private String userID;
    private String firstName;
    private String lastName;
    private String roles;
    private String rolesName;
    private String lastChangedPass;
    private String isChangePassword;
    private String issued;
    private String expires;

    @JsonProperty("access_token")
    public String getAccessToken() { return accessToken; }
    @JsonProperty("access_token")
    public void setAccessToken(String value) { this.accessToken = value; }

    @JsonProperty("token_type")
    public String getTokenType() { return tokenType; }
    @JsonProperty("token_type")
    public void setTokenType(String value) { this.tokenType = value; }

    @JsonProperty("expires_in")
    public long getExpiresIn() { return expiresIn; }
    @JsonProperty("expires_in")
    public void setExpiresIn(long value) { this.expiresIn = value; }

    @JsonProperty("refresh_token")
    public String getRefreshToken() { return refreshToken; }
    @JsonProperty("refresh_token")
    public void setRefreshToken(String value) { this.refreshToken = value; }

    @JsonProperty("userName")
    public String getUserName() { return userName; }
    @JsonProperty("userName")
    public void setUserName(String value) { this.userName = value; }

    @JsonProperty("userId")
    public String getUserID() { return userID; }
    @JsonProperty("userId")
    public void setUserID(String value) { this.userID = value; }

    @JsonProperty("firstName")
    public String getFirstName() { return firstName; }
    @JsonProperty("firstName")
    public void setFirstName(String value) { this.firstName = value; }

    @JsonProperty("lastName")
    public String getLastName() { return lastName; }
    @JsonProperty("lastName")
    public void setLastName(String value) { this.lastName = value; }

    @JsonProperty("roles")
    public String getRoles() { return roles; }
    @JsonProperty("roles")
    public void setRoles(String value) { this.roles = value; }

    @JsonProperty("rolesName")
    public String getRolesName() { return rolesName; }
    @JsonProperty("rolesName")
    public void setRolesName(String value) { this.rolesName = value; }

    @JsonProperty("LastChangedPass")
    public String getLastChangedPass() { return lastChangedPass; }
    @JsonProperty("LastChangedPass")
    public void setLastChangedPass(String value) { this.lastChangedPass = value; }

    @JsonProperty("isChangePassword")
    public String getIsChangePassword() { return isChangePassword; }
    @JsonProperty("isChangePassword")
    public void setIsChangePassword(String value) { this.isChangePassword = value; }

    @JsonProperty(".issued")
    public String getIssued() { return issued; }
    @JsonProperty(".issued")
    public void setIssued(String value) { this.issued = value; }

    @JsonProperty(".expires")
    public String getExpires() { return expires; }
    @JsonProperty(".expires")
    public void setExpires(String value) { this.expires = value; }
}

