package member.join.repository;


import member.join.entity.JoinEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JoinRepository extends JpaRepository<JoinEntity, Long> {
}
