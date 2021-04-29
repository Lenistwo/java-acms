package pl.xisp.auth.document;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AdminAccount extends Account {
    private String firstname;
    private String surname;
}
