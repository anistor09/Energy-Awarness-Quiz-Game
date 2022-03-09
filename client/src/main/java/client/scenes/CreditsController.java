package client.scenes;

import javafx.fxml.FXML;

import javax.inject.Inject;


public class CreditsController {
    private final MainCtrl mainCtrl;

    @Inject
    public CreditsController(MainCtrl mainCtrl) {
        this.mainCtrl = mainCtrl;
    }

    @FXML
    protected void returnMenu(){
        mainCtrl.goTo("menu");
    }

}
