package server.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import commons.Player;
import server.PlayerService;

import java.util.List;

/**
 * API layer of the Player class.
 */
@RestController
@RequestMapping("api/player")
public class PlayerController {
    private PlayerService playerService;

    /**
     * Creates an instance of PlayerController Class
     * @param playerService An instance of the utility class that links the API Layer to the Repository Layer
     */
    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    /**
     * Serves the user's GET request by invoking the getPlayers method from the playerService object
     * @return List of players returned by the service layer.
     */
    @GetMapping
    public List<Player> getPlayers(){
        return playerService.getPlayers();
    }

    /**
     * API layer method for the POST request
     * @param player An instance of the utility class that links the API Layer to the Repository Layer
     */
    @PostMapping
    public void addNewPlayer( @RequestBody Player player){ playerService.addPlayer(player);
    }
}
