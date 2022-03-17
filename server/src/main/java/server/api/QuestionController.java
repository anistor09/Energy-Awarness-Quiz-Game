package server.api;

import commons.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.sevice.QuestionService;

@RestController
@RequestMapping("api/question")
public class QuestionController {


    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    /**
     * This method will get a random question from the QuestionService
     * @return the random question
     */
    public Question getRandomQuestion() {
        return questionService.getRandomQuestion();
    }


}
