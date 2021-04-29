package pl.xisp.shared.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pagination {
    private int page;
    private long pageSize;
    private long totalSize;
    private int totalPages;
}
