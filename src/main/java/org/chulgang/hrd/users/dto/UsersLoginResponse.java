package org.chulgang.hrd.users.dto;

import java.time.LocalDateTime;

public class UsersLoginResponse {
    private long id;
    private String email;
    private String username;
    private String password;
    private String full_name;
    private String phone;
    private String role;
    private LocalDateTime create_at;
    private LocalDateTime modified_at;

    public UsersLoginResponse(long id, String email, String username, String password, String full_name, String phone, String role, LocalDateTime create_at, LocalDateTime modified_at) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.full_name = full_name;
        this.phone = phone;
        this.role = role;
        this.create_at = create_at;
        this.modified_at = modified_at;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public UsersLoginResponse(){}


    public LocalDateTime getModified_at() {
        return modified_at;
    }

    public void setModified_at(LocalDateTime modified_at) {
        this.modified_at = modified_at;
    }

    public LocalDateTime getCreate_at() {
        return create_at;
    }

    public void setCreate_at(LocalDateTime create_at) {
        this.create_at = create_at;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
