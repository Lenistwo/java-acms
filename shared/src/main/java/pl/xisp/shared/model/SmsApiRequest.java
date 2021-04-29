package pl.xisp.shared.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SmsApiRequest {
    private String phone_number;
    private String from;
    private String content;
    private String fast;
}
