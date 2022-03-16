package client.scenes;

import com.google.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class MultiPlayerOpenQuestionCtrl {

    @FXML
    private Label ReactionName;

    @FXML
    private ImageView anger;

    @FXML
    private Separator bar;

    @FXML
    private ImageView crying;

    @FXML
    private ImageView devil;

    @FXML
    private Button exit;

    @FXML
    private Button help;

    @FXML
    private ImageView image;

    @FXML
    private ImageView inLove;

    @FXML
    private ImageView joker;

    @FXML
    private Button joker1;

    @FXML
    private Button joker2;

    @FXML
    private Button joker3;

    @FXML
    private Label question;

    @FXML
    private ImageView reaction;

    @FXML
    private Label score;

    @FXML
    private ImageView smiling;

    @FXML
    private ImageView thinking;

    @FXML
    private Label time;

    @FXML
    private TextField userAnswer;

    private final MainCtrl mainCtrl;

    @Inject
    public MultiPlayerOpenQuestionCtrl(MainCtrl mainCtrl) {
        this.mainCtrl = mainCtrl;
    }

    @FXML
    void exit(ActionEvent event) {
        mainCtrl.goTo("menu");
    }

    @FXML
    void help(ActionEvent event) {

    }

}
