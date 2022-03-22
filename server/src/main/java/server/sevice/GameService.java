package server.sevice;

import commons.Question;
import commons.*;
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
     * This method will create a list of mostEnergyQuestions to return to the player
     * @return the list of MostEnergyQuestion
     */
    public ArrayList<MostEnergyQuestion> getListMostEnergy() {
        ArrayList<MostEnergyQuestion> questions = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            Question q = questionController.getRandomQuestion("mostEnergy");
            if(questions.contains(q)) {
                i--;
                continue;
            } else {
                questions.add((MostEnergyQuestion) q);
            }
        }
        return questions;
    }

    /**
     * This method will create a list of GuessQuestion to return to the player
     * @return the list of GuessQuestion
     */
    public ArrayList<GuessQuestion> getListGuessQuestion() {
        ArrayList<GuessQuestion> questions = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            Question q = questionController.getRandomQuestion("guess");
            if(questions.contains(q)) {
                i--;
                continue;
            } else {
                questions.add((GuessQuestion) q);
            }
        }
        return questions;
    }

    /**
     * This method will create a list of multipleChoiceQuestion to return to the player
     * @return the list of multipleChoiceQuestion
     */
    public ArrayList<MultipleChoiceQuestion> getListMultipleChoice() {
        ArrayList<MultipleChoiceQuestion> questions = new ArrayList<>();
        for(int i = 0; i < 6; i++) {
            Question q = questionController.getRandomQuestion("multipleChoice");
            if(questions.contains(q)) {
                i--;
                continue;
            } else {
                questions.add((MultipleChoiceQuestion) q);
            }
        }
        return questions;
    }

    /**
     * This method will create a list of insteadOfQuestion to return to the player
     * @return the list of insteadOfQuestion
     */
    public ArrayList<InsteadOfQuestion> getListInsteadOf() {
        ArrayList<InsteadOfQuestion> questions = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            Question q = questionController.getRandomQuestion("insteadOf");
            if(questions.contains(q)) {
                i--;
                continue;
            } else {
                questions.add((InsteadOfQuestion) q);
            }
        }
        return questions;
    }
}
