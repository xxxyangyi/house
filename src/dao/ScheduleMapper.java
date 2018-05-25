package dao;

import Pojo.Schedule;

import java.util.List;

public interface ScheduleMapper {
    void insertSchedule(Schedule schedule);

    List<Schedule> selectAll();

    void deleteSchedule(Integer id);

    void updateSchedule(Schedule schedule);

    Schedule selectById(Integer id);
}
