package commons;

import java.util.ArrayList;
import java.util.Objects;

public class EliminateOption extends JokerCard{
    private MultipleChoiceQuestion question;

    public EliminateOption(String name, String description, boolean onlyMultiplayer, MultipleChoiceQuestion question) {
        super(name, description, onlyMultiplayer);
        this.question = question;
    }

    @Override
    public void useCard() {
        ArrayList<Double> options = this.question.getOptions();
        double correctOption = (double) this.question.getActivity().getCorrectAnswer();
        double optionToDelete = options.get(0);
        //first two elements in the list are always wrong according to Som's implementation
        //just in case, additional check:
        if(optionToDelete == correctOption){
            optionToDelete = options.get(1);
        }
        options.remove(optionToDelete);
    }

    public MultipleChoiceQuestion getQuestion() {
        return question;
    }

    public void setQuestion(MultipleChoiceQuestion question) {
        this.question = question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        EliminateOption that = (EliminateOption) o;
        return Objects.equals(question, that.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), question);
    }

    @Override
    public String toString() {
        return "EliminateOption{" +
                "question=" + question +
                '}';
    }
}
