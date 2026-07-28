package com.kritsn.lld.designPattern;

import java.util.ArrayList;
import java.util.List;

// Step 1: Mediator interface
interface ChatMediator {
    void sendMessage(String message, User sender);
    void addUser(User user);
}

// Step 2: participant — talks ONLY to the mediator, never to other Users directly
abstract class User {
    protected ChatMediator mediator;
    protected String name;

    User(ChatMediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

    abstract void send(String message);
    abstract void receive(String message, String senderName);
}

class ChatUser extends User {
    ChatUser(ChatMediator mediator, String name) { super(mediator, name); }

    void send(String message) {
        System.out.println(name + " sends: " + message);
        mediator.sendMessage(message, this); // delegate coordination to mediator
    }
    void receive(String message, String senderName) {
        System.out.println(name + " received from " + senderName + ": " + message);
    }
}

// Step 3: concrete Mediator — holds all participants, contains coordination logic
class ChatRoomMediator implements ChatMediator {
    private final List<User> users = new ArrayList<>();

    public void addUser(User user) { users.add(user); }

    public void sendMessage(String message, User sender) {
        for (User user : users) {
            if (user != sender) { // don't echo back to the sender
                user.receive(message, sender.name);
            }
        }
    }
}

public class _19MediatorPatternDemo {
    public static void main(String[] args) {
        ChatRoomMediator chatRoom = new ChatRoomMediator();

        User alice = new ChatUser(chatRoom, "Alice");
        User bob = new ChatUser(chatRoom, "Bob");
        User charlie = new ChatUser(chatRoom, "Charlie");

        chatRoom.addUser(alice);
        chatRoom.addUser(bob);
        chatRoom.addUser(charlie);

        // Step 4: Alice never references Bob or Charlie directly
        alice.send("Hey everyone!");
    }
}