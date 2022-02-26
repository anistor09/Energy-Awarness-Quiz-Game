package server.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import commons.Player;
import server.PlayerService;

import java.util.List;

@RestController
@RequestMapping("api/player")
public class PlayerController {
    private PlayerService playerService;

    /**
     * Constructor for the PlayerController Class
     * @param playerService An instance of the utility class that links the API Layer to the Repository Layer
     */
    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public List<Player> printPlayers(){
        return playerService.getPlayers();
    }

    @PostMapping
    public void addNewPlayer( @RequestBody Player player){ playerService.addPlayer(player);
    }
}
