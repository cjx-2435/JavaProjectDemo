package project03.com.team.service;

import project03.com.team.domain.*;

import java.util.Arrays;

import static project03.com.team.service.Data.*;

/**
 *
 */
public class NameListService {
    private Employee[] employees;

    public NameListService() {
        this.employees = new Employee[EMPLOYEES.length];
        for (int i = 0; i < EMPLOYEES.length; i++) {
            int type = Integer.parseInt(EMPLOYEES[i][0]);
            int id = Integer.parseInt(EMPLOYEES[i][1]);
            String name = EMPLOYEES[i][2];
            int age = Integer.parseInt(EMPLOYEES[i][3]);
            double salary = Double.parseDouble(EMPLOYEES[i][4]);
            Equipment equipment = null;
            double bonus = 0;
            switch (type) {
                case EMPLOYEE:
                    this.employees[i] = new Employee(id, name, age, salary);
                    break;
                case PROGRAMMER:
                    equipment = createEquipment(i);
                    this.employees[i] = new Programmer(id, name, age, salary, equipment);
                    break;
                case DESIGNER:
                    equipment = createEquipment(i);
                    bonus = Double.parseDouble(EMPLOYEES[i][5]);
                    this.employees[i] = new Designer(id, name, age, salary, equipment, bonus);
                    break;
                case ARCHITECT:
                    equipment = createEquipment(i);
                    bonus = Double.parseDouble(EMPLOYEES[i][5]);
                    int stock = Integer.parseInt(EMPLOYEES[i][6]);
                    this.employees[i] = new Architect(id, name, age, salary, equipment, bonus, stock);
                    break;
            }
        }
    }

    private Equipment createEquipment(int i) {
        Equipment equipment = null;
        int e = Integer.parseInt(EQUIPMENTS[i][0]);
        String model = EQUIPMENTS[i][1];
        switch (e) {
            case PC:
                equipment = new PC(model, EQUIPMENTS[i][2]);
                break;
            case NOTEBOOK:
                double price = Double.parseDouble(EQUIPMENTS[i][2]);
                equipment = new NoteBook(model, price);
                break;
            case PRINTER:
                equipment = new Printer(model, EQUIPMENTS[i][2]);
                break;
        }
        return equipment;
    }

    public Employee[] getAllEmployees() {
        return employees;
    }

    public Employee getEmployees(int id) throws TeamException {
        for (Employee employee : getAllEmployees()) {
            if(employee.getId()==id){
                return employee;
            }
        }
        throw new TeamException("找不到指定员工");
    }

    public void setEmployees(Employee[] employees) {
        this.employees = employees;
    }
}