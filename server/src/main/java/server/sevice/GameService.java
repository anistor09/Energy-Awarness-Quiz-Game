package server.sevice;

import commons.Question;
import commons.SinglePlayerGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.api.QuestionController;

import java.util.ArrayList;

@Service
public class GameService {

    private final QuestionController questionController;

    @Autowired
    public GameService(QuestionController questionController) {
        this.questionController = questionController;
    }

    /**
     * This is the method that will create the game for the player. It sends requests to questionController to get all
     * the needed activities. It initializes the game with all the questions but with Player as null and jokers as an
     * empty array
     *
     * @return the SinglePlayerGame
     */
    public SinglePlayerGame createGame() {
        ArrayList<Question> questions = new ArrayList<>();
        for(int i = 0; i < 21; i++) {
            Question q = questionController.getRandomQuestion();
            if(questions.contains(q)) {
                i--;
                continue;
            } else {
                questions.add(q);
            }
        }
        SinglePlayerGame toReturn = new SinglePlayerGame(questions, new ArrayList<>(), null);
        return toReturn;
    }
}
