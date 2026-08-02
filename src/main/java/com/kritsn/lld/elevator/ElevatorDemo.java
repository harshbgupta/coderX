package com.kritsn.lld.elevator;

/*
    SCALE & CONFIGURATION:
    - Single elevator or multiple elevators?
    - How many floors? (5? 10? 100?)
    - How many passengers per elevator?

    FUNCTIONALITY:
    - Up/Down buttons only or specific floor buttons?
    - Emergency stop button?
    - Door open/close delays?
    - Priority handling (express elevator for higher floors)?

    CONSTRAINTS:
    - Weight capacity?
    - Speed per floor?
    - Time for door open/close?
    - Concurrent requests handling?

    EDGE CASES:
    - What if elevator is full?
    - Power failure handling?
    - Stuck between floors?
    - Multiple simultaneous calls?

    ----------------
    Entities:
    - Elevator
    - ElevatorState: IDLE, MOVING, DOOR_OPEN, DOOR_CLOSE
    - Floor
    - Direction: UP, DOWN, IDLE
    - Button (floor button)
    - CallButton (Up/Down Floor Button)
    - Door
    - ElevatorController
    - SCANController
    - ElevatorSystem

    Errors:
    - InvalidFloorException
    - ElevatorFullException
    - ElevatorStuckException
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

enum Direction {
    UP, DOWN, IDLE
}

enum ElevatorState {
    IDLE, MOVING, DOOR_OPEN, DOOR_CLOSE
}

class Button {
    private int floor;
    private boolean isPressed;

    public Button(int floor) {
        this.floor = floor;
        this.isPressed = false;
    }

    public void press() {
        this.isPressed = true;
    }

    public void release() {
        this.isPressed = false;
    }

    public boolean isPressed() {
        return isPressed;
    }

    public int getFloor() {
        return floor;
    }
}

class CallButton {
    private int floor;
    private boolean isPressed;

    public CallButton(int floor) {
        this.floor = floor;
        this.isPressed = false;
    }

    public void press() {
        this.isPressed = true;
    }

    public void release() {
        this.isPressed = false;
    }

    public boolean isPressed() {
        return isPressed;
    }

    public int getFloor() {
        return floor;
    }
}

class Door {
    private boolean isOpen;
    private long openDuration; // milliseconds

    public Door(long openDuration) {
        this.isOpen = false;
        this.openDuration = openDuration;
    }

    public void open() {
        isOpen = true;
        System.out.println("[DOOR] Opening (duration: " + openDuration + "ms)");
    }

    public void close() {
        isOpen = false;
        System.out.println("[DOOR] Closed");
    }

    public boolean isOpen() {
        return isOpen;
    }

    public long getOpenDuration() {
        return openDuration;
    }
}

class Floor {
    private int floorNumber;
    private Button upButton;
    private Button downButton;
    private boolean isElevatorHere;

    public Floor(int floorNumber) {
        this.floorNumber = floorNumber;
        this.upButton = new Button(floorNumber);
        this.downButton = new Button(floorNumber);
        this.isElevatorHere = false;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public Button getUpButton() {
        return upButton;
    }

    public Button getDownButton() {
        return downButton;
    }

    public void pressUp() {
        upButton.press();
        System.out.println("[FLOOR " + floorNumber + "] UP button pressed");
    }

    public void pressDown() {
        downButton.press();
        System.out.println("[FLOOR " + floorNumber + "] DOWN button pressed");
    }

    public boolean isElevatorPresent() {
        return isElevatorHere;
    }

    public void setElevatorPresent(boolean present) {
        isElevatorHere = present;
    }
}

class Elevator {
    private String id;
    private int currentFloor;
    private Direction direction;
    private ElevatorState state;
    private List<String> passengers;
    private int maxCapacity;
    private Door door;
    private Queue<Integer> destinationQueue;

    public Elevator(String id, int maxCapacity, int startFloor) {
        this.id = id;
        this.maxCapacity = maxCapacity;
        this.currentFloor = startFloor;
        this.direction = Direction.IDLE;
        this.state = ElevatorState.IDLE;
        this.passengers = new ArrayList<>();
        this.door = new Door(2000); // 2 second door open
        this.destinationQueue = new LinkedList<>();
    }

    /**
     * Move elevator UP one floor
     * State changes to MOVING
     */
    public void moveUp() {
        if (currentFloor < 5) { // Max 5 floors
            currentFloor++;
            direction = Direction.UP;
            state = ElevatorState.MOVING;
            System.out.println("[ELEVATOR " + id + "] Moving UP to floor " +
                    currentFloor);
        }
    }

    /**
     * Move elevator DOWN one floor
     */
    public void moveDown() {
        if (currentFloor > 0) { // Min 0 floors
            currentFloor--;
            direction = Direction.DOWN;
            state = ElevatorState.MOVING;
            System.out.println("[ELEVATOR " + id + "] Moving DOWN to floor " +
                    currentFloor);
        }
    }

    /**
     * Stop elevator
     * Open door at current floor
     * Passengers can exit
     */
    public void stop() {
        state = ElevatorState.IDLE;
        direction = Direction.IDLE;
        openDoor();
        System.out.println("[ELEVATOR " + id + "] Stopped at floor " +
                currentFloor);
    }

    /**
     * Open elevator door
     * Allow passengers to exit/enter
     */
    public void openDoor() {
        door.open();
        state = ElevatorState.DOOR_OPEN;
        System.out.println("[ELEVATOR " + id + "] Doors OPEN at floor " +
                currentFloor);
    }

    /**
     * Close elevator door
     * Elevator ready to move
     */
    public void closeDoor() {
        door.close();
        state = ElevatorState.DOOR_CLOSE;
        System.out.println("[ELEVATOR " + id + "] Doors CLOSE");
    }

    /**
     * Add destination floor to queue
     * Elevator will visit this floor
     */
    public void addDestination(int floor) throws InvalidFloorException {
        if (floor < 0 || floor > 5) {
            throw new InvalidFloorException("Invalid floor: " + floor);
        }
        destinationQueue.add(floor);
    }

    /**
     * Add passenger if elevator not full
     */
    public void addPassenger(String passengerId)
            throws ElevatorFullException {

        if (passengers.size() >= maxCapacity) {
            throw new ElevatorFullException("Elevator " + id + " is full");
        }

        passengers.add(passengerId);
        System.out.println("[ELEVATOR " + id + "] Passenger " + passengerId +
                " entered (Count: " + passengers.size() + ")");
    }

    /**
     * Remove passenger from elevator
     */
    public void removePassenger(String passengerId) {
        if (passengers.remove(passengerId)) {
            System.out.println("[ELEVATOR " + id + "] Passenger " +
                    passengerId + " exited (Count: " +
                    passengers.size() + ")");
        }
    }

    /**
     * Get next destination floor to visit
     */
    public Integer getNextDestination() {
        return destinationQueue.peek();
    }

    /**
     * Process movement toward next destination
     */
    public void processMovement() {
        if (destinationQueue.isEmpty()) {
            state = ElevatorState.IDLE;
            return;
        }

        int nextFloor = getNextDestination();

        if (currentFloor < nextFloor) {
            moveUp();
        } else if (currentFloor > nextFloor) {
            moveDown();
        } else {
            // Reached destination
            stop();
            destinationQueue.poll(); // Remove from queue
        }
    }

    // Getters
    public String getId() {
        return id;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public ElevatorState getState() {
        return state;
    }

    public int getPassengerCount() {
        return passengers.size();
    }

    public boolean isFull() {
        return passengers.size() >= maxCapacity;
    }

    public boolean hasDestinations() {
        return !destinationQueue.isEmpty();
    }

    public Door getDoor() {
        return door;
    }
}

class Request {
    private int sourceFloor;
    private int destinationFloor;
    private Direction direction;
    private long timestamp;
    private String passengerId;

    public Request(int sourceFloor, int destinationFloor,
                   Direction direction, String passengerId) {
        this.sourceFloor = sourceFloor;
        this.destinationFloor = destinationFloor;
        this.direction = direction;
        this.passengerId = passengerId;
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * Validate request
     * Source and destination must be different
     * Direction must match (UP if dest > source, DOWN if dest < source)
     */
    public boolean isValid() {
        if (sourceFloor == destinationFloor) {
            return false; // Already at destination
        }

        if (direction == Direction.UP && destinationFloor <= sourceFloor) {
            return false; // Going UP but destination is below
        }

        if (direction == Direction.DOWN && destinationFloor >= sourceFloor) {
            return false; // Going DOWN but destination is above
        }

        return true;
    }

    public int getSourceFloor() {
        return sourceFloor;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getPassengerId() {
        return passengerId;
    }
}

interface ElevatorController {
    /**
     * Strategy Pattern - Why: Different scheduling algorithms
     * Benefit: Easy to swap FCFS, SCAN, LOOK without changing Elevator
     * Trade-off: More classes, but cleaner design
     */
    Elevator selectElevator(List<Elevator> elevators, Request request);

    int getNextFloor(Elevator elevator);
}

class SCANController implements ElevatorController {

    /**
     * SCAN Algorithm - Why: Better than FCFS
     * Move in one direction until no more requests
     * Then change direction
     * Benefit: Reduces average wait time
     * Trade-off: More complex than FCFS
     */
    @Override
    public Elevator selectElevator(List<Elevator> elevators,
                                   Request request) {

        // Select elevator closest to source floor with room
        Elevator best = null;
        int minDistance = Integer.MAX_VALUE;

        for (Elevator e : elevators) {
            if (e.isFull()) continue;

            int distance = Math.abs(e.getCurrentFloor() -
                    request.getSourceFloor());

            if (distance < minDistance) {
                minDistance = distance;
                best = e;
            }
        }

        return best != null ? best : elevators.get(0);
    }

    @Override
    public int getNextFloor(Elevator elevator) {
        // Follow SCAN algorithm
        if (elevator.getNextDestination() != null) {
            return elevator.getNextDestination();
        }
        return elevator.getCurrentFloor();
    }
}

class ElevatorSystem {
    private List<Elevator> elevators;
    private List<Floor> floors;
    private ElevatorController controller;
    private Queue<Request> requestQueue;
    private int totalFloors;

    public ElevatorSystem(int totalFloors, int elevatorCount,
                          int capacity) {
        this.totalFloors = totalFloors;
        this.elevators = new ArrayList<>();
        this.floors = new ArrayList<>();
        this.controller = new SCANController(); // Default strategy
        this.requestQueue = new LinkedList<>();

        // Initialize floors
        for (int i = 0; i < totalFloors; i++) {
            floors.add(new Floor(i));
        }

        // Initialize elevators
        for (int i = 0; i < elevatorCount; i++) {
            elevators.add(new Elevator("E" + i, capacity, 0));
        }

        System.out.println("[SYSTEM] Initialized with " + totalFloors +
                " floors and " + elevatorCount + " elevators");
    }

    /**
     * User presses button on floor
     * Creates request and queues it
     */
    public void pressFloorButton(int floor, Direction direction)
            throws InvalidFloorException {

        if (floor < 0 || floor >= totalFloors) {
            throw new InvalidFloorException("Invalid floor: " + floor);
        }

        Floor floorObj = floors.get(floor);

        if (direction == Direction.UP) {
            floorObj.pressUp();
        } else {
            floorObj.pressDown();
        }

        // Create request (destination determined later)
        System.out.println("[SYSTEM] Button pressed at floor " + floor +
                " direction: " + direction);
    }

    /**
     * Passenger inside elevator presses button for specific floor
     * Elevator adds this floor to destination queue
     */
    public void pressElevatorButton(String elevatorId, int destinationFloor)
            throws InvalidFloorException, ElevatorFullException {

        for (Elevator e : elevators) {
            if (e.getId().equals(elevatorId)) {
                e.addDestination(destinationFloor);
                System.out.println("[SYSTEM] Floor " + destinationFloor +
                        " called in " + elevatorId);
                return;
            }
        }

        throw new InvalidFloorException("Elevator not found: " + elevatorId);
    }

    /**
     * Create request and assign to best elevator
     */
    public void createRequest(int sourceFloor, int destinationFloor,
                              String passengerId)
            throws InvalidFloorException, ElevatorFullException {

        Direction direction = destinationFloor > sourceFloor ?
                Direction.UP : Direction.DOWN;

        Request request = new Request(sourceFloor, destinationFloor,
                direction, passengerId);

        if (!request.isValid()) {
            throw new InvalidFloorException("Invalid request");
        }

        // Find best elevator using strategy
        Elevator best = controller.selectElevator(elevators, request);

        // Add destination to elevator
        best.addDestination(sourceFloor);
        best.addDestination(destinationFloor);

        System.out.println("[SYSTEM] Assigned " + passengerId +
                " from floor " + sourceFloor +
                " to floor " + destinationFloor +
                " in " + best.getId());
    }

    /**
     * Update all elevators (main loop)
     * Move elevators and process requests
     */
    public void updateElevators() {
        for (Elevator elevator : elevators) {
            if (elevator.hasDestinations()) {
                elevator.processMovement();
            } else {
                elevator.stop();
            }
        }
    }

    /**
     * Print status of all elevators
     */
    public void printStatus() {
        System.out.println("\n========== ELEVATOR STATUS ==========");
        for (Elevator e : elevators) {
            System.out.println("Elevator " + e.getId() +
                    ": Floor " + e.getCurrentFloor() +
                    " State: " + e.getState() +
                    " Passengers: " + e.getPassengerCount() +
                    " Direction: " + e.getDirection());
        }
        System.out.println("=====================================\n");
    }

    // Getters
    public List<Elevator> getElevators() {
        return elevators;
    }

    public List<Floor> getFloors() {
        return floors;
    }
}

class InvalidFloorException extends Exception {
    public InvalidFloorException(String msg) {
        super(msg);
    }
}

class ElevatorFullException extends Exception {
    public ElevatorFullException(String msg) {
        super(msg);
    }
}

class ElevatorStuckException extends Exception {
    public ElevatorStuckException(String msg) {
        super(msg);
    }
}

public class ElevatorDemo {
    public static void main(String[] args) throws Exception {
        System.out.println("========== ELEVATOR SYSTEM DEMO ==========\n");

        // Create system: 6 floors (0-5), 2 elevators, 5 capacity each
        ElevatorSystem system = new ElevatorSystem(6, 2, 5);
    }
}
