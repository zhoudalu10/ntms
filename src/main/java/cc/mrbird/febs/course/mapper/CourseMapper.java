package cc.mrbird.febs.course.mapper;

import cc.mrbird.febs.course.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseMapper extends BaseMapper<Course> {

    IPage<Course> findCourseList(Page<Course> page, @Param("course") Course course);

    Course findById(@Param("courseId") String courseId);

    List<Course> findCourseListAll(@Param("course") Course course);
}
