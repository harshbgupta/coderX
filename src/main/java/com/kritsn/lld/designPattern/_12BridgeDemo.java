package com.kritsn.lld.designPattern;
// Step 2: Implementor — the "how" (delivery mechanism)
interface MessageSender {
    void sendMessage(String to, String content);
}

// Step 3: concrete Implementors — one per channel
class EmailSenderBridge implements MessageSender {
    public void sendMessage(String to, String content) {
        System.out.println("Sending EMAIL to " + to + ": " + content);
    }
}
class SmsSenderBridge implements MessageSender {
    public void sendMessage(String to, String content) {
        System.out.println("Sending SMS to " + to + ": " + content);
    }
}

// Step 4: Abstraction — the "what," holds a reference to Implementor (the bridge)
abstract class Notification {
    protected final MessageSender sender; // composition, not inheritance — this IS the bridge

    Notification(MessageSender sender) { this.sender = sender; }

    abstract void notifyUser(String to);
}

// Step 5: Refined Abstractions — vary independently of MessageSender's variants
class OrderNotification extends Notification {
    OrderNotification(MessageSender sender) { super(sender); }
    void notifyUser(String to) {
        sender.sendMessage(to, "Your order has shipped!");
    }
}
class PromoNotification extends Notification {
    PromoNotification(MessageSender sender) { super(sender); }
    void notifyUser(String to) {
        sender.sendMessage(to, "Flat 20% off, today only!");
    }
}

public class _12BridgeDemo {
    public static void main(String[] args) {
        // Step 6: any Notification type + any MessageSender, composed freely at runtime
        Notification orderViaEmail = new OrderNotification(new EmailSenderBridge());
        Notification promoViaSms = new PromoNotification(new SmsSenderBridge());

        orderViaEmail.notifyUser("user@x.com");
        promoViaSms.notifyUser("9876543210");

        // switch the channel for the SAME notification type — no new class needed
        Notification orderViaSms = new OrderNotification(new SmsSenderBridge());
        orderViaSms.notifyUser("9876543210");
    }
}