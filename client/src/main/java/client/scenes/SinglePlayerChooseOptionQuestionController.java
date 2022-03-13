package client.scenes;

import com.google.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class SinglePlayerChooseOptionQuestionController {

    @FXML
    private Button exit;

    @FXML
    private Button help;

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

    private final MainCtrl mainCtrl;

    @Inject
    public SinglePlayerChooseOptionQuestionController(MainCtrl mainCtrl) {
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

