package knight2103;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Knight2103 knight2103;

    private Image userImage = new Image(
            this.getClass().getResourceAsStream("/images/Koobit_Jaguar.png"));
    private Image knight2103Image = new Image(
            this.getClass().getResourceAsStream("/images/Koobit_Water_Droplet.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(DialogBox.getKnight2103Dialog(
                "_____________\nHello! I'm Knight2103\nWhat can I do for you?\n_____________",
                knight2103Image));
    }

    /** Injects the Knight2103 instance */
    public void setKnight2103(Knight2103 chatbot) {
        knight2103 = chatbot;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = knight2103.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getKnight2103Dialog(response, knight2103Image)
        );
        userInput.clear();

        /*
        if (input.contains("Bye. Hope to see you again soon!")) {
            Platform.exit();
        }*/
    }
}
