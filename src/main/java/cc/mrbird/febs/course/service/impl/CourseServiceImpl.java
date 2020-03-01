package cc.mrbird.febs.course.service.impl;

import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.utils.SortUtil;
import cc.mrbird.febs.course.entity.Course;
import cc.mrbird.febs.course.mapper.CourseMapper;
import cc.mrbird.febs.course.service.CourseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Override
    public IPage<Course> findCourseList(Course course, QueryRequest request) {
        Page<Course> page = new Page<>(request.getPageNum(), request.getPageSize());
        SortUtil.handlePageSort(request, page, "courseId", FebsConstant.ORDER_ASC, false);
        return this.baseMapper.findCourseList(page, course);
    }

    @Override
    public void addCourse(Course course) {
        save(course);
    }

    @Override
    public void updateCourse(Course course) {
        updateById(course);
    }

    @Override
    public void deleteCourses(String[] ids) {
        List<String> list = Arrays.asList(ids);
        removeByIds(list);
    }

    @Override
    public Course findById(String courseId) {
        return this.baseMapper.findById(courseId);
    }

    @Override
    public List<Course> findCourseListAll(Course course) {
        return this.baseMapper.findCourseListAll(course);
    }
}
