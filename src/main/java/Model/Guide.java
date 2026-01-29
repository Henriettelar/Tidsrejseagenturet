package Model;

public class Guide {
    private int guideId;
    private String name;
    private String specialty;

    //Konstruktør
    public  Guide(int guideId, String name, String specialty) {
        this.guideId = guideId;
        this.name = name;
        this.specialty = specialty;
    }

    //gettere
    public int getGuideId() {
        return guideId;
    }
    public String getName() {
        return name;
    }
    public String getSpecialty() {
        return specialty;
    }

    //Settere
    public void setName(String name) {
        this.name = name;
    }
    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
}
