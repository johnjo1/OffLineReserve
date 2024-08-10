package org.chulgang.hrd.users.dto;

import java.time.LocalDateTime;

public class UsersSignUpRequest {
    private long id;
    private String email;
    private String username;
    private String password;
    private String full_name;
    private String phone;
    private LocalDateTime create_at;
    private LocalDateTime modified_at;

}
