package org.example.realtimechat.DTO;

import java.util.Date;

public class UserDTO {
    private Integer userId;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String role;
    private Date dateCreated;
    private boolean enabled;

    public UserDTO(String username, String password, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.firstName =  firstName;
        this.lastName = lastName;
    }

    public UserDTO(String username, String password, String firstName, String lastName, String role) {
        this.username = username;
        this.password = password;
        this.firstName =  firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public UserDTO() {
    }

    public Integer getUserId(){
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Date getDateCreated() {
        return dateCreated;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role='" + role + '\'' +
                ", dateCreated=" + dateCreated +
                ", enabled=" + enabled +
                '}';
    }
}
