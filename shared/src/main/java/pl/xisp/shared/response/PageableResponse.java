package pl.xisp.shared.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageableResponse<T> {
    private boolean status;
    private T data;
    private Pagination pagination;
}
