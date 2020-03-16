package cc.mrbird.febs.course.controller;

import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.course.entity.Course;
import cc.mrbird.febs.course.service.CourseService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("courseView")
@RequestMapping(FebsConstant.VIEW_PREFIX + "course")
public class ViewController extends BaseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("course")
    @RequiresPermissions("course:view")
    public String course() {
        return FebsUtil.view("course/course");
    }

    @GetMapping("add")
    @RequiresPermissions("course:add")
    public String courseAdd() {
        return FebsUtil.view("course/courseAdd");
    }

    @GetMapping("update/{courseId}")
    @RequiresPermissions("course:update")
    public String courseUpdate(@PathVariable String courseId, Model model) {
        Course course = courseService.findById(courseId);
        model.addAttribute("course", course);
        return FebsUtil.view("course/courseUpdate");
    }

    @GetMapping("detail/{courseId}")
    @RequiresPermissions("course:view")
    public String courseDetail(@PathVariable String courseId, Model model) {
        Course course = courseService.findById(courseId);
        model.addAttribute("course", course);
        return FebsUtil.view("course/courseDetail");
    }
}
