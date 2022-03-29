package commons;

import java.util.ArrayList;
import java.util.Objects;

public class EliminateOptionJoker extends JokerCard{
    private MultipleChoiceQuestion question;

    public EliminateOptionJoker(String name, String description,
                                boolean onlyMultiplayer, MultipleChoiceQuestion question) {
        super(name, description, onlyMultiplayer);
        this.question = question;
    }
    public EliminateOptionJoker( MultipleChoiceQuestion question) {
        super("EliminateOptionJoker", "....", false);
        this.question = question;
    }

    /**
     * This method deletes one of the wrong answers in the MultipleChoiceQuestion.
     */
    @Override
    public void useCard() {
        ArrayList<Long> options = this.question.getOptions();
        long correctOption = this.question.getActivity().getConsumption_in_wh();
        long optionToDelete = options.get(0);
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
        EliminateOptionJoker that = (EliminateOptionJoker) o;
        return Objects.equals(question, that.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), question);
    }

    @Override
    public String toString() {
        return "EliminateOptionJoker{" +
                "question=" + question +
                '}';
    }
}
