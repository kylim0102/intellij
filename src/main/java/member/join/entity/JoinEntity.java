package member.join.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "member")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JoinEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "a_member_seq")
    @SequenceGenerator(name = "a_member_seq", sequenceName = "a_member_seq", allocationSize = 1)
    @Column (name = "idx")
    private Long idx;

    @Column (name = "name")
    private String name;

    @Column (name = "id")
    private String id;

    @Column (name = "password")
    private String password;

    @Column (name = "age")
    private int age;
}


