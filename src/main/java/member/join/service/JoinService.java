package member.join.service;

import member.join.dto.JoinDTO;
import member.join.dto.PageDTO;
import member.join.entity.JoinEntity;

import java.util.List;

public interface JoinService {
    public void write(JoinDTO joinDTO);                             //write
    Long register(JoinDTO joinDTO);                                 //write_proc
    public List<JoinDTO> findAll(int page);                         //모든회원 정보 가져오기
    public JoinDTO find(Long idx);                                  //뷰
    public JoinDTO modify(JoinDTO joinDTO);                         //수정
    public void delete(Long idx);                                   //삭제
    public PageDTO<JoinDTO, JoinEntity> getList(int page);          //페이지
    default JoinDTO entityToDto (JoinEntity entity) {               //entity를 DTO로 변환
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
