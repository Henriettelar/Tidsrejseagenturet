package Model;

public class TimePeriod {
    private int periodId;
    private String name;
    private String description;

    //Konstruktør
    public TimePeriod(int periodId, String name, String description) {
        this.periodId = periodId;
        this.name = name;
        this.description = description;
    }

    //gettere
    public int getPeriodId() {
        return periodId;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }

    //settere
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
