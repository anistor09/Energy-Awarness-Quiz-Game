package server.database;

import org.springframework.data.jpa.repository.JpaRepository;
import commons.Player;

/**
 * Repository for the player class
 */
public interface PlayerRepository extends JpaRepository<Player, Long> {

}
