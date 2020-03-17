package cc.mrbird.febs.course.service.impl;

import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.utils.SortUtil;
import cc.mrbird.febs.course.entity.Course;
import cc.mrbird.febs.course.mapper.CourseMapper;
import cc.mrbird.febs.course.service.CourseService;
import cc.mrbird.febs.test.service.PaperService;
import cc.mrbird.febs.test.service.QuestionService;
import cc.mrbird.febs.timetable.service.TimetableService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Autowired
    private TimetableService timetableService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private PaperService paperService;

    @Override
    public IPage<Course> findCourseList(Course course, QueryRequest request) {
        Page<Course> page = new Page<>(request.getPageNum(), request.getPageSize());
        SortUtil.handlePageSort(request, page, "courseId", FebsConstant.ORDER_ASC, false);
        return this.baseMapper.findCourseList(page, course);
    }

    @Override
    @Transactional
    public void addCourse(Course course) {
        save(course);
    }

    @Override
    @Transactional
    public void updateCourse(Course course) {
        updateById(course);
    }

    @Override
    @Transactional
    public void deleteCourses(String[] ids) {
        List<String> list = Arrays.asList(ids);
        list.forEach(courseId -> {
            timetableService.deleteByCourseId(courseId);
            questionService.deleteByCourseId(courseId);
            paperService.deleteByCourseId(courseId);
        });
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
