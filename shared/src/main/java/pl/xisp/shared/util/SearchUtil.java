package pl.xisp.shared.util;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import pl.xisp.shared.response.PageableResponse;
import pl.xisp.shared.response.Pagination;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchUtil<T> {
    private static final boolean STATUS = true;
    private static final int FIRST_PAGE = 0;

    private final Gson gson;
    private final MongoTemplate template;

    public Optional<PageableResponse<List<T>>> prepareSearchResponse(int page, int limit, String search, Class<T> returnType) {

        @SuppressWarnings("unchecked") Map<String, String> searchParams = gson.fromJson(URLDecoder.decode(search, StandardCharsets.UTF_8), Map.class);

        var query = prepareQuery(searchParams);

        if (query.isEmpty()) {
            return Optional.empty();
        }

        var queryResult = template.find(query.get(), returnType);

        List<T> response = null;

        if (page == FIRST_PAGE) {
            response = queryResult.stream().limit(limit).collect(Collectors.toList());
        }

        if (page > FIRST_PAGE) {
            response = queryResult.stream().skip((long) limit * page).limit(limit).collect(Collectors.toList());
        }

        var totalPageAmount = calculateTotalPageAmount(queryResult, limit);
        var pagination = new Pagination(page, limit, queryResult.size(), totalPageAmount);

        return Optional.of(new PageableResponse<>(STATUS, response, pagination));
    }

    private int calculateTotalPageAmount(List<?> list, int limit) {
        return list.size() % limit == 0 ? list.size() / limit : list.size() / limit + 1;
    }

    private Optional<Query> prepareQuery(Map<String, String> map) {
        Criteria criteria = null;

        var addWhere = true;

        if (map == null) {
            return Optional.empty();
        }

        for (var key : map.keySet()) {

            if (map.get(key) == null) {
                continue;
            }

            if (addWhere) {
                criteria = Criteria.where(key).regex(map.get(key));
                addWhere = false;
                continue;
            }
            criteria.and(key).regex(map.get(key));
        }

        if (criteria == null){
            return Optional.empty();
        }

        return Optional.of(new Query(criteria)) ;
    }
}
