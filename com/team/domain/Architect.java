package project03.com.team.domain;

public class Architect extends Designer {
    private int stock;

    public Architect() {
    }

    public Architect(int id, String name, int age, double salary, Equipment equipment, double bonus, int stock) {
        super(id, name, age, salary, equipment, bonus);
        this.stock = stock;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return  this.getDetails()+"\t架构师\t"+this.getStatus()+"\t"+this.getBonus()+"\t"+
                this.getStock()+"\t"+this.getEquipment().getDescription();
    }

    @Override
    public String getDetailForTeam() {
        return this.getMemberId()+"/"+this.getId()+"\t\t" +this.getName()+"\t"
                +this.getAge()+"\t\t"+this.getSalary()+"\t架构师\t"+this.getBonus()+"\t"+this.getStock();
    }
}
