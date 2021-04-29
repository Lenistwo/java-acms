package pl.xisp.customer.controller.service;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.xisp.customer.document.finance.Surcharge;
import pl.xisp.customer.service.service.SurchargeService;
import pl.xisp.shared.response.PageableResponse;
import pl.xisp.shared.response.Response;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/surcharge")
public class SurchargeController {

    private final SurchargeService surchargeService;

    @GetMapping
    public PageableResponse<List<Surcharge>> getAllSurcharges(@RequestParam(required = false, defaultValue = "0") int page,
                                                              @RequestParam(required = false, defaultValue = "5") int limit,
                                                              @RequestParam(required = false, defaultValue = "") String search) {
        return surchargeService.getAllSurcharges(page, limit, search);
    }

    @GetMapping("/{surchargeId}")
    public Response<Surcharge> getSurchargeById(@PathVariable String surchargeId) {
        return surchargeService.getSurchargeById(surchargeId);
    }

    @PostMapping
    public Response<String> createSurcharge(@RequestBody Surcharge surcharge) {
        return surchargeService.createSurcharge(surcharge);
    }

    @PutMapping
    public Response<Map<String, String>> updateSurcharge(@RequestBody Surcharge surcharge) {
        return surchargeService.updateSurcharge(surcharge);
    }

    @DeleteMapping("/{surchargeId}")
    public Response<Map<String, String>> deleteSurchargeById(@PathVariable String surchargeId) {
        return surchargeService.deleteSurcharge(surchargeId);
    }
}
