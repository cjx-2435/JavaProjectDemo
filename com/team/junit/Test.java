package project03.com.team.junit;

import project03.com.team.domain.Employee;
import project03.com.team.domain.Programmer;
import project03.com.team.domain.Status;
import project03.com.team.service.NameListService;
import project03.com.team.service.TeamException;
import project03.com.team.service.TeamService;

import java.sql.ClientInfoStatus;

public class Test {
    @org.junit.Test
    public void test1(){
        NameListService list = new NameListService();
        Employee[] allEmployees = list.getAllEmployees();
        for (Employee allEmployee : allEmployees) {
            System.out.println(allEmployee);
        }
    }

    @org.junit.Test
    public void test2(){
        NameListService list = new NameListService();
        try {
            Employee employees = list.getEmployees(3);
            assert (employees.getId() == 3)&&(employees.getName().equals("李彦宏")&&(employees.getAge()==23));
            System.out.println("测试通过");
        } catch (TeamException e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void test3() throws TeamException {
        TeamService ts = new TeamService();
        NameListService list = new NameListService();
        ts.addMember(list.getEmployees(2));
        ts.addMember(list.getEmployees(4));
        ts.addMember(list.getEmployees(6));
        for (Programmer programmer : ts.getTeam()) {
            System.out.print(programmer.getMemberId()+" ");
            System.out.println(programmer);
        }
        ts.removeMember(2);
        ts.removeMember(3);
        ts.addMember(list.getEmployees(4));
        System.out.println("---------------------------------");
        for (Programmer programmer : ts.getTeam()) {
            System.out.print(programmer.getMemberId()+" ");
            System.out.println(programmer);
        }
        System.out.println("---------------------------------");
        for (Employee allEmployee : list.getAllEmployees()) {
            System.out.println(allEmployee);
        }
    }
}
