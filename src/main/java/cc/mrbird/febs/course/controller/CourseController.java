package cc.mrbird.febs.course.controller;

import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.course.entity.Course;
import cc.mrbird.febs.course.service.CourseService;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

@Slf4j
@Validated
@RestController
@RequestMapping("course")
public class CourseController extends BaseController {

    @Autowired
    private CourseService courseService;

    @RequestMapping("list")
    @RequiresPermissions("course:view")
    public FebsResponse courseList(Course course, QueryRequest request) {
        Map<String, Object> dataTable = getDataTable(courseService.findCourseList(course, request));
        return new FebsResponse().success().data(dataTable);
    }

    @RequestMapping("add")
    @RequiresPermissions("course:add")
    @ControllerEndpoint(operation = "新增课程", exceptionMessage = "新增课程失败")
    public FebsResponse addCourse(@Valid Course course) {
        courseService.addCourse(course);
        return new FebsResponse().success();
    }

    @RequestMapping("update")
    @RequiresPermissions("course:update")
    @ControllerEndpoint(operation = "修改课程", exceptionMessage = "修改课程失败")
    public FebsResponse updateCourse(@Valid Course course) {
        if (course.getCourseId() == null)
            throw new FebsException("课程ID为空");
        courseService.updateCourse(course);
        return new FebsResponse().success();
    }

    @RequestMapping("delete/{courseIds}")
    @RequiresPermissions("course:delete")
    @ControllerEndpoint(operation = "删除课程", exceptionMessage = "删除课程失败")
    public FebsResponse deleteCourses(@NotBlank(message = "{required}") @PathVariable String courseIds) {
        String[] ids = courseIds.split(StringPool.COMMA);
        courseService.deleteCourses(ids);
        return new FebsResponse().success();
    }

    @RequestMapping("excel")
    @RequiresPermissions("course:export")
    @ControllerEndpoint(exceptionMessage = "导出Excel失败")
    public void export(QueryRequest queryRequest, Course course, HttpServletResponse response) {
        List<Course> courses = courseService.findCourseList(course, queryRequest).getRecords();
        ExcelKit.$Export(Course.class, response).downXlsx(courses, false);
    }

    @RequestMapping("listAll")
    public FebsResponse courseListAll(Course course) {
        return new FebsResponse().success().data(courseService.findCourseListAll(course));
    }
}
