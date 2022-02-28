package client.scenes;

import com.google.inject.Inject;


public class MenuCtrl {

    private final MainCtrl mainCtrl;

    @Inject
    public MenuCtrl(MainCtrl mainCtrl) {
        this.mainCtrl = mainCtrl;
    }

    public void exitButton() {
        mainCtrl.closeStage();
    }

}
