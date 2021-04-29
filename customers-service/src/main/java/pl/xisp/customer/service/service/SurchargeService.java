package pl.xisp.customer.service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.xisp.customer.document.finance.Surcharge;
import pl.xisp.customer.exception.SurchargeNotFoundException;
import pl.xisp.customer.repository.service.SurchargeRepository;
import pl.xisp.shared.response.PageableResponse;
import pl.xisp.shared.response.Pagination;
import pl.xisp.shared.response.Response;
import pl.xisp.shared.util.SearchUtil;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class SurchargeService {

    private static final boolean STATUS = true;

    private final SurchargeRepository surchargeRepository;
    private final SearchUtil<Surcharge> searchUtil;

    public PageableResponse<List<Surcharge>> getAllSurcharges(int page, int limit, String search) {
        return Optional.ofNullable(searchUtil.prepareSearchResponse(page, limit, search, Surcharge.class)).orElse(findAllSurcharges(page, limit));
    }

    private PageableResponse<List<Surcharge>> findAllSurcharges(int page, int limit) {
        var pageRequest = PageRequest.of(page, limit);
        var pageableResponse = surchargeRepository.findAll(pageRequest);
        var pagination = new Pagination(page, pageableResponse.getSize(), pageableResponse.getTotalElements(), pageableResponse.getTotalPages());

        return new PageableResponse<>(STATUS, pageableResponse.getContent(), pagination);
    }

    public Response<Surcharge> getSurchargeById(String surchargeId) {
        return new Response<>(STATUS, surchargeRepository.findById(surchargeId).orElseThrow(SurchargeNotFoundException::new));
    }

    public Response<String> createSurcharge(Surcharge surcharge) {
        surcharge.setId(UUID.randomUUID().toString());
        surcharge.setCreatedAt(LocalDateTime.now());
        surchargeRepository.save(surcharge);
        return new Response<>(STATUS, surcharge.getId());
    }

    public Response<Map<String, String>> updateSurcharge(Surcharge surcharge) {
        if (surchargeRepository.existsById(surcharge.getId())) throw new SurchargeNotFoundException();
        surchargeRepository.save(surcharge);
        return new Response<>(STATUS, Collections.emptyMap());
    }

    public Response<Map<String, String>> deleteSurcharge(String surchargeId) {
        if (surchargeRepository.existsById(surchargeId)) throw new SurchargeNotFoundException();
        surchargeRepository.deleteById(surchargeId);
        return new Response<>(STATUS, Collections.emptyMap());
    }
}
