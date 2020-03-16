package cc.mrbird.febs.classroom.controller;

import cc.mrbird.febs.classroom.entity.Classroom;
import cc.mrbird.febs.classroom.service.ClassroomService;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.utils.FebsUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("classroomView")
@RequestMapping(FebsConstant.VIEW_PREFIX + "classroom")
public class ViewController extends BaseController {

    @Autowired
    private ClassroomService classroomService;

    @GetMapping("classroom")
    @RequiresPermissions("classroom:view")
    public String classroom() {
        return FebsUtil.view("classroom/classroom");
    }

    @GetMapping("add")
    @RequiresPermissions("classroom:add")
    public String classroomAdd() {
        return FebsUtil.view("classroom/classroomAdd");
    }

    @GetMapping("update/{classroomId}")
    @RequiresPermissions("classroom:update")
    public String classroomUpdate(@PathVariable String classroomId, Model model) {
        Classroom classroom = classroomService.findById(classroomId);
        model.addAttribute("classroom", classroom);
        return FebsUtil.view("classroom/classroomUpdate");
    }

    @GetMapping("detail/{classroomId}")
    @RequiresPermissions("classroom:view")
    public String classroomDetail(@PathVariable String classroomId, Model model) {
        Classroom classroom = classroomService.findById(classroomId);
        model.addAttribute("classroom", classroom);
        return FebsUtil.view("classroom/classroomDetail");
    }
}
