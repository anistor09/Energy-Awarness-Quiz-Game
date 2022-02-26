package server;

import commons.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.database.PlayerRepository;

import java.util.List;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    /**
     * Returns a list of players in the repository.
     * @return List of Player instances found the in the repository.
     */
    public List<Player> getPlayers(){
        return playerRepository.findAll();
    }

    public void addPlayer(Player player) {
        playerRepository.save(player);
    }
}
