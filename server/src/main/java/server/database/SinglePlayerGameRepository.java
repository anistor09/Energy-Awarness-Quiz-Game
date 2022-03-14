package server.database;

import commons.SinglePlayerGame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SinglePlayerGameRepository extends JpaRepository<SinglePlayerGame, Long> {
}
