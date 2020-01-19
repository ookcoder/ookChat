package com.ook.ookChat;

import com.ook.ookChat.components.MessageList;
import com.ook.ookChat.components.ToggleMessage;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.UnicastProcessor;

@Route
@Push
@StyleSheet("styles.css")
@Theme(value = Lumo.class, variant = Lumo.DARK)
@PWA(name = "Project Base for Vaadin Flow with Spring", shortName = "Project Base")
public class MainView extends VerticalLayout {

    private final UnicastProcessor<ChatMessage> publisher;
    private final Flux<ChatMessage> messages;
    private String username;

    public MainView(UnicastProcessor<ChatMessage> publisher, Flux<ChatMessage> messages) {
        this.publisher = publisher;
        this.messages = messages;
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        setSizeFull();
        addClassName("main-view");

        H1 header = new H1("Ook Chat");

        HorizontalLayout appBar = new HorizontalLayout();
        appBar.setWidthFull();
        appBar.add(header);
        add(appBar);

        askUsername();
    }

    private void askUsername() {
        HorizontalLayout usernameLayout = new HorizontalLayout();

        TextField usernameField = new TextField();
        Button startButton = new Button("Start Chatting", click -> {
            username = usernameField.getValue();
            remove(usernameLayout);
            showChat();
        });
        usernameLayout.add(usernameField, startButton);

        add(usernameLayout);
    }

    private void showChat() {
        MessageList messageList = new MessageList();
        expand(messageList);
        messages.subscribe(message -> {
            getUI().ifPresent(ui -> ui.access(() -> {
                ToggleMessage button = new ToggleMessage(message);
                button.addClickListener(click -> button.toggle());
                messageList.add(button);
            }));
        });

        HorizontalLayout inputLayout = new HorizontalLayout();
        TextField messageField = new TextField();

        Button sendButton = new Button("Send", click -> {
            if (!messageField.getValue().isEmpty()) {
                publisher.onNext(new ChatMessage(username, messageField.getValue()));
                messageField.clear();
                messageField.focus();
            }
        });
        messageField.focus();
        sendButton.addClickShortcut(Key.ENTER);

        inputLayout.addClassName("input-layout");
        inputLayout.add(messageField, sendButton);
        inputLayout.expand(messageField);

        add(messageList, inputLayout);
    }
}
