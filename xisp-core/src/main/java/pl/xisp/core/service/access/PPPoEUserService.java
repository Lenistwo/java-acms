package pl.xisp.core.service.access;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.xisp.core.documents.access.pppoe.PPPoEUser;
import pl.xisp.core.exception.PPoEUserNotFoundException;
import pl.xisp.core.repository.access.PPPoEUserRepository;
import pl.xisp.shared.response.PageableResponse;
import pl.xisp.shared.response.Pagination;
import pl.xisp.shared.response.Response;
import pl.xisp.shared.util.SearchUtil;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PPPoEUserService {
    private static final boolean STATUS = true;

    private final PPPoEUserRepository ppPoEUserRepository;
    private final SearchUtil<PPPoEUser> searchUtil;

    public PageableResponse<List<PPPoEUser>> getAll(int page, int limit, String search) {
        return searchUtil.prepareSearchResponse(page, limit, search, PPPoEUser.class).orElse(getAllPPPoEUsers(page, limit));
    }

    private PageableResponse<List<PPPoEUser>> getAllPPPoEUsers(int page, int limit) {
        var pageRequest = PageRequest.of(page, limit);
        var pageableResponse = ppPoEUserRepository.findAll(pageRequest);
        var pagination = new Pagination(page, pageableResponse.getSize(), pageableResponse.getTotalElements(), pageableResponse.getTotalPages());

        return new PageableResponse<>(STATUS, pageableResponse.getContent(), pagination);
    }

    public Response<PPPoEUser> getByName(String name) {
        return new Response<>(STATUS, ppPoEUserRepository.findByName(name).orElseThrow(PPoEUserNotFoundException::new));
    }

    public Response<PPPoEUser> getByAddress(String address) {
        return new Response<>(STATUS, ppPoEUserRepository.findByAddress(address).orElseThrow(PPoEUserNotFoundException::new));
    }

    public Response<PPPoEUser> updateByMac(String name) {
        return new Response<>(STATUS, ppPoEUserRepository.findByName(name).orElseThrow(PPoEUserNotFoundException::new));
    }
}
