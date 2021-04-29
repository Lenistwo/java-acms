package pl.xisp.customer.document.service.plan;

import lombok.Data;

@Data
public class SpeedLimit {
    private float max;
    private float burst;
    private int burstTime;
}
