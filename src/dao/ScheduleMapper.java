package dao;

import Pojo.Schedule;

import java.util.List;

public interface ScheduleMapper {
    public void insertschedule(Schedule schedule);

    public List<Schedule> selectAll();

    public void deleteschedule(Integer id);

    public void updateschedule(Schedule schedule);

    public Schedule selectbyid(Integer id);
}
