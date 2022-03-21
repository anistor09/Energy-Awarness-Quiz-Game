package client.scenes;

import client.MyFXML;
import client.MyModule;
import client.utils.ServerUtils;
import com.google.inject.Injector;
import commons.Activity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javax.inject.Inject;

import java.util.List;
import java.util.stream.Collectors;

import static com.google.inject.Guice.createInjector;

public class AdminPanelCtrl {

    private final MainCtrl mainCtrl;
    private final ServerUtils server;
    private List<Activity> listOfActivities;
    private int currentIndex = 0;

    private static final Injector INJECTOR = createInjector(new MyModule());
    private static final MyFXML FXML = new MyFXML(INJECTOR);

    @Inject
    public AdminPanelCtrl(MainCtrl mainCtrl, ServerUtils serverUtils) {
        this.mainCtrl = mainCtrl;
        this.server = serverUtils;
    }

    @FXML
    private Label activity1;

    @FXML
    private Label activity2;

    @FXML
    private Label activity3;

    @FXML
    private Label activity4;

    @FXML
    private Label activity5;

    @FXML
    private Label activity6;

    @FXML
    private Label activity7;

    @FXML
    private Label activity8;

    @FXML
    private Label activity9;

    @FXML
    private Label activity10;

    @FXML
    private Button next;

    @FXML
    private Button previous;

    @FXML
    private Button returnMenu;

    @FXML
    public void goToMenu() {
        this.mainCtrl.goTo("menu");
    }

    public void nextPage() {
        instantiateActivities(true, false);
    }

    public void previousPage() {
        instantiateActivities(false, false);
    }

    public void editActivity(String id) {
        mainCtrl.goToEditActivity(this.server.getActivityById(id));
    }

    public void instantiateActivities(boolean direction, boolean firstTime) {
        activateLabels();

        listOfActivities = server.getActivities();
        if(direction) {
            if(!firstTime) currentIndex += 10;
            previous.setDisable(firstTime);
            if(currentIndex + 10 >= (listOfActivities.size() - 1)) {
                List<Activity> temp = listOfActivities.subList(currentIndex, listOfActivities.size());
                List<String> list = temp.stream().map(Activity::getId).collect(Collectors.toList());
                while(list.size() < 10) list.add("");
                next.setDisable(true);
                mapActivities(list);
            } else {
                List<Activity> temp = listOfActivities.subList(currentIndex, currentIndex + 10);
                List<String> list = temp.stream().map(Activity::getId).collect(Collectors.toList());
                mapActivities(list);
            }
        } else {
            next.setDisable(false);
            List<Activity> temp = listOfActivities.subList(currentIndex - 10, currentIndex);
            List<String> list = temp.stream().map(Activity::getId).collect(Collectors.toList());
            mapActivities(list);
            currentIndex -= 10;
            if(currentIndex == 0) previous.setDisable(true);
        }
    }

    public void mapActivities(List<String> smallList) {
        activity1.setText(smallList.get(0));
        activity2.setText(smallList.get(1));
        activity3.setText(smallList.get(2));
        activity4.setText(smallList.get(3));
        activity5.setText(smallList.get(4));
        activity6.setText(smallList.get(5));
        activity7.setText(smallList.get(6));
        activity8.setText(smallList.get(7));
        activity9.setText(smallList.get(8));
        activity10.setText(smallList.get(9));
    }

    public void activateLabels() {
        activity1.setOnMouseClicked(e -> editActivity(((Label) e.getSource()).getText()));
        activity2.setOnMouseClicked(e -> editActivity(((Label) e.getSource()).getText()));
        activity3.setOnMouseClicked(e -> editActivity(((Label) e.getSource()).getText()));
        activity4.setOnMouseClicked(e -> editActivity(((Label) e.getSource()).getText()));
        activity5.setOnMouseClicked(e -> editActivity(((Label) e.getSource()).getText()));
        activity6.setOnMouseClicked(e -> editActivity(((Label) e.getSource()).getText()));
        activity7.setOnMouseClicked(e -> editActivity(((Label) e.getSource()).getText()));
        activity8.setOnMouseClicked(e -> editActivity(((Label) e.getSource()).getText()));
        activity9.setOnMouseClicked(e -> editActivity(((Label) e.getSource()).getText()));
        activity10.setOnMouseClicked(e -> editActivity(((Label) e.getSource()).getText()));
    }



}