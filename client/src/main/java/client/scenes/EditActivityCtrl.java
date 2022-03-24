package client.scenes;

import commons.Activity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javax.inject.Inject;

public class EditActivityCtrl {

    private final MainCtrl mainCtrl;
    private final AdminPanelCtrl adminPanelCtrl;
    private Activity activity;

    @Inject
    public EditActivityCtrl(MainCtrl mainCtrl, AdminPanelCtrl adminPanelCtrl) {
        this.mainCtrl = mainCtrl;
        this.adminPanelCtrl = adminPanelCtrl;
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

    /**
     * This method will prepare the edited activity and make a request to the server to update that activity.
     * Once that is finished the user returns to the admin panel
     * @param event
     */
    @FXML
    void goToAdmin(ActionEvent event) {
        String activityTitle = title.getText();
        long activityConsumption = getConsumption();
        String activitySource = source.getText();
        if(activitySource.length() >= 255) activitySource = activitySource.substring(0, 255);
        //String imagePath = image_path.getText();  CURRENTLY THERE IS NO IMPLEMENTATION OF IMAGE TRANSFER
        Activity newAct = new Activity(this.activity.getId(), activity.getImage_path(), activityTitle,
                activityConsumption, activitySource);
        mainCtrl.editActivity(newAct);
        mainCtrl.goTo("admin");
    }


    /**
     * This method will get the consumption out of the text field, taking into account that the user might have
     * entered an invalid number
     * @return the consumption as a long
     */
    long getConsumption() {
        try {
            long guess = Long.parseLong(consumption.getCharacters().toString());
            return guess;
        } catch (Exception e) {
            return activity.getConsumption_in_wh();
        }

    }

    /**
     * This method will set all text fields to the corresponding activity attributes
     * @param activity to map
     */
    void initialize(Activity activity) {
        this.activity = activity;
        this.title.setText(activity.getTitle());
        this.consumption.setText("" + activity.getConsumption_in_wh());
        this.source.setText(activity.getSource());
        this.image_path.setText(activity.getImage_path());
    }


}