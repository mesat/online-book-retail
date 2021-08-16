package org.openreading.readingisgood.security;

/**
 * @author Muhammet Sakarya
 * created at 8/14/2021
 */
public class UserPrincipal {
    private Integer id;
    private String username;
    private UserType userType;

    public UserPrincipal(Integer id, String username, UserType userType) {
        this.setId(id);
        this.setUsername(username);
        this.setUserType(userType);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
