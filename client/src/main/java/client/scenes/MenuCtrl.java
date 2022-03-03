package client.scenes;

import client.MyFXML;
import client.MyModule;
import com.google.inject.Inject;
import com.google.inject.Injector;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

import static com.google.inject.Guice.createInjector;


public class MenuCtrl {

    private final MainCtrl mainCtrl;
    @javafx.fxml.FXML
    Button multiPlayerButton;
    @javafx.fxml.FXML
    Button singlePlayerButton;

    private static final Injector INJECTOR = createInjector(new MyModule());
    private static final MyFXML FXML = new MyFXML(INJECTOR);

    @Inject
    public MenuCtrl(MainCtrl mainCtrl) {
        this.mainCtrl = mainCtrl;
    }

    public void exitButton() {
        mainCtrl.closeStage();
    }

    @FXML
    protected void goToMultiPlayerLobby(){
        mainCtrl.goToMultiPlayerLobby();
    }

    @FXML
    protected void goToCredits(){
        mainCtrl.goToCredits();
    }

    @FXML
    protected void goToSinglePlayerLobby() throws IOException {
        mainCtrl.goToSinglePlayerLobby();
    }
}
