package server.api;

import commons.SinglePlayerGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.sevice.GameService;

@RestController
@RequestMapping("api/game")
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    /**
     * This method will create a Game to send to the client. It will be a singlePlayer instance
     * @return a SinglePlayerGame with all the attributes so the player can play the game
     */
    @GetMapping(path = "singleGame")
    public SinglePlayerGame createGame() {
        return gameService.createGame();
    }


}
