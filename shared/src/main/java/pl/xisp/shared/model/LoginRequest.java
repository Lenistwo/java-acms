package pl.xisp.shared.model;

import lombok.Data;

@Data
public class LoginRequest {
    private int code;
    private String mail;
    private String password;

    private String address;
    private String useragent;
}
