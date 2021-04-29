package pl.xisp.shared.model;

import lombok.Data;

@Data
public class SmsApiResponse {
    private String id;
    private String code;
    private String phone_number;
    private String from;
}

