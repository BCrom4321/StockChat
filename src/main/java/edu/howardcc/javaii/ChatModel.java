package edu.howardcc.javaii;
import java.util.ArrayList;
import java.util.List;

public class ChatModel {
    private String chatName; // The name of the chat
    private List<String> messages; // The messages in the chat

    public ChatModel(String chatName) {
        this.chatName = chatName;
        this.messages = new ArrayList<>();
    }

    public String getChatName() {
        return chatName;
    }

    public List<String> getMessages() {
        return new ArrayList<>(messages); // Return a copy of messages to maintain encapsulation
    }

    public void addMessage(String message) {
        this.messages.add(message);
    }
}

