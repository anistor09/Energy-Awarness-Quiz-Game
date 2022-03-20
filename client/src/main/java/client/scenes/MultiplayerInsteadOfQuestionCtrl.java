package client.scenes;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;

import javax.inject.Inject;

public class MultiplayerInsteadOfQuestionCtrl {

    @FXML
    private Button exit;

    @FXML
    private Button joker1;

    @FXML
    private Button joker2;

    @FXML
    private Button joker3;

    @FXML
    private Button option1;

    @FXML
    private ImageView option1Image;

    @FXML
    private Button option2;

    @FXML
    private ImageView option2Image;

    @FXML
    private Button option3;

    @FXML
    private ImageView option3Image;

    @FXML
    private Label question;

    @FXML
    private Label score;

    @FXML
    private Label time;

    @FXML
    private Label ReactionName;

    @FXML
    private ImageView anger;

    @FXML
    private ImageView crying;

    @FXML
    private ImageView devil;

    @FXML
    private ImageView inLove;

    @FXML
    private ImageView smiling;

    @FXML
    private ImageView thinking;

    @FXML
    private Separator bar;

    @FXML
    private ImageView reaction;

    private final MainCtrl mainCtrl;

    @Inject
    public MultiplayerInsteadOfQuestionCtrl(MainCtrl mainCtrl){
        this.mainCtrl = mainCtrl;
    }

    @FXML
    protected void goToMenu(){
        mainCtrl.goTo("menu");
    }
}
