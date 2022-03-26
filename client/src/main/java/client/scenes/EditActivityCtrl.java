package client.scenes;

import client.utils.ServerUtils;
import commons.Activity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class EditActivityCtrl {

    private final MainCtrl mainCtrl;
    private final ServerUtils server;
    private final AdminPanelCtrl adminPanelCtrl;
    private Activity activity;
    private File image;

    @Inject
    public EditActivityCtrl(MainCtrl mainCtrl, AdminPanelCtrl adminPanelCtrl, ServerUtils server) {
        this.mainCtrl = mainCtrl;
        this.adminPanelCtrl = adminPanelCtrl;
        this.server = server;
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
    private Button delete;

    @FXML
    private TextField id;

    @FXML
    private Button addActivity;

    @FXML
    private Label pathInfo;


    /**
     * This method will prepare the edited activity and make a request to the server to update that activity.
     * Once that is finished the user returns to the admin panel
     * @param event
     */
    @FXML
    void goToAdmin(ActionEvent event) throws IOException {
        if(id.visibleProperty().get()) {
            String newActId = this.id.getText();
            String activityTitle = title.getText();
            long activityConsumption = getConsumption();
            String activitySource = source.getText();
            String path = image_path.getText();
            if (newActId.equals("") || activityTitle.equals("") || activitySource.equals("") || path.equals("")) {
                mainCtrl.goTo("admin");
            }
            Activity newAct = new Activity(newActId, "extra/" + newActId + "." + getExtension(this.image.getName()),
                    activityTitle, activityConsumption, activitySource);
            //send Image
            MultipartFile toSend = new MockMultipartFile(newActId + "." + getExtension(this.image.getName()),
                    image.getName(), "image/" + getExtension(image.getName()),
                    Files.readAllBytes(Paths.get(image.getAbsolutePath())));
//
            server.addActivity(newAct);
            mainCtrl.goTo("admin");
        } else {
            String activityTitle = title.getText();
            long activityConsumption = getConsumption();
            String activitySource = source.getText();
            if(activitySource.length() >= 255) activitySource = activitySource.substring(0, 255);
            if (activityTitle.equals("") || activitySource.equals("")) {
                mainCtrl.goTo("admin");
            }
            Activity newAct;
            if (this.image == null) {
                newAct = new Activity(this.activity.getId(), activity.getImage_path(), activityTitle,
                        activityConsumption, activitySource);
            } else {
                String newImagePath = "extra/" + activity.getId().substring(3) + "." +
                        getExtension(this.image.getName());
                newAct = new Activity(this.activity.getId(), newImagePath, activityTitle, activityConsumption,
                        activitySource);
                //send image
            }
            server.editActivity(newAct);
            mainCtrl.goTo("admin");
        }
        activity = null;
        image = null;
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
            if(id.visibleProperty().get()) return 0;
            return activity.getConsumption_in_wh();
        }

    }

    /**
     * This method will set all text fields to the corresponding activity attributes
     * @param activity to map
     */
    void initialize(Activity activity) {
        setForEdit();
        this.addActivity.setVisible(false);
        this.activity = activity;
        this.title.setText(activity.getTitle());
        this.consumption.setText("" + activity.getConsumption_in_wh());
        this.source.setText(activity.getSource());
        this.image_path.setText(activity.getImage_path());
    }

    public void prepareAddActivity() {
        setForAdd();
    }

    public void deleteActivity() {
        server.deleteActivity(activity);
        mainCtrl.goTo("admin");
    }

    public void setForEdit() {
        pathInfo.setText("");
        this.id.setVisible(false);
        this.addActivity.setVisible(false);
        this.delete.setVisible(true);
    }

    public void setForAdd() {
        pathInfo.setText("");
        this.id.setVisible(true);
        this.addActivity.setVisible(true);
        this.delete.setVisible(false);
        this.id.clear();
        this.title.clear();
        this.consumption.clear();
        this.source.clear();
        this.image_path.clear();
    }

    public void chooseFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open image");
        File image = fileChooser.showOpenDialog(mainCtrl.primaryStage);
        if (image != null) {
            System.out.println(image.length());
            if (image.length() > 500000) {
                pathInfo.setText("Pick a file with smaller size!");
                return;
            }
            String path = image.getName();
            System.out.println(path);
            if (path.endsWith("png") || path.endsWith("jpeg") ||path.endsWith("jpg")) {
                pathInfo.setText("");
                image_path.setText(image.getAbsolutePath());
                this.image = image;
            } else {
                pathInfo.setText("Invalid file type!");
                return;
            }
        }
    }

    public String getExtension(String name) {
        String[] arr = name.split("\\.");
        return arr[1];
    }

}