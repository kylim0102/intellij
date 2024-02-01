package member.join.entity;

import jakarta.persistence.*;
import lombok.*;
import member.join.dto.JoinDTO;

@Entity
@Getter
@Table(name ="member")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JoinEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "a_member_seq")
    @SequenceGenerator(name = "a_member_seq", sequenceName = "a_member_seq", allocationSize = 1)
    @Column(name = "idx")
    private Long idx;

    @Column(name = "name")
    private String name;

    @Column(name = "id")
    private String id;

    @Column(name = "password")
    private String password;

    @Column(name = "age")
    private int age;

    public JoinDTO toDTO() {
        return JoinDTO.builder()
                .idx(idx)
                .name(name)
                .id(id)
                .password(password)
                .age(age)
                .build();
    }

    public void modify(String name, int age, String id, String password) {
        this.name = name;
        this.age = age;
        this.id = id;
        this.password = password;
    }
}