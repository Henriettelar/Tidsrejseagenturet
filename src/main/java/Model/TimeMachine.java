package Model;

public class TimeMachine {
    private int machineId;
    private String name;
    private int capacity;
    private boolean status;

    //Konstruktør
    public TimeMachine(int machineId, String name, int capacity){
        this.machineId = machineId;
        this.name = name;
        this.capacity = capacity;
        this.status = true;
    }

    //gettere
    public int getMachineId() {
        return machineId;
    }
    public String getName() {
        return name;
    }
    public int getCapacity() {
        return capacity;
    }
    public boolean getStatus() {
        return status;
    }

    //settere
    public void setName(String name) {
        this.name = name;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
}
