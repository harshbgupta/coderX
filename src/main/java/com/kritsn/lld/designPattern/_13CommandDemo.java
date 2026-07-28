package com.kritsn.lld.designPattern;
import java.util.LinkedList;
import java.util.Queue;

// Step 1: Command interface
interface Command {
    void execute();
}

// Step 2: concrete Command — wraps the action + its data
class PlaceOrderCommand implements Command {
    private final String skuId;
    private final int qty;

    PlaceOrderCommand(String skuId, int qty) {
        this.skuId = skuId;
        this.qty = qty;
    }

    public void execute() {
        System.out.println("Placing order for " + skuId + " x" + qty);
    }
}

// Step 3: Invoker — queues commands, executes them independently of when they were created
class OrderCommandQueue {
    private final Queue<Command> queue = new LinkedList<>();

    void submit(Command command) { queue.add(command); }

    void processAll() {
        while (!queue.isEmpty()) {
            queue.poll().execute();
        }
    }
}

public class _13CommandDemo {
    public static void main(String[] args) {
        OrderCommandQueue commandQueue = new OrderCommandQueue();

        // Step 4: client just builds Commands, doesn't execute them directly
        commandQueue.submit(new PlaceOrderCommand("SKU1", 2));
        commandQueue.submit(new PlaceOrderCommand("SKU2", 1));

        System.out.println("Commands queued, not yet executed...");
        commandQueue.processAll(); // executed later, possibly on a different thread/worker
    }
}