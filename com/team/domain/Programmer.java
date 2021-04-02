package project03.com.team.domain;

public class Programmer extends Employee {
    private int memberId;
    private Status status = Status.FREE;
    private Equipment equipment;

    public Programmer() {
    }

    public Programmer(int id, String name, int age, double salary, Equipment equipment) {
        super(id, name, age, salary);
        this.equipment = equipment;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    @Override
    public String toString() {
        return this.getDetails() + "\t程序员\t"+this.getStatus()+"\t\t\t\t\t"+this.getEquipment().getDescription();
    }

    public String getDetailForTeam(){
        return this.getMemberId()+"/"+this.getId()+"\t\t" +this.getName()+"\t"
                +this.getAge()+"\t\t"+this.getSalary()+"\t程序员";
    }
}
