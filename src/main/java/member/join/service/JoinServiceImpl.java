package member.join.service;

import lombok.RequiredArgsConstructor;
import member.join.dto.JoinDTO;
import member.join.dto.PageDTO;
import member.join.entity.JoinEntity;
import member.join.repository.JoinRepository;
import org.apache.catalina.authenticator.SavedRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JoinServiceImpl implements JoinService {
    @Autowired
    private final JoinRepository joinRepository;

    @Override
    public Long register(JoinDTO joinDTO) {
        return null;
    }

    @Override
    public void write(JoinDTO joinDTO) {
        JoinEntity joinEntity = joinDTO.joinEntity();
        joinRepository.save(joinEntity);
    }

    @Override
    public List<JoinDTO> findAll(int page) {
        List<JoinEntity> result = joinRepository.findAll();
        List<JoinDTO> toReturn = new ArrayList<JoinDTO>();
        for (JoinEntity je : result) {
            toReturn.add(entityToDto(je));
        }
        return toReturn;
    }

    public PageDTO<JoinDTO, JoinEntity> getList(int page) {
        Sort sort = Sort.by("idx").descending();
        Pageable pageable = PageRequest.of(page-1, 5, sort);
        Page<JoinEntity> result = joinRepository.findAll(pageable);
        List<JoinEntity> result2 = result.getContent();
        List<JoinDTO> list = new ArrayList<JoinDTO>();
        for (int i=0; i<result2.size(); i++) {
            list.add(entityToDto(result2.get(i)));
        }

        return new PageDTO<JoinDTO, JoinEntity>(result, list);
    }

    @Override
    public JoinDTO find(Long idx) {
        JoinEntity view = joinRepository.findById(idx).orElse(null);
        JoinDTO joinDTO = view.toDTO();
        return joinDTO;
    }

    @Override
    public JoinDTO modify(JoinDTO joinDTO) {
        JoinEntity modify = joinRepository.findById(joinDTO.getIdx()).orElse(null);
        modify.modify(joinDTO.getName(), joinDTO.getAge(), joinDTO.getId(), joinDTO.getPassword());
        JoinDTO view = joinRepository.save(modify).toDTO();
        return view;
    }

    @Override
    public void delete(Long idx) {
        JoinEntity je = joinRepository.findById(idx).orElse(null);
        joinRepository.delete(je);
    }


}
