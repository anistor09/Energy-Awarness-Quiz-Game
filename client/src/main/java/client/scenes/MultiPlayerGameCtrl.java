package client.scenes;

import client.utils.ServerUtils;
import commons.Emoji;
import commons.Player;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

public class MultiPlayerGameCtrl implements Initializable {

    private final ServerUtils server;
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
        this.server = mainCtrl.getServer();
    }

    public void exit() {
        mainCtrl.goTo("menu");
    }

    public void setTime(int i) {
        time.setText(String.valueOf(i));
    }
    /**
     * This method send the Emoji to the other clients through WebSockets.
     * @param e Instance of Emoji Class that contains an emoji with the Player's username and it's image path.
     */
    public void sendEmoji(Emoji e){
        server.send("/app/emojis",e);
    }
    /**
     * This  method creates an Emoji and passes it to the sendEmoji() method
     * @param event Event that occurs when an image view for Emoji is pressed.
     */
    public void getEmoji(Event event){
        Emoji e =  new Emoji(mainCtrl.getLocalPlayer().getUsername(),((ImageView)event.getSource()).
                getImage().getUrl());
        sendEmoji(e);
    }

    /**
     * This method initialises the Scene with the last Emoji that was sent through the WebSocket.
     * @param e Instance of Emoji Class( sent through the WebSocket for Emoji Class)
     */
    public void initialiseEmoji(Emoji e) {
        ReactionName.setText(e.getSender());
        reaction.setImage(new Image(e.getEmojiPath()));
    }
    /**
     * This method initialises the Emojis images because they are not rendered directly for Windows users.
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        smiling.setImage(new Image(MainCtrl.class.getResource("/pictures/smilingTeeth.png").toString()));
        anger.setImage(new Image(MainCtrl.class.getResource("/pictures/anger.png").toString()));
        devil.setImage(new Image(MainCtrl.class.getResource("/pictures/devil.png").toString()));
        inLove.setImage(new Image(MainCtrl.class.getResource("/pictures/in-love.png").toString()));
        thinking.setImage(new Image(MainCtrl.class.getResource("/pictures/thinking.png").toString()));
        crying.setImage(new Image(MainCtrl.class.getResource("/pictures/crying.png").toString()));

    }

    public void addPoints(){
        Player localPlayer = mainCtrl.getLocalPlayer();
        localPlayer.setCurrentScore(localPlayer.getCurrentScore()+10);
    }
}