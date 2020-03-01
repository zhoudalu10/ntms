package cc.mrbird.febs.classroom.service;

import cc.mrbird.febs.classroom.entity.Classroom;
import cc.mrbird.febs.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

public interface ClassroomService {

    IPage<Classroom> findClassroomList(Classroom classroom, QueryRequest request);

    void addClassroom(Classroom classroom);

    void updateClassroom(Classroom classroom);

    void deleteClassrooms(String[] ids);

    Classroom findById(String classroomId);

    List<Classroom> findClassroomListAll(Classroom classroom);
}
