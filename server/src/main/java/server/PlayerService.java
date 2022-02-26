package server;

import commons.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.database.PlayerRepository;

import java.util.List;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    /**
     * Creates an instance of this class PlayerService Class.
     * @param playerRepository  An instance of the repository class.
     */
    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> getPlayers(){
        return playerRepository.findAll();
    }

    /**
     * Service layer method for adding a player to the repository class
     * @param player An instance of the Player Class
     */
    public void addPlayer(Player player) {
        playerRepository.save(player);
    }
}
