package pl.xisp.shared.model;

import lombok.Data;

@Data
public class VerifyCredentialsRequest {
    private String mail;
    private String password;
}
