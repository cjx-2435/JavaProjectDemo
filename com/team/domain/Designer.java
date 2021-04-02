package project03.com.team.domain;

public class Designer extends Programmer {
    private double bonus;

    public Designer() {
    }

    public Designer(int id, String name, int age, double salary, Equipment equipment, double bonus) {
        super(id, name, age, salary, equipment);
        this.bonus = bonus;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    @Override
    public String toString() {
        return this.getDetails() + "\t设计师\t"+this.getStatus()+"\t"+this.getBonus()+"\t\t\t"+this.getEquipment().getDescription();
    }

    @Override
    public String getDetailForTeam() {
        return this.getMemberId()+"/"+this.getId()+"\t\t" +this.getName()+"\t"
                +this.getAge()+"\t\t"+this.getSalary()+"\t设计师\t"+this.getBonus();
    }
}
