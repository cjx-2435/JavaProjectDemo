package project03.com.team.service;

import project03.com.team.domain.*;

public class TeamService {
    private static int counter = 1;//给memberId赋值
    private final int MAX_MEMBER = 5;//限制人数
    Programmer[] team = new Programmer[MAX_MEMBER];
    private int total = 0;

    public Programmer[] getTeam() {
        Programmer[] team = new Programmer[total];
        if (total > 0) System.arraycopy(this.team, 0, team, 0, total);
        return team;
    }

    /**
     * 成员已满，无法添加
     * 该成员不是开发人员，无法添加
     * 该员工已在本开发团队中
     * 该员工已是某团队成员
     * 该员正在休假，无法添加
     * 团队中至多只能有一名架构师
     * 团队中至多只能有两名设计师
     * 团队中至多只能有三名程序员
     *
     * @param e
     * @throws TeamException
     */
    public void addMember(Employee e) throws TeamException {
        if (this.total >= this.MAX_MEMBER) {
            throw new TeamException("成员已满,无法添加");
        }
        if (!(e instanceof Programmer)) {
            throw new TeamException("该成员不是开发成员,无法添加");
        }
        isValid((Programmer) e);
        switch (((Programmer) e).getStatus()) {
            case BUSY:
                throw new TeamException("该员工已是某团队成员");
            case VOCATION:
                throw new TeamException("该员工正在休假,无法添加");
        }
        ((Programmer) e).setMemberId(counter++);
        ((Programmer) e).setStatus(Status.BUSY);
        this.team[total++] = (Programmer) e;
    }

    public void removeMember(int memberId) throws TeamException {
        Programmer[] team = this.getTeam();
        for (int i = 0; i < this.total; i++) {
            if(team[i].getMemberId() == memberId){
                team[i].setStatus(Status.FREE);
                int index = i;
                while (index<this.total-1){
                    this.team[index] = this.team[++index];
                }
                this.team[--this.total] = null;
                return;
            }
        }
        throw new TeamException("找不到该成员,无法删除");
    }

    private void isValid(Programmer p) throws TeamException {
        Programmer[] team = this.getTeam();
        int programmer_num = 3;
        int designer_num = 2;
        int architect_num = 1;
        for (Programmer programmer : team) {
            if (p.getId() == programmer.getId()) {
                throw new TeamException("该员工已在本开发团队中");
            }
            if (programmer instanceof Architect) {
                architect_num--;
            } else if(programmer instanceof Designer){
                designer_num--;
            }else {
                programmer_num--;
            }
        }

        if (p instanceof Architect) {
            architect_num--;
            if (architect_num <0) throw new TeamException("团队中至多只能有一名架构师");
        } else if(p instanceof Designer){
            designer_num--;
            if(designer_num<0) throw new TeamException("团队中至多只能有两名设计师");
        }else {
            programmer_num--;
            if(programmer_num<0) throw new TeamException("团队中至多只能有三名程序员");
        }

    }
}
