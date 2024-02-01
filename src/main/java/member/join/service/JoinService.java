package member.join.service;

import member.join.dto.JoinDTO;
import member.join.dto.PageDTO;
import member.join.entity.JoinEntity;

import java.awt.print.Pageable;
import java.util.List;

public interface JoinService {
    public void write(JoinDTO joinDTO);
    public List<JoinDTO> findAll(int page);
    public JoinDTO find(Long idx);
    public JoinDTO modify(JoinDTO joinDTO);
    public void delete(Long idx);
    Long register(JoinDTO joinDTO);
    public PageDTO<JoinDTO, JoinEntity> getList(int page);
    default JoinDTO entityToDto (JoinEntity entity) {
        JoinDTO dto = JoinDTO.builder()
                .idx(entity.getIdx())
                .name(entity.getName())
                .age(entity.getAge())
                .id(entity.getId())
                .password(entity.getPassword())
                .build();
        return dto;
    }
}
