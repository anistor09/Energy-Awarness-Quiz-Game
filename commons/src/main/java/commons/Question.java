package commons;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Question {

    private String question;    // question text
    private ArrayList<Double> options;  // display options
    private Double correctAnswer;  // the actual answer
    private BufferedImage questionImage;    // this is a front end issue. How should we do this?
    private Integer allowedTime;
    private String difficulty;
    private Integer availablePoints;

    /**
     * Creates an instance of Question.
     * @param question Activity to be asked.
     * @param correctAnswer Correct answer to the energy consumption of the activity.
     * @param questionImage Image of the question.
     * @param allowedTime Time allowed for the question to be answered by the user.
     * @param difficulty Difficulty of the question. This will determine the range of options that will be given to the
     *                   user.
     * @param availablePoints Maximum number of points that can be obtained by answering the question.
     */
    public Question(String question, Double correctAnswer, BufferedImage questionImage, Integer allowedTime,
                    String difficulty, Integer availablePoints) {
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

            optionOne = generateRandomNumber(correctAnswer*1.2, correctAnswer*0.8);

            optionTwo = optionOne;

            while (optionOne==optionTwo) {
                optionTwo = generateRandomNumber(correctAnswer*1.2, correctAnswer*0.8);
            }

        } else if (difficulty.equals("MEDIUM")) {

            optionOne = generateRandomNumber(correctAnswer*1.1, correctAnswer*0.9);
            optionTwo = optionOne;
            while (optionOne==optionTwo) {
                optionTwo = generateRandomNumber(correctAnswer*1.1, correctAnswer*0.9);
            }
        } else {
            optionOne = generateRandomNumber( correctAnswer*1.05, correctAnswer*0.95);

            optionTwo = optionOne;

            while (optionOne==optionTwo) {
                optionTwo = generateRandomNumber( correctAnswer*1.05, correctAnswer*0.95);
            }
        }

        // add all possible answers to the arrayList
        this.options.addAll(List.of(correctAnswer, optionOne, optionTwo));
    }

    /**
     * Private utility method used by the constructor.
     * @param lowerBound Lower bound.
     * @param upperBound Upper bound.
     * @return A random number (double) between the given lower bound and upper bound.
     */
    private Double generateRandomNumber(double lowerBound, double upperBound) {
        double range = upperBound - lowerBound;

        // generate 2 unique numbers within these bounds
        double number = ((Math.random() * range) + lowerBound);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question)) return false;
        Question question1 = (Question) o;
        return getQuestion().equals(question1.getQuestion()) && getOptions().equals(question1.getOptions())
                && getCorrectAnswer().equals(question1.getCorrectAnswer()) && Objects.equals(getQuestionImage(),
                question1.getQuestionImage()) && getAllowedTime().equals(question1.getAllowedTime()) &&
                getDifficulty().equals(question1.getDifficulty()) &&
                getAvailablePoints().equals(question1.getAvailablePoints());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getQuestion(), getOptions(), getCorrectAnswer(), getQuestionImage(), getAllowedTime(),
                getDifficulty(), getAvailablePoints());
    }


    /**
     * Returns a String representation of the question object.
     * @return String displaying attributes of the question.
     */
    @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
                ", options=" + options +
                ", correctAnswer=" + correctAnswer +
                ", questionImage=" + questionImage +
                ", allowedTime=" + allowedTime +
                ", difficulty='" + difficulty + '\'' +
                ", availablePoints=" + availablePoints +
                '}';
    }
}
