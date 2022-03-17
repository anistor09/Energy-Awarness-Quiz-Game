package client.scenes;

import javafx.fxml.FXML;

import javax.inject.Inject;


public class HelpCtrl{
    private final MainCtrl mainCtrl;

    @Inject
    public HelpCtrl(MainCtrl mainCtrl){
        this.mainCtrl = mainCtrl;
    }

    @FXML
    protected void goToMenu(){
        mainCtrl.goTo("menu");
    }
}