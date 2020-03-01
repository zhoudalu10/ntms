package cc.mrbird.febs.timetable.service;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.timetable.entity.Timetable;
import cc.mrbird.febs.timetable.entity.TimetableAddState;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

public interface TimetableService {

    IPage<Timetable> findTimetableList(Timetable timetable, QueryRequest request);

    TimetableAddState addTimetable(Timetable timetable);

    void deleteTimetables(String[] ids);

    Timetable findById(String timetableId);

    List<Timetable> findMyTimetableList(Timetable timetable);
}
