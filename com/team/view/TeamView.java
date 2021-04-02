package project03.com.team.view;

import project03.com.team.domain.Employee;
import project03.com.team.domain.Programmer;
import project03.com.team.service.NameListService;
import project03.com.team.service.TeamException;
import project03.com.team.service.TeamService;

/**
 * @ClassName TeamView
 * @Description TODO
 * @Author CK
 * @Date 2021/4/2 9:36
 * @Version 1.0
 **/
public class TeamView {
    private NameListService listSvc = new NameListService();
    private TeamService teamSvc = new TeamService();

    public void enterMainMenu() {
        boolean flag = true;
        char menu = '0';
        while (flag) {
            if (menu != '1') {
                this.listAllEmployees();
            }
            System.out.print("1-团队列表 2-添加团队成员 3-删除团队成员 4-退出 请选择(1-4):");
            menu = TSUtility.readMenuSelection();
            switch (menu) {
                case '1':
                    this.getTeam();
                    break;
                case '2':
                    this.addMember();
                    break;
                case '3':
                    this.deleteMember();
                    break;
                case '4':
                    System.out.print("确认是否退出(Y/N):");
                    char isExit = TSUtility.readConfirmSelection();
                    if (isExit == 'Y') {
                        flag = false;
                    }
                    break;
            }
        }
    }

    private void listAllEmployees() {
        System.out.println("---------------------------开发团队调度软件---------------------------");
        System.out.println("ID\t姓名\t年龄\t工资\t职位\t状态\t奖金\t股票\t领用设备");
        if (listSvc.getAllEmployees() == null || listSvc.getAllEmployees().length == 0) {
            System.out.println("暂无团队数据...");
        } else {
            for (Employee employee : listSvc.getAllEmployees()) {
                System.out.println(employee);
            }
        }
        System.out.println("----------------------------------------------------------------------");
    }

    private void getTeam() {
        System.out.println("------------------------------团队成员列表------------------------------");
        if (this.teamSvc.getTeam() == null || this.teamSvc.getTeam().length == 0) {
            System.out.println("暂无团队数据...");
        } else {
            System.out.println("TID/ID\t姓名\t年龄\t工资\t职位\t奖金\t股票");
            for (Programmer programmer : this.teamSvc.getTeam()) {
                System.out.println(programmer.getDetailForTeam());
            }
        }
        System.out.println("----------------------------------------------------------------------");
    }

    private void addMember() {
        System.out.println("------------------------------添加成员------------------------------");
        System.out.print("请输入要添加的员工ID:");
        int id = TSUtility.readInt();
        try {
            Employee employees = listSvc.getEmployees(id);
            teamSvc.addMember(employees);
            System.out.println("添加成功");
            TSUtility.readReturn();
        } catch (TeamException e) {
            System.out.println("添加失败,原因："+e.getMessage());
            TSUtility.readReturn();
        }
        System.out.println("----------------------------------------------------------------------");
    }

    private void deleteMember() {
        System.out.println("------------------------------删除成员------------------------------");
        System.out.print("请输入要删除的TID:");
        int id = TSUtility.readInt();
        try {
            boolean find = false;
            Programmer p = null;
            for (Programmer programmer : teamSvc.getTeam()) {
                if(programmer.getMemberId() == id){
                    find = true;
                    p = programmer;
                    break;
                }
            }
            if(!find){
                throw new TeamException("没找到指定TID的成员");
            }
            System.out.print("确认删除{"+p.getName()+"}(Y/N)");
            char remove = TSUtility.readConfirmSelection();
            if(remove=='Y'){
                teamSvc.removeMember(id);
                System.out.println("删除成功");
                TSUtility.readReturn();
            }
        } catch (TeamException e) {
            System.out.println("删除失败,原因："+e.getMessage());
            TSUtility.readReturn();
        }
        System.out.println("----------------------------------------------------------------------");
    }

    public static void main(String[] args) {
        TeamView teamView = new TeamView();
        teamView.enterMainMenu();
    }

}
