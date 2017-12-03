package ohnana.persistence;

import ohnana.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
}
