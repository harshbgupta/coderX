package com.kritsn.lld.designPattern;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 23, 2026
 */

public final class _02FactoryMethodDemo {

    public static void main(String[] args) {
        
        ///////////////////////////////////////////////////////////////////////////
        // First Example: Normal Contains Switch
        ///////////////////////////////////////////////////////////////////////////
        NotificationSender emailSender = NotificationFactory.getSender("EMAIL");
        emailSender.send("user@x.com", "Your order has shipped");  // trigger "EmailSender" -> 'send' method

        NotificationSender smsSender = NotificationFactory.getSender("SMS");
        smsSender.send("9876543210", "Your OTP is 4821"); // trigger "SmsSender" -> 'send' method

        System.out.println("---");
        ///////////////////////////////////////////////////////////////////////////
        // 2nd Example: the GoF Factory Method
        ///////////////////////////////////////////////////////////////////////////
        NotificationCreator emailCreator =  new EmailNotificationCreator();
        emailCreator.notifyUser("user@x.com","Your order has shipped"); // trigger "EmailSender" -> 'send' method

        NotificationCreator smsCreator =  new SmsNotificationCreator();
        smsCreator.notifyUser("9876543210","Your OTP is 4821"); // trigger "SmsSender" -> 'send' method
    }
}

interface NotificationSender {
    void send(String to, String message);
}

///////////////////////////////////////////////////////////////////////////
// First Example: Normal Contains Switch
///////////////////////////////////////////////////////////////////////////
class NotificationFactory {
    static NotificationSender getSender(String channel) {
        return switch (channel) {
            case "EMAIL" -> new EmailSender();
            case "SMS" -> new SmsSender();
            default -> throw new IllegalArgumentException("Unknown channel: " + channel);
        };
    }
}

class EmailSender implements NotificationSender {
    public void send(String to, String msg) {
        System.out.println("Email to " + to + ": " + msg);
    }
}

class SmsSender implements NotificationSender {
    public void send(String to, String msg) {
        System.out.println("SMS to " + to + ": " + msg);
    }
}

///////////////////////////////////////////////////////////////////////////
// 2nd Example the Gof FactoryMethod
///////////////////////////////////////////////////////////////////////////
abstract class NotificationCreator {
    abstract NotificationSender createSender(); // the actual "factory method"

    void notifyUser(String to, String msg) {
        NotificationSender sender = createSender();
        sender.send(to, msg);
    }
}
class EmailNotificationCreator extends NotificationCreator {
    NotificationSender createSender() { return new EmailSender(); }
}
class SmsNotificationCreator extends NotificationCreator {
    NotificationSender createSender() { return new SmsSender(); }
}