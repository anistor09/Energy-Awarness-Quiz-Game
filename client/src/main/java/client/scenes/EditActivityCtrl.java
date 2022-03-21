package client.scenes;

import commons.Activity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javax.inject.Inject;

public class EditActivityCtrl {

    private final MainCtrl mainCtrl;
    private Activity activity;

    @Inject
    public EditActivityCtrl(MainCtrl mainCtrl) {
        this.mainCtrl = mainCtrl;
    }

    @FXML
    private TextField consumption;

    @FXML
    private TextField image_path;

    @FXML
    private Button returnAdmin;

    @FXML
    private TextField source;

    @FXML
    private TextField title;

    @FXML
    void goToAdmin(ActionEvent event) {
        String activityTitle = title.getText();
        long activityConsumption = getConsumption();
        String activitySource = source.getText();
        if(activitySource.length() >= 255) activitySource = activitySource.substring(0, 255);
        String imagePath = image_path.getText();
        mainCtrl.goTo("admin");
    }

    long getConsumption() {
        try {
            long guess = Long.parseLong(consumption.getCharacters().toString());
            return guess;
        } catch (Exception e) {
            return activity.getConsumption_in_wh();
        }

    }

    void initialize(Activity activity) {
        this.activity = activity;
        this.title.setText(activity.getTitle());
        this.consumption.setText("" + activity.getConsumption_in_wh());
        this.source.setText(activity.getSource());
        this.image_path.setText(activity.getImage_path());
    }


}