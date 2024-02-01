package member.join.dto;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class PageDTO<JoinDTO, JoinEntity> {
    private List<JoinDTO> dtoList;
    private int totalPage;
    private int page;
    private int size;
    private int start, end;
    private boolean prev, next;
    private List<Integer> pageList;
    /*public PageDTO(Page<JoinEntity> result, Function<JoinEntity, JoinDTO> fn) {
        dtoList = result.stream().map(fn).collect(Collectors.toList());
        totalPage = result.getTotalPages();
        makePageList(result.getPageable());
    }*/
    public PageDTO(Page<JoinEntity> pageObj, List<JoinDTO> dto) {
        this.dtoList = dto;
        this.totalPage = pageObj.getTotalPages();
        this.page = pageObj.getNumber() + 1;
        this.size = pageObj.getSize();

        int tempEnd = (int)(Math.ceil(page/5.0))*5;
        start = tempEnd - 4;
        prev = start > 1;
        end = totalPage > tempEnd ? tempEnd : totalPage;
        next = totalPage > tempEnd;
        pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
    }
    /*private void makePageList(Pageable pageable) {
        this.page = pageable.getPageNumber() + 1;
        this.size = pageable.getPageSize();


    }*/
}
