package pl.xisp.customer.document.service.plan;

import lombok.Data;

@Data
public class Bonus {
    private Duration duration;

    private SpeedLimit downloadSpeed;
    private SpeedLimit uploadSpeed;

    private int price;
}
