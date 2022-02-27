package server.database;

import org.springframework.data.jpa.repository.JpaRepository;
import commons.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {

}
