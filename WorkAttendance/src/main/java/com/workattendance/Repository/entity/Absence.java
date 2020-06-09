package com.workattendance.Repository.entity;

//暂时只用于获取的接口

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*** mao***/
public  class Absence {

    private int type;
    private String start_time;
    private String end_time;
    private String emp_name;

    private  static final int OUTWORKTYPE = 0;

    public Absence(Leave l){

        this.type = l.getType();
        this.start_time = l.getStart_time();
        this.end_time = l.getEnd_time();
       this.emp_name = l.getEmp_name();
    }

    public Absence(GoOut o){

        this.emp_name = o.getEmp_name();
        this.start_time = o.getStart_time();
        this.end_time = o.getEnd_time();
        this.type = OUTWORKTYPE;
    }

    public static List<Absence> toAbsenceList(List list){
        if(list.size()==0){
            return null;
        }
        ArrayList<Absence> absenceList = new ArrayList<>();
        Iterator it = list.iterator();
        if(list.get(0) instanceof Leave){
            absenceList = LeaveToAbsence(list);
        }
        if(list.get(0) instanceof  GoOut){
            absenceList = goOutToAbsence(list);
        }
        return absenceList;
    }

    private static ArrayList<Absence> LeaveToAbsence(List<Leave> list){
        ArrayList<Absence> absences = new ArrayList<>();
        Iterator<Leave> it = list.iterator();
        while (it.hasNext()){
            absences.add(new Absence(it.next()));
        }
        return absences;
    }

    private static ArrayList<Absence> goOutToAbsence(List<GoOut> list){
        ArrayList<Absence> absences = new ArrayList<>();
        Iterator<GoOut> it = list.iterator();
        while (it.hasNext()){
            absences.add(new Absence(it.next()));
        }
        return absences;
    }

    public int getType() {
        return type;
    }

    public String getStart_time() {
        return start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public String getEmp_name() {
        return emp_name;
    }


}
