package pl.xisp.core.controller.access;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.xisp.core.documents.device.NetworkDevice;
import pl.xisp.core.service.device.NetworkDeviceService;
import pl.xisp.shared.response.PageableResponse;
import pl.xisp.shared.response.Response;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/network-device")
public class NetworkDeviceController {

    private final NetworkDeviceService networkDeviceService;

    @GetMapping
    public PageableResponse<List<NetworkDevice>> getAllDevices(@RequestParam(required = false, defaultValue = "0") int page,
                                                               @RequestParam(required = false, defaultValue = "5") int limit,
                                                               @RequestParam(required = false, defaultValue = "") String search) {
        return networkDeviceService.getAll(page, limit, search);
    }

    @GetMapping("/{deviceId}")
    public Response<NetworkDevice> getById(@PathVariable String deviceId) {
        return networkDeviceService.getNetworkDeviceById(deviceId);
    }

    @PostMapping
    public Response<NetworkDevice> addDevice(@RequestBody NetworkDevice networkDeviceDto) {
        return networkDeviceService.saveNetworkDevice(networkDeviceDto);
    }

    @PutMapping
    public Response<Map<String, String>> updateDevice(@RequestBody NetworkDevice networkDevice) {
        return networkDeviceService.updateNetworkDevice(networkDevice);
    }

    @DeleteMapping("/{deviceId}")
    public Response<Map<String, String>> deleteDevice(@PathVariable String deviceId) {
        return networkDeviceService.deleteNetworkDevice(deviceId);
    }
}
