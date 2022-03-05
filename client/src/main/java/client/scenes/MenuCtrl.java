package client.scenes;

import com.google.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javax.swing.text.html.ImageView;


public class MenuCtrl {

    private final MainCtrl mainCtrl;

    @Inject
    public MenuCtrl(MainCtrl mainCtrl) {
        this.mainCtrl = mainCtrl;
    }

    @FXML
    private Button credits;

    @FXML
    private Button exit;

    @FXML
    private ImageView multiIcon;

    @FXML
    private Button multiPlayer;

    @FXML
    private ImageView singleIcon;

    @FXML
    private Button singlePlayer;

    @FXML
    void exitButton(ActionEvent event) {

    }

    public void exitButton() {
        mainCtrl.closeStage();
    }

}
