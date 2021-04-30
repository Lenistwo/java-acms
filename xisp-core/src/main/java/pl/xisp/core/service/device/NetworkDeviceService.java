package pl.xisp.core.service.device;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.xisp.core.documents.device.NetworkDevice;
import pl.xisp.core.exception.NetworkDeviceNotFoundException;
import pl.xisp.core.repository.device.NetworkDeviceRepository;
import pl.xisp.shared.response.PageableResponse;
import pl.xisp.shared.response.Pagination;
import pl.xisp.shared.response.Response;
import pl.xisp.shared.util.SearchUtil;

import java.util.*;

@Service
@RequiredArgsConstructor
public class NetworkDeviceService {
    private static final boolean STATUS = true;

    private final NetworkDeviceRepository networkDeviceRepository;
    private final SearchUtil<NetworkDevice> searchUtil;

    public PageableResponse<List<NetworkDevice>> getAll(int page, int limit, String search) {
        return searchUtil.prepareSearchResponse(page, limit, search, NetworkDevice.class).orElse(getAllNetworkDevices(page, limit));
    }

    private PageableResponse<List<NetworkDevice>> getAllNetworkDevices(int page, int limit) {
        var pageable = PageRequest.of(page, limit);
        var pageableResponse = networkDeviceRepository.findAll(pageable);
        var pagination = new Pagination(page, pageableResponse.getSize(), pageableResponse.getTotalElements(),
                                        pageableResponse.getTotalPages());

        return new PageableResponse<>(STATUS, pageableResponse.getContent(), pagination);
    }

    public Response<NetworkDevice> getNetworkDeviceById(String id) {
        return new Response<>(STATUS,
                              networkDeviceRepository.findById(id).orElseThrow(NetworkDeviceNotFoundException::new));
    }


    public Response<NetworkDevice> saveNetworkDevice(NetworkDevice networkDevice) {
        networkDevice.setId(UUID.randomUUID().toString());
        networkDeviceRepository.save(networkDevice);

        return new Response<>(STATUS, networkDevice);
    }

    public Response<Map<String, String>> updateNetworkDevice(NetworkDevice networkDevice) {

        if (networkDeviceRepository.existsById(networkDevice.getId())) throw new NetworkDeviceNotFoundException();

        networkDeviceRepository.insert(networkDevice);
        return new Response<>(STATUS, Collections.emptyMap());
    }

    public Response<Map<String, String>> deleteNetworkDevice(String deviceId) {
        if (networkDeviceRepository.existsById(deviceId)) throw new NetworkDeviceNotFoundException();

        networkDeviceRepository.deleteById(deviceId);
        return new Response<>(STATUS, Collections.emptyMap());
    }
}
