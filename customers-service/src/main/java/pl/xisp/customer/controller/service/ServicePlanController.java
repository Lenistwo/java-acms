package pl.xisp.customer.controller.service;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.xisp.customer.document.service.plan.ServicePlan;
import pl.xisp.customer.service.service.ServicePlanService;
import pl.xisp.shared.response.PageableResponse;
import pl.xisp.shared.response.Response;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/service-plan")
public class ServicePlanController {

    private final ServicePlanService servicePlanService;

    @GetMapping
    public PageableResponse<List<ServicePlan>> getAllServicePlans(@RequestParam(required = false, defaultValue = "0") int page,
                                                                  @RequestParam(required = false, defaultValue = "5") int limit,
                                                                  @RequestParam(required = false, defaultValue = "") String search) {
        return servicePlanService.getServicePlans(page, limit, search);
    }

    @GetMapping("/{servicePlanId}")
    public Response<ServicePlan> getServicePlanById(@PathVariable String servicePlanId) {
        return servicePlanService.getServicePlanById(servicePlanId);
    }

    @PostMapping
    public Response<String> createServicePlan(@RequestBody ServicePlan servicePlan) {
        return servicePlanService.createServicePlan(servicePlan);
    }

    @PutMapping
    public Response<Map<String, String>> updateServicePlan(@RequestBody ServicePlan servicePlan) {
        return servicePlanService.updateServicePlan(servicePlan);
    }

    @DeleteMapping("/{servicePlanId}")
    public Response<Map<String, String>> deleteServicePlanById(@PathVariable String servicePlanId) {
        return servicePlanService.deleteServicePlan(servicePlanId);
    }

}
