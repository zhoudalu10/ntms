package cc.mrbird.febs.timetable.controller;

import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.utils.DateUtil;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.timetable.entity.Timetable;
import cc.mrbird.febs.timetable.service.TimetableService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("timetableView")
@RequestMapping(FebsConstant.VIEW_PREFIX + "timetable")
public class ViewController extends BaseController {

    @Autowired
    private TimetableService timetableService;

    @GetMapping("timetable")
    @RequiresPermissions("timetable:view")
    public String timetable() {
        return FebsUtil.view("timetable/timetable");
    }

    @GetMapping("add")
    @RequiresPermissions("timetable:add")
    public String timetableAdd() {
        return FebsUtil.view("timetable/timetableAdd");
    }

    @GetMapping("detail/{timetableId}")
    @RequiresPermissions("timetable:view")
    public String timetableDetail(@PathVariable String timetableId, Model model) {
        Timetable timetable = timetableService.findById(timetableId);
        model.addAttribute("timetable", timetable);
        model.addAttribute("createTime", DateUtil.getDateFormat(timetable.getCreateTime(), DateUtil.FULL_TIME_SPLIT_PATTERN));
        return FebsUtil.view("timetable/timetableDetail");
    }

    @GetMapping("myTimetable")
    @RequiresPermissions("myTimetable:view")
    public String myTimetable() {
        return FebsUtil.view("timetable/myTimetable");
    }
}
