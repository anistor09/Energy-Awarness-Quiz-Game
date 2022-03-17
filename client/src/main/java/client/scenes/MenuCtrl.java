package client.scenes;

import client.MyFXML;
import client.MyModule;
import com.google.inject.Inject;
import com.google.inject.Injector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

import static com.google.inject.Guice.createInjector;


public class MenuCtrl {

    private final MainCtrl mainCtrl;

    private static final Injector INJECTOR = createInjector(new MyModule());
    private static final MyFXML FXML = new MyFXML(INJECTOR);

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
        mainCtrl.closeStage();
    }

    @FXML
    protected void goToMultiPlayerLobby(){
        mainCtrl.goTo("insertInfoMultiPlayer");
    }

    @FXML
    protected void goToCredits(){
        mainCtrl.goTo("credits");
    }

    @FXML
    protected void goToSinglePlayerLobby() throws IOException {
        mainCtrl.goTo("singleLobby");
    }

    @FXML
    protected void goToSinglePlayerEntryScreen(MouseEvent mouseEvent) {
        mainCtrl.goTo("insertInfoSinglePlayer");
    }
}