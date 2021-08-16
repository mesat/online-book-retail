package org.openreading.readingisgood.security;

/**
 * @author Muhammet Sakarya
 * created at 8/14/2021
 */
public class User {

    private Integer id;
    private String username;
    private String password;
    private UserType userType;

  /**
   * @param id
   * @param username
   * @param password
   * @param userType
   */
  public User(Integer id, String username, String password, UserType userType) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.userType = userType;
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

    public String getPassword() {
        return password;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public UserType getUserType() {
        return userType;
    }
}
