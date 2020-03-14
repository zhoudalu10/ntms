package cc.mrbird.febs.timetable.controller;

import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.timetable.entity.Timetable;
import cc.mrbird.febs.timetable.service.TimetableService;
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
@RequestMapping("timetable")
public class TimetableController extends BaseController {

    @Autowired
    private TimetableService timetableService;

    @RequestMapping("list")
    @RequiresPermissions("timetable:view")
    public FebsResponse timetableList(Timetable timetable, QueryRequest request) {
        Map<String, Object> dataTable = getDataTable(timetableService.findTimetableList(timetable, request));
        return new FebsResponse().success().data(dataTable);
    }

    @RequestMapping("add")
    @RequiresPermissions("timetable:add")
    @ControllerEndpoint(operation = "新增课程安排", exceptionMessage = "新增课程安排失败")
    public FebsResponse addTimetable(@Valid Timetable timetable) {
        switch (timetableService.addTimetable(timetable)) {
            case SAME_CLASS_SAME_TIME_CLASH: {
                return new FebsResponse().fail().message("所选班级当前时段已有课程安排");
            }
            case SAME_TEACHER_SAME_TIME_CLASH: {
                return new FebsResponse().fail().message("所选课程任课老师当前时段已有课程安排");
            }
            case CLASSROOM_PEOPLE_AMOUNT_CLASH: {
                return new FebsResponse().fail().message("所选教室当前时段人数过多");
            }
            case SAME_CLASSROOM_SAME_TIME_CLASH: {
                return new FebsResponse().fail().message("所选教室当前时段已有课程安排");
            }
            case COURSE_PERIOD_CLASH: {
                return new FebsResponse().fail().message("所选课程安排课时达到上限");
            }
            case SUCCESS: {
                return new FebsResponse().success();
            }
            default: {
                return new FebsResponse().fail().message("未知错误");
            }

        }
    }

    @RequestMapping("delete/{timetableIds}")
    @RequiresPermissions("timetable:delete")
    @ControllerEndpoint(operation = "删除课程安排", exceptionMessage = "删除课程安排失败")
    public FebsResponse deleteTimetables(@NotBlank(message = "{required}") @PathVariable String timetableIds) {
        String[] ids = timetableIds.split(StringPool.COMMA);
        timetableService.deleteTimetables(ids);
        return new FebsResponse().success();
    }

    @RequestMapping("excel")
    @RequiresPermissions("timetable:export")
    @ControllerEndpoint(exceptionMessage = "导出Excel失败")
    public void export(QueryRequest queryRequest, Timetable timetable, HttpServletResponse response) {
        List<Timetable> timetables = timetableService.findTimetableList(timetable, queryRequest).getRecords();
        ExcelKit.$Export(Timetable.class, response).downXlsx(timetables, false);
    }

    @RequestMapping("myTimetable")
    @RequiresPermissions("myTimetable:view")
    public FebsResponse timetableMyTimetable(Timetable timetable) {
        return new FebsResponse().success().data(timetableService.findMyTimetableList(timetable));
    }

}
