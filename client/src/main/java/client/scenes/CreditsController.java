package client.scenes;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javax.inject.Inject;


public class CreditsController {
    private final MainCtrl mainCtrl;

    @Inject
    public CreditsController(MainCtrl mainCtrl) {
        this.mainCtrl = mainCtrl;
    }

    @FXML
    protected void returnMenu(){
        mainCtrl.Return("menu");
    }

}
