package pl.xisp.auth.document;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class CustomerAccount extends Account {
    private String firstname;
    private String surname;

    private List<String> customers = new ArrayList<>();
}
