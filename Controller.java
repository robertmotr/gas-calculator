package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    @FXML
    private TextField originBox;

    @FXML
    private Button calculateButton;

    @FXML
    private TextField destBox;

    @FXML
    private Label gasBillFinal;

    private List<String> origin_suggestions; // for queries
    private List<String> destination_suggestions;

    private String origin_address; // for calculations
    private String destination_address;

    private List<String> distanceInt;
    private String distance;
    private Integer temporary;
    private double gasBill;


    @FXML
    public void buttonClick() throws IOException {

        calculateButton.setTranslateX(5); // animation

        origin_address = originBox.getText(); // get text from text fields
        destination_address = destBox.getText();

        DistanceGateway distanceGateway = new DistanceGateway(origin_address, destination_address);
        Distance distanceStack = distanceGateway.executeDistance(origin_address, destination_address);

        distanceInt = distanceStack.getDistanceList();
        distance = distanceInt.get(0);
        temporary = Integer.parseInt(distance); // temporary cast, temporary represents distance in KM

        gasBill = temporary / 100;
        gasBill = gasBill * 9.5;
        gasBill = gasBill * 1;

        temporary = null; // throw temporary to gc

        gasBillFinal.setText("Your gas bill is: " + gasBill);
        

    }
    @FXML
    public void originTextChanged() throws IOException {

        origin_address = originBox.getText();
        QueryGateway queryGateway = new QueryGateway(origin_address);
        Query queryStack = queryGateway.executeQuery(origin_address);
        origin_suggestions = queryStack.queryList;

    }
    @FXML
    public void destinationTextChanged() throws IOException {

        destination_address = destBox.getText();
        QueryGateway queryGateway = new QueryGateway(destination_address);
        Query queryStack = queryGateway.executeQuery(destination_address);
        destination_suggestions = queryStack.queryList;

    }

    @FXML
    public void menuCloseClicked() { System.exit(0); }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        TextFields.bindAutoCompletion(originBox, origin_suggestions);
        TextFields.bindAutoCompletion(destBox, destination_suggestions);


    }
}
