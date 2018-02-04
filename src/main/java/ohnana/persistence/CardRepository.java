package ohnana.persistence;

import ohnana.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface CardRepository extends JpaRepository<Card, UUID> {
    @Query(
            nativeQuery = true,
            value = "SELECT * FROM card ORDER BY RANDOM() LIMIT 30"
    )
    List<Card> getRandomCards();
}
