package service;

import Pojo.Schedule;
import dao.ScheduleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private ScheduleMapper scheduleMapper;

    @Override
    public void insertSchedule(Schedule schedule) {
        scheduleMapper.insertschedule(schedule);

    }

    @Override
    public List<Schedule> selectAll() {
        List<Schedule> list = scheduleMapper.selectAll();
        return list;
    }

    @Override
    public void deleteSchedule(Integer id) {
        scheduleMapper.deleteschedule(id);

    }

    @Override
    public void updateSchedule(Schedule schedule) {
        scheduleMapper.updateschedule(schedule);

    }

    @Override
    public Schedule selectById(Integer id) {
        Schedule schedule = scheduleMapper.selectbyid(id);
        return schedule;
    }

}
