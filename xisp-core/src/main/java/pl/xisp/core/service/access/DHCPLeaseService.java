package pl.xisp.core.service.access;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.xisp.core.documents.access.dhcp.DHCPLease;
import pl.xisp.core.exception.DHCPLeaseNotFoundException;
import pl.xisp.core.repository.access.DHCPLeaseRepository;
import pl.xisp.shared.response.PageableResponse;
import pl.xisp.shared.response.Pagination;
import pl.xisp.shared.response.Response;
import pl.xisp.shared.util.SearchUtil;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DHCPLeaseService {
    private static final boolean STATUS = true;

    private final DHCPLeaseRepository repository;
    private final SearchUtil<DHCPLease> searchUtil;

    public PageableResponse<List<DHCPLease>> getAll(int page, int limit, String search) {
        return Optional.ofNullable(searchUtil.prepareSearchResponse(page, limit, search, DHCPLease.class))
                .orElse(getAllDHCPLeases(page, limit));
    }

    private PageableResponse<List<DHCPLease>> getAllDHCPLeases(int page, int limit) {
        var pageRequest = PageRequest.of(page, limit);
        var pageableResponse = repository.findAll(pageRequest);
        var pagination = new Pagination(page, pageableResponse.getSize(), pageableResponse.getTotalElements(),
                                        pageableResponse.getTotalPages());


        return new PageableResponse<>(STATUS, pageableResponse.getContent(), pagination);
    }

    public Response<DHCPLease> getByMac(String mac) {
        return new Response<>(STATUS, repository.findByMac(mac).orElseThrow(DHCPLeaseNotFoundException::new));
    }

    public Response<DHCPLease> getByAddress(String address) {
        return new Response<>(STATUS, repository.findByAddress(address).orElseThrow(DHCPLeaseNotFoundException::new));
    }

    public Response<DHCPLease> updateByMac(String mac) {
        return new Response<>(STATUS, repository.findByMac(mac).orElseThrow(DHCPLeaseNotFoundException::new));
    }
}
