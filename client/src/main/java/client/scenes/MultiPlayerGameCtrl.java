package client.scenes;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;

import javax.inject.Inject;

public class MultiPlayerGameCtrl {

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
    private Button option1;

    @FXML
    private Button option3;

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

    private final MainCtrl mainCtrl;

    @Inject
    public MultiPlayerGameCtrl(MainCtrl mainCtrl) {
        this.mainCtrl = mainCtrl;
    }

    /**
     * This method is supposed to go to the help page. At the moment it goes to menu because there is no help page
     */
    public void help() {
        mainCtrl.goTo("help");
    }

    public void exit() {
        mainCtrl.goTo("menu");
    }

}