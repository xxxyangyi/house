package service;

import Pojo.Schedule;

import java.util.List;

public interface ScheduleService {
     void insertSchedule(Schedule schedule);

     List<Schedule> selectAll();

     void deleteSchedule(Integer id);

     void updateSchedule(Schedule schedule);

     Schedule selectById(Integer id);
}
