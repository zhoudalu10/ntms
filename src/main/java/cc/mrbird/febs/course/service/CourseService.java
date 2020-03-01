package cc.mrbird.febs.course.service;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.course.entity.Course;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

public interface CourseService {

    IPage<Course> findCourseList(Course course, QueryRequest request);

    void addCourse(Course course);

    void updateCourse(Course course);

    void deleteCourses(String[] ids);

    Course findById(String courseId);

    List<Course> findCourseListAll(Course course);
}
