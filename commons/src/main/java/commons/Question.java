package commons;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Question {

    private String question;    // question text
    private ArrayList<Double> options;  // display options
    private Double correctAnswer;  // the actual answer
    private BufferedImage questionImage;    // this is a front end issue. How should we do this?
    private Integer allowedTime;
    private String difficulty;
    private Integer availablePoints;

    public Question(String question, Double correctAnswer, BufferedImage questionImage, Integer allowedTime, String difficulty, Integer availablePoints) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.questionImage = questionImage;
        this.allowedTime = allowedTime;
        this.availablePoints = availablePoints;

        // create a range of answers

        double optionOne;
        double optionTwo;

        if (difficulty.equals("EASY")) {
            // 20% range

            optionOne = generateRandomNumber(correctAnswer, correctAnswer*1.2, correctAnswer*0.8);

            optionTwo = optionOne;

            while (optionOne==optionTwo) {
                optionTwo = generateRandomNumber(correctAnswer, correctAnswer*1.2, correctAnswer*0.8);
            }

        } else if (difficulty.equals("MEDIUM")) {

            optionOne = generateRandomNumber(correctAnswer, correctAnswer*1.1, correctAnswer*0.9);
            optionTwo = optionOne;
            while (optionOne==optionTwo) {
                optionTwo = generateRandomNumber(correctAnswer, correctAnswer*1.1, correctAnswer*0.9);
            }
        } else {
            optionOne = generateRandomNumber(correctAnswer, correctAnswer*1.05, correctAnswer*0.95);

            optionTwo = optionOne;

            while (optionOne==optionTwo) {
                optionTwo = generateRandomNumber(correctAnswer, correctAnswer*1.05, correctAnswer*0.95);
            }
        }

        // add all possible answers to the arrayList
        this.options.addAll(List.of(correctAnswer, optionOne, optionTwo));
    }

    private Double generateRandomNumber(double base, double lowerBound, double upperBound) {
        double range = upperBound - lowerBound;

        // generate 2 unique numbers within these bounds
        double number = ((Math.random() * (upperBound - lowerBound)) + lowerBound);
        return number;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ArrayList<Double> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<Double> options) {
        this.options = options;
    }

    public Double getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(Double correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public BufferedImage getQuestionImage() {
        return questionImage;
    }

    public void setQuestionImage(BufferedImage questionImage) {
        this.questionImage = questionImage;
    }

    public Integer getAllowedTime() {
        return allowedTime;
    }

    public void setAllowedTime(Integer allowedTime) {
        this.allowedTime = allowedTime;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getAvailablePoints() {
        return availablePoints;
    }

    public void setAvailablePoints(Integer availablePoints) {
        this.availablePoints = availablePoints;
    }

    
}
