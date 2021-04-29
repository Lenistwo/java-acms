package pl.xisp.customer.service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.xisp.customer.document.service.plan.ServicePlan;
import pl.xisp.customer.exception.ServicePlanNotFoundException;
import pl.xisp.customer.repository.service.ServicePlanRepository;
import pl.xisp.shared.response.PageableResponse;
import pl.xisp.shared.response.Pagination;
import pl.xisp.shared.response.Response;
import pl.xisp.shared.util.SearchUtil;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ServicePlanService {
    private static final boolean STATUS = true;

    private final ServicePlanRepository servicePlanRepository;
    private final SearchUtil<ServicePlan> searchUtil;

    public PageableResponse<List<ServicePlan>> getServicePlans(int page, int limit, String search) {
        return Optional.ofNullable(searchUtil.prepareSearchResponse(page, limit, search, ServicePlan.class))
                .orElse(findServicePlans(page, limit));
    }

    private PageableResponse<List<ServicePlan>> findServicePlans(int page, int limit) {
        var pageRequest = PageRequest.of(page, limit);
        var pageableResponse = servicePlanRepository.findAll(pageRequest);
        var pagination = new Pagination(page, pageableResponse.getSize(), pageableResponse.getTotalElements(),
                                        pageableResponse.getTotalPages());

        return new PageableResponse<>(STATUS, pageableResponse.getContent(), pagination);
    }

    public Response<ServicePlan> getServicePlanById(String servicePlanId) {
        return new Response<>(STATUS, servicePlanRepository.findById(servicePlanId)
                .orElseThrow(ServicePlanNotFoundException::new));
    }

    public Response<String> createServicePlan(ServicePlan servicePlan) {
        servicePlan.setId(UUID.randomUUID().toString());
        servicePlanRepository.save(servicePlan);
        return new Response<>(STATUS, servicePlan.getId());
    }

    public Response<Map<String, String>> updateServicePlan(ServicePlan servicePlan) {
        if (servicePlanRepository.existsById(servicePlan.getId())) throw new ServicePlanNotFoundException();
        servicePlanRepository.save(servicePlan);
        return new Response<>(STATUS, Collections.emptyMap());
    }

    public Response<Map<String, String>> deleteServicePlan(String servicePlanId) {
        if (servicePlanRepository.existsById(servicePlanId)) throw new ServicePlanNotFoundException();
        servicePlanRepository.deleteById(servicePlanId);
        return new Response<>(STATUS, Collections.emptyMap());
    }
}
