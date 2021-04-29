package pl.xisp.customer.service.finance;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.xisp.customer.document.finance.Tax;
import pl.xisp.customer.exception.TaxNotFoundException;
import pl.xisp.customer.repository.finance.TaxRepository;
import pl.xisp.shared.response.PageableResponse;
import pl.xisp.shared.response.Pagination;
import pl.xisp.shared.response.Response;
import pl.xisp.shared.util.SearchUtil;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class TaxService {
    private static final boolean STATUS = true;

    private final TaxRepository taxRepository;
    private final SearchUtil<Tax> searchUtil;

    public PageableResponse<List<Tax>> getAll(int page, int limit, String search) {
        return Optional.ofNullable(searchUtil.prepareSearchResponse(page, limit, search, Tax.class)).orElse(findAllTaxes(page, limit));
    }

    private PageableResponse<List<Tax>> findAllTaxes(int page, int limit) {
        var pageRequest = PageRequest.of(page, limit);
        var pageableResponse = taxRepository.findAll(pageRequest);
        var pagination = new Pagination(page, pageableResponse.getSize(), pageableResponse.getTotalElements(), pageableResponse.getTotalPages());

        return new PageableResponse<>(STATUS, pageableResponse.getContent(), pagination);
    }

    public Response<Tax> getById(String taxId) {
        var tax = taxRepository.findById(taxId).orElseThrow(TaxNotFoundException::new);
        return new Response<>(STATUS, tax);
    }

    public Response<String> createTax(Tax tax) {
        tax.setId(UUID.randomUUID().toString());
        tax.setCreatedAt(LocalDateTime.now());
        taxRepository.save(tax);
        return new Response<>(STATUS, tax.getId());
    }

    public Response<Tax> updateTax(Tax tax) {
        if (!taxRepository.existsById(tax.getId())) {
            throw new TaxNotFoundException();
        }
        taxRepository.save(tax);
        return new Response<>(STATUS, tax);
    }

    public Response<Map<String, String>> deleteTaxById(String id) {
        if (!taxRepository.existsById(id)) {
            throw new TaxNotFoundException();
        }
        taxRepository.deleteById(id);
        return new Response<>(STATUS, Collections.emptyMap());
    }
}
