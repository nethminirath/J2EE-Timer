package lk.jiat.web.eetimer.ejb;

import jakarta.ejb.Schedule;
import jakarta.ejb.Schedules;

public class AutoTimerSessionBean {

    //works in deployment time

    @Schedules({
            @Schedule(hour = "*", minute = "30", second = "10"),
            @Schedule(hour = "*", minute = "30", second = "10"),
            @Schedule(hour = "*", minute = "30", second = "10"),
            @Schedule(hour = "*", minute = "30", second = "10")
    })

    public void autoSchedule() {
        System.out.println("Auto scheduled");
    }
}
