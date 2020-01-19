package com.ook.ookChat.components;

import com.ook.ookChat.ChatMessage;
import com.ook.ookChat.Ook;
import com.vaadin.flow.component.button.Button;

public class ToggleMessage extends Button {
    private ChatMessage message;
    private boolean isEncrypted;

    public ToggleMessage(ChatMessage message) {
        super(message.getFrom() + ": " + Ook.encrypt(message.getMessage()));
        this.isEncrypted = true;
        this.message = message;
    }

    public void toggle() {
        if (isEncrypted) {
            this.setText(message.getFrom() + ": " + message.getMessage());
            isEncrypted = false;
        } else {
            this.setText(message.getFrom() + ": " + Ook.encrypt(message.getMessage()));
            isEncrypted = true;
        }
    }
}
