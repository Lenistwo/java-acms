package pl.xisp.customer.service.service;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import pl.xisp.customer.document.service.Service;
import pl.xisp.customer.exception.ServiceNotFoundException;
import pl.xisp.customer.repository.service.ServiceRepository;
import pl.xisp.shared.response.PageableResponse;
import pl.xisp.shared.response.Pagination;
import pl.xisp.shared.response.Response;
import pl.xisp.shared.util.SearchUtil;

import java.time.LocalDateTime;
import java.util.*;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class UserServiceService {
    private static final boolean STATUS = true;

    private final ServiceRepository serviceRepository;
    private final SearchUtil<Service> searchUtil;

    public PageableResponse<List<Service>> getAll(int page, int limit, String search) {
        return Optional.ofNullable(searchUtil.prepareSearchResponse(page, limit, search, Service.class)).orElse(findAllServices(page, limit));
    }

    private PageableResponse<List<Service>> findAllServices(int page, int limit) {
        var pageRequest = PageRequest.of(page, limit);
        var pageableResponse = serviceRepository.findAll(pageRequest);
        var pagination = new Pagination(page, pageableResponse.getSize(), pageableResponse.getTotalElements(), pageableResponse.getTotalPages());

        return new PageableResponse<>(STATUS, pageableResponse.getContent(), pagination);
    }

    public Response<Map<String, String>> deleteService(String serviceId) {
        if (!serviceRepository.existsById(serviceId)) throw new ServiceNotFoundException();
        serviceRepository.deleteById(serviceId);
        return new Response<>(STATUS, Collections.emptyMap());
    }


    public Response<Service> getServiceById(String serviceId) {
        return new Response<>(STATUS, serviceRepository.findById(serviceId).orElseThrow(ServiceNotFoundException::new));
    }

    public Response<String> createService(Service service) {
        service.setId(UUID.randomUUID().toString());
        service.setCreatedAt(LocalDateTime.now());
        serviceRepository.save(service);
        return new Response<>(STATUS, service.getId());
    }

    public Response<Map<String, String>> updateService(Service service) {
        if (!serviceRepository.existsById(service.getId())) throw new ServiceNotFoundException();
        serviceRepository.save(service);
        return new Response<>(STATUS, Collections.emptyMap());
    }
}
