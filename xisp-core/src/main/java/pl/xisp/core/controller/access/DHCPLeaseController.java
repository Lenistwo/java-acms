package pl.xisp.core.controller.access;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.xisp.core.documents.access.dhcp.DHCPLease;
import pl.xisp.core.service.access.DHCPLeaseService;
import pl.xisp.shared.response.PageableResponse;
import pl.xisp.shared.response.Response;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/access/dhcp_leases")
public class DHCPLeaseController {

    private final DHCPLeaseService dhcpLeaseService;

    @GetMapping
    public PageableResponse<List<DHCPLease>> getAllDHCP(@RequestParam(required = false, defaultValue = "0") int page,
                                                        @RequestParam(required = false, defaultValue = "5") int limit,
                                                        @RequestParam(required = false, defaultValue = "") String search) {
        return dhcpLeaseService.getAll(page, limit, search);
    }

    @GetMapping("/{mac}")
    public Response<DHCPLease> getByMac(@PathVariable String mac) {
        return dhcpLeaseService.getByMac(mac);
    }

    @GetMapping("/address/{address}")
    public Response<DHCPLease> getByAddress(@PathVariable String address) {
        return dhcpLeaseService.getByAddress(address);
    }

    @PutMapping("/{mac}")
    public Response<DHCPLease> updateByMac(@PathVariable String mac) {
        return dhcpLeaseService.updateByMac(mac);
    }
}
