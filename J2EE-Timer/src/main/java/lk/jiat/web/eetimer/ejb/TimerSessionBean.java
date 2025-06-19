package lk.jiat.web.eetimer.ejb;

import jakarta.annotation.Resource;
import jakarta.ejb.*;
import lk.jiat.web.eetimer.timer.Task;

import java.io.Serializable;
import java.util.Collection;
import java.util.UUID;

@Stateless
public class TimerSessionBean {

    @Resource
    private TimerService timerService;

    public Task doTask(long time) {

        //timerService.createTimer(10000,"Clock"); // EJB 3.0/3.1
        //timerService.createIntervalTimer(1000,5000,new TimerConfig()); // EJB new versions


        TimerConfig timerConfig = new TimerConfig();
        String taskId = UUID.randomUUID().toString();
        Task task = new Task(taskId, "Test Task");
        timerConfig.setInfo(task);

        ScheduleExpression se = new ScheduleExpression();

        //         se.dayOfMonth("31");  maximum day of month
        //         works as if(se.dayOfWeek("MON")||se.dayOfMonth("17"))
        //         se.hour("8-12"); between 8 to 12 hours
        //         se.hour("*"); every hour
        //         se.hour("*/8");  every 8 hours
        //         se.dayOfMonth("L");  last day of month


        se.hour("*");
        se.minute("*");
        se.second("*/10");

//        se.hour("*");
//        se.minute("*");
//        se.second("*/10");

//        se.dayOfWeek("MON");
//        se.dayOfMonth("L");
//        se.hour("16");
//        se.minute("10-59");
//        se.second("10");

//        se.dayOfMonth("L");      //L represents the last day of month


//        System.out.println("Schedule Expression: " + se);
//        timerService.createSingleActionTimer(time, timerConfig);

//        se.hour("15-16");
//        se.minute("15-20");
//        se.second("10-20");

//        se.hour("15,17,23");
//        se.minute("10,20,30,40,50");
//        se.second("*/8,17");

        timerService.createCalendarTimer(se,timerConfig);

//        timerService.createSingleActionTimer(time,timerConfig);

        return task;


    }



    @Timeout
    public void timeOutTask1(Timer timer) {

        Serializable info = timer.getInfo();
        if (info instanceof Task) {
            Task task = (Task) info;
            System.out.println(task.getTaskName() + ": " + task.getTaskId() + " Task1 is done. ");
        }

//        System.out.println("Task timed out"+timer);

    }


//    @Timeout
//    public void timeOutTask2(Timer timer) {
//
//        Serializable info = timer.getInfo();
//        if (info instanceof Task) {
//            Task task = (Task) info;
//            System.out.println(task.getTaskName() + ": " + task.getTaskId() + " Task2 is done. ");
//        }
//
////        System.out.println("Task timed out"+timer);
//
//    }

    public void cancelTimer(String taskId) {

        for (Timer timer : timerService.getTimers()) {
            if (timer.getInfo() instanceof Task && ((Task) timer.getInfo()).getTaskId().equals(taskId)) {
                timer.cancel();
                System.out.println("Timer cancelled:" + taskId);
                break;
            }
        }
    }
}


