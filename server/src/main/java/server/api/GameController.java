package server.api;

import commons.GuessQuestion;
import commons.InsteadOfQuestion;
import commons.MostEnergyQuestion;
import commons.MultipleChoiceQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.sevice.GameService;

import java.util.ArrayList;

@RestController
@RequestMapping("api/game")
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }


    /**
     * This method will retrieve a list of mostEnergyQuestions
     * @return the list of mostEnergyQuestions
     */
    @GetMapping(path = "singleGame/mostEnergy")
    public ArrayList<MostEnergyQuestion> getListMostEnergy() {
        return gameService.getListMostEnergy();
    }

    @GetMapping(path = "singleGame/guess")
    public ArrayList<GuessQuestion> getListGuess() {
        return gameService.getListGuessQuestion();
    }

    @GetMapping(path = "singleGame/multipleChoice")
    public ArrayList<MultipleChoiceQuestion> getListMultipleChoice() {
        return gameService.getListMultipleChoice();
    }

    @GetMapping(path = "singleGame/insteadOf")
    public ArrayList<InsteadOfQuestion> getListInsteadOf() {
        return gameService.getListInsteadOf();
    }
}
