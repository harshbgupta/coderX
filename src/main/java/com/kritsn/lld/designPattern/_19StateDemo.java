package com.kritsn.lld.designPattern;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 24, 2026
 */
// Step 1: State interface
interface PaymentState {
    void processNextStep(PaymentContext context);
    void markSuccess(PaymentContext context);
    void markFailure(PaymentContext context);
    void refund(PaymentContext context);
    String getStatus();
}

// Step 2: concrete states

class InitiatedState implements PaymentState {
    public void processNextStep(PaymentContext context) {
        System.out.println("Sending payment request to gateway...");
        context.setState(new ProcessingState());
    }
    public void markSuccess(PaymentContext context) {
        System.out.println("Cannot mark success — payment not even processing yet.");
    }
    public void markFailure(PaymentContext context) {
        System.out.println("Cannot fail — payment not even processing yet.");
    }
    public void refund(PaymentContext context) {
        System.out.println("Cannot refund — payment never succeeded.");
    }
    public String getStatus() { return "INITIATED"; }
}

class ProcessingState implements PaymentState {
    public void processNextStep(PaymentContext context) {
        System.out.println("Already processing — please wait.");
    }
    public void markSuccess(PaymentContext context) {
        System.out.println("Gateway confirmed payment!");
        context.setState(new SuccessState());
    }
    public void markFailure(PaymentContext context) {
        context.incrementRetryCount();
        if (context.getRetryCount() >= 3) {
            System.out.println("Payment failed 3 times — giving up.");
            context.setState(new FailedState());
        } else {
            System.out.println("Payment failed, retry " + context.getRetryCount() + "/3...");
            context.setState(new InitiatedState()); // loop back — NOT a linear progression!
        }
    }
    public void refund(PaymentContext context) {
        System.out.println("Cannot refund — payment still processing.");
    }
    public String getStatus() { return "PROCESSING"; }
}

class SuccessState implements PaymentState {
    public void processNextStep(PaymentContext context) {
        System.out.println("Already succeeded — nothing to process.");
    }
    public void markSuccess(PaymentContext context) {
        System.out.println("Already marked successful.");
    }
    public void markFailure(PaymentContext context) {
        System.out.println("Cannot fail a payment that already succeeded.");
    }
    public void refund(PaymentContext context) {
        System.out.println("Processing refund...");
        context.setState(new RefundedState());
    }
    public String getStatus() { return "SUCCESS"; }
}

class FailedState implements PaymentState {
    public void processNextStep(PaymentContext context) {
        System.out.println("Cannot retry — max retries already exhausted. Raise a manual dispute.");
    }
    public void markSuccess(PaymentContext context) {
        System.out.println("Cannot succeed — payment permanently failed.");
    }
    public void markFailure(PaymentContext context) {
        System.out.println("Already in failed state.");
    }
    public void refund(PaymentContext context) {
        System.out.println("Cannot refund — payment never succeeded.");
    }
    public String getStatus() { return "FAILED"; }
}

class RefundedState implements PaymentState {
    public void processNextStep(PaymentContext context) {
        System.out.println("Cannot process — already refunded.");
    }
    public void markSuccess(PaymentContext context) {
        System.out.println("Cannot succeed — already refunded.");
    }
    public void markFailure(PaymentContext context) {
        System.out.println("Cannot fail — already refunded.");
    }
    public void refund(PaymentContext context) {
        System.out.println("Already refunded.");
    }
    public String getStatus() { return "REFUNDED"; }
}

// Step 3: Context — delegates behavior AND holds shared data states need (retryCount)
class PaymentContext {
    private PaymentState state = new InitiatedState();
    private int retryCount = 0;

    void setState(PaymentState state) { this.state = state; }
    void incrementRetryCount() { retryCount++; }
    int getRetryCount() { return retryCount; }

    void processPayment() { state.processNextStep(this); }
    void markSuccess() { state.markSuccess(this); }
    void markFailure() { state.markFailure(this); }
    void refund() { state.refund(this); }
    String getStatus() { return state.getStatus(); }
}

public class _19StateDemo {
    public static void main(String[] args) {
        PaymentContext payment = new PaymentContext();

        System.out.println("Status: " + payment.getStatus());
        payment.processPayment();               // Initiated -> Processing
        System.out.println("Status: " + payment.getStatus());

        payment.markFailure();                   // Processing -> back to Initiated (retry 1)
        System.out.println("Status: " + payment.getStatus());

        payment.processPayment();                // Initiated -> Processing again
        payment.markFailure();                    // retry 2
        payment.processPayment();
        payment.markFailure();                    // retry 3 -> exhausted -> Failed
        System.out.println("Status: " + payment.getStatus());

        payment.refund();                         // rejected — never succeeded
    }
}