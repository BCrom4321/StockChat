package edu.howardcc.javaii;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class PrimaryController implements Initializable {
    @FXML
    private ListView<String> chatListView; // Assuming the list view items are strings (chat names)
    @FXML
    private TextArea chatHistory;
    @FXML
    private TextField messageField;
    @FXML
    private Button sendButton;

    private ChatModel currentChat; // Assuming you have a ChatModel class for handling chat data

    @FXML
    private void initialize() {
        // Initialize your controller here.
        // For example, you can add an event listener to the sendButton like this:
        sendButton.setOnAction(event -> sendMessage());
        // Add an event listener for the ListView to handle chat switching
        chatListView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> switchChat(newSelection));
    }

    private void sendMessage() {
        String message = messageField.getText();
        if (!message.trim().isEmpty() && currentChat != null) {
            currentChat.addMessage(message);
            // Clear the message field after sending
            messageField.clear();
            // Update chat history
            updateChatHistory();
        }
    }

    private void switchChat(String chatName) {
        // Here you would normally fetch the chat data based on the selected chat name.
        // For this example, let's assume we're creating a new chat each time we switch.
        currentChat = new ChatModel(chatName);
        // Update chat history for the new chat
        updateChatHistory();
    }

    private void updateChatHistory() {
        if (currentChat != null) {
            chatHistory.clear();
            for (String message : currentChat.getMessages()) {
                chatHistory.appendText(message + "\n");
            }
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}


