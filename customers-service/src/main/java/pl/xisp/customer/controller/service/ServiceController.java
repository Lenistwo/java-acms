package pl.xisp.customer.controller.service;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.xisp.customer.document.service.Service;
import pl.xisp.customer.service.service.UserServiceService;
import pl.xisp.shared.response.PageableResponse;
import pl.xisp.shared.response.Response;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/service")
public class ServiceController {

    private final UserServiceService userServiceService;

    @GetMapping
    public PageableResponse<List<Service>> getAllServices(@RequestParam(required = false, defaultValue = "0") int page,
                                                          @RequestParam(required = false, defaultValue = "5") int limit,
                                                          @RequestParam(required = false, defaultValue = "") String search) {
        return userServiceService.getAll(page, limit, search);
    }

    @GetMapping("/{serviceId}")
    public Response<Service> getServiceById(@PathVariable String serviceId) {
        return userServiceService.getServiceById(serviceId);
    }

    @PostMapping
    public Response<String> createService(@RequestBody Service service) {
        return userServiceService.createService(service);
    }

    @PutMapping
    public Response<Map<String, String>> updateService(@RequestBody Service service) {
        return userServiceService.updateService(service);
    }

    @DeleteMapping("/{serviceId}")
    public Response<Map<String, String>> deleteServiceById(@PathVariable String serviceId) {
        return userServiceService.deleteService(serviceId);
    }
}
