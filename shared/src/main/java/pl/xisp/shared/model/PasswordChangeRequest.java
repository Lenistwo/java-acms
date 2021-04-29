package pl.xisp.shared.model;

import lombok.Data;

@Data
public class PasswordChangeRequest {
    private String remindId;
    private String password;
}
