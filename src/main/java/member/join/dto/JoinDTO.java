package member.join.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import member.join.entity.JoinEntity;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JoinDTO {
    private Long idx;
    private String name;
    private String id;
    private String password;
    private int age;

    public JoinEntity joinEntity() {
        return JoinEntity.builder()
                .idx(idx)
                .name(name)
                .id(id)
                .password(password)
                .age(age)
                .build();
    }

}
