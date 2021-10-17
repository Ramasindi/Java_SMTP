

import java.lang.reflect.InvocationTargetException;

import acsse.csc2b.Email;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * A class containing the main method.
 * @author Thalukanyo
 * @version P03
 */
public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
    }
    
	@Override
    public void start(Stage primaryStage) throws InvocationTargetException {
        primaryStage.setTitle("Sending Electronic Mail using SMTP Protocol");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setHgap(40);
        grid.setVgap(10);
        grid.setPadding(new Insets(5, 5, 5, 5));
        Text scenetitle = new Text("Welcome to your Mail");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label hostName = new Label("Enter Host-name:");
        grid.add(hostName, 0, 1);

        TextField hostTextField = new TextField();
        grid.add(hostTextField, 1, 1);
       
        
        Label portNumber = new Label("Enter Port-number:");
        grid.add(portNumber, 0, 2);

        TextField portNumBox = new TextField();
        grid.add(portNumBox, 1, 2);
        
        Button btn = new Button("Connect");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);
        
        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);
        
        btn.setOnAction(new EventHandler<ActionEvent>() {
        	 
            @Override
            public void handle(ActionEvent e) {
                actiontarget.setFill(Color.FIREBRICK);
                String hostTextVal = hostTextField.getText(); 
                int number = Integer.parseInt(portNumBox.getText());
                
                actiontarget.setText(Email.establishConnection(hostTextVal,number));
            }
        });
        //Details
        Label senderName = new Label("Sender Name:");
        grid.add(senderName, 0, 8);

        TextField senderTextField = new TextField();
        grid.add(senderTextField, 1, 8);
        
        
        Label recipientName = new Label("Recipient Name:");
        grid.add(recipientName, 0, 9);

        TextField recipientBox = new TextField();
        grid.add(recipientBox, 1, 9);
        
        
        Label subject = new Label("Subject:");
        grid.add(subject, 0, 10);

        TextField subjectBox = new TextField();
        grid.add(subjectBox, 1, 10);
        
        
        Label messageLabel = new Label("Message:"); 
        grid.add(messageLabel, 0, 11);
        
        TextArea messageBox = new TextArea();
        messageBox.setPrefWidth(80);
        messageBox.setMaxSize(200, 300);
        messageBox.setWrapText(true);;
        
        grid.add(messageBox, 1, 11,1,11);
       
        //Sending mail
        Button send = new Button("Send");
        HBox hbSend = new HBox(10);
        hbSend.setAlignment(Pos.BOTTOM_RIGHT);
        hbSend.getChildren().add(send);
        grid.add(hbSend, 1, 23);
        
        final Text messageStatus = new Text();
        grid.add(messageStatus, 1, 24);
        
        send.setOnAction(new EventHandler<ActionEvent>() {
        	 
            @Override
            public void handle(ActionEvent e) {
            	messageStatus.setFill(Color.FIREBRICK);
            	String senderVal = senderTextField.getText(); 
            	String recipientVal = recipientBox.getText();
            	String subjectVal = subjectBox.getText();
            	String messageBoxVal = messageBox.getText();
                messageStatus.setText(Email.addDetails(senderVal, recipientVal, messageBoxVal,subjectVal));
            }
        });
        
        Scene scene = new Scene(grid, 700, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
     
   
}
