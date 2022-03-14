package server.api;

import commons.SinglePlayerGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.database.SinglePlayerGameRepository;
import server.sevice.PlayerService;
import server.sevice.SinglePlayerGameService;

import java.util.List;

@RestController
@RequestMapping("/api/singleplayergame")
public class SinglePlayerGameController {

    private SinglePlayerGameService singlePlayerGameService;

    @Autowired
    public SinglePlayerGameController(SinglePlayerGameService singlePlayerGameService) {
        this.singlePlayerGameService = singlePlayerGameService;
    }

    @GetMapping
    public List<SinglePlayerGame> getSinglePlayerGames() {
        return singlePlayerGameService.getSinglePlayerGames();
    }

    @PostMapping
    public void addNewSinglePlayerGame(@RequestBody SinglePlayerGame singlePlayerGame) {
        singlePlayerGameService.addSinglePlayerGame(singlePlayerGame);
    }

    @DeleteMapping(path="{gameId}")
    public void deleteSinglePlayerGame(@PathVariable("gameId") Long gameId) {
        singlePlayerGameService.deleteSinglePlayerGame(gameId);
    }
}
