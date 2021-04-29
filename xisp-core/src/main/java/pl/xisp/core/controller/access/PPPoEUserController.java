package pl.xisp.core.controller.access;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.xisp.core.documents.access.pppoe.PPPoEUser;
import pl.xisp.core.service.access.PPPoEUserService;
import pl.xisp.shared.response.PageableResponse;
import pl.xisp.shared.response.Response;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/access/pppoe_users")
public class PPPoEUserController {

    private final PPPoEUserService ppPoEUserService;

    @GetMapping
    public PageableResponse<List<PPPoEUser>> getAllPPPoEUsers(@RequestParam(required = false, defaultValue = "0") int page,
                                                              @RequestParam(required = false, defaultValue = "5") int limit,
                                                              @RequestParam(required = false, defaultValue = "") String search) {
        return ppPoEUserService.getAll(page, limit, search);
    }

    @GetMapping("/{name}")
    public Response<PPPoEUser> getByName(@PathVariable String name) {
        return ppPoEUserService.getByName(name);
    }

    @GetMapping("/address/{address}")
    public Response<PPPoEUser> getByAddress(@PathVariable String address) {
        return ppPoEUserService.getByAddress(address);
    }

    @PutMapping("/{name}")
    public Response<PPPoEUser> updateByMac(@PathVariable String name) {
        return ppPoEUserService.updateByMac(name);
    }
}
