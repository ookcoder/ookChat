package com.ook.ookChat.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class MessageList extends VerticalLayout {
    public MessageList() {
        addClassName("message-list");
    }

    @Override
    public void add(Component... components) {
        super.add(components);

        components[components.length - 1].getElement().callJsFunction("scrollIntoView");
    }
}
