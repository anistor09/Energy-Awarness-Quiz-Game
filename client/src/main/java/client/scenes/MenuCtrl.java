package client.scenes;

import com.google.inject.Inject;


public class MenuCtrl {

    private final MainCtrl mainCtrl;

    @Inject
    public MenuCtrl(MainCtrl mainCtrl) {
        this.mainCtrl = mainCtrl;
    }

    /**
     * This action proceeds to close the stage
     */
    public void exitButton() {
        mainCtrl.closeStage();
    }

}
