package pl.xisp.customer.controller.finance;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.xisp.customer.document.finance.Tax;
import pl.xisp.customer.service.finance.TaxService;
import pl.xisp.shared.response.PageableResponse;
import pl.xisp.shared.response.Response;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tax")
public class TaxController {

    private final TaxService taxService;


    @GetMapping
    public PageableResponse<List<Tax>> getAllTaxes(@RequestParam(required = false, defaultValue = "0") int page,
                                                   @RequestParam(required = false, defaultValue = "5") int limit,
                                                   @RequestParam(required = false, defaultValue = "") String search) {
        return taxService.getAll(page, limit, search);
    }

    @GetMapping("/{taxId}")
    public Response<Tax> getById(@PathVariable String taxId) {
        return taxService.getById(taxId);
    }

    @PostMapping
    public Response<String> createTax(Tax tax) {
        return taxService.createTax(tax);
    }

    @PutMapping
    public Response<Tax> updateTax(Tax tax) {
        return taxService.updateTax(tax);
    }

    @DeleteMapping("/{taxId}")
    public Response<Map<String, String>> deleteById(@PathVariable String taxId) {
        return taxService.deleteTaxById(taxId);
    }

}

