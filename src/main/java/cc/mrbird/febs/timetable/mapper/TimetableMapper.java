package cc.mrbird.febs.timetable.mapper;

import cc.mrbird.febs.timetable.entity.Timetable;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TimetableMapper extends BaseMapper<Timetable> {

    IPage<Timetable> findTimetableList(Page<Timetable> page, @Param("timetable") Timetable timetable);

    List<Timetable> judgeSameClassSameTimeState(@Param("timetable") Timetable timetable);

    List<Timetable> judgeSameClassSameTeacherState(@Param("timetable") Timetable timetable);

    Integer selectClassroomPeopleAmount(@Param("timetable") Timetable timetable);

    Integer selectClassroomPeopleAmountNow(@Param("timetable") Timetable timetable);

    List<Timetable> judgeSameClassroomSameTimeState(@Param("timetable") Timetable timetable);

    Integer selectCoursePeriodNow(@Param("timetable") Timetable timetable);

    Integer selectCoursePeriod(@Param("timetable") Timetable timetable);

    Timetable findById(@Param("timetableId") String timetableId);

    List<Timetable> findMyTimetableList(@Param("timetable") Timetable timetable);

    void deleteByCourseId(@Param("courseId") String courseId);
}
