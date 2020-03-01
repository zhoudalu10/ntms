package cc.mrbird.febs.classroom.controller;

import cc.mrbird.febs.classroom.entity.Classroom;
import cc.mrbird.febs.classroom.service.ClassroomService;
import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.exception.FebsException;
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
@RequestMapping("classroom")
public class ClassroomController extends BaseController {

    @Autowired
    private ClassroomService classroomService;

    @RequestMapping("list")
    @RequiresPermissions("classroom:view")
    public FebsResponse classroomList(Classroom classroom, QueryRequest request) {
        Map<String, Object> dataTable = getDataTable(classroomService.findClassroomList(classroom, request));
        return new FebsResponse().success().data(dataTable);
    }

    @RequestMapping("add")
    @RequiresPermissions("classroom:add")
    @ControllerEndpoint(operation = "新增教室", exceptionMessage = "新增教室失败")
    public FebsResponse addClassroom(@Valid Classroom classroom) {
        classroomService.addClassroom(classroom);
        return new FebsResponse().success();
    }

    @RequestMapping("update")
    @RequiresPermissions("classroom:update")
    @ControllerEndpoint(operation = "修改教室", exceptionMessage = "修改教室失败")
    public FebsResponse updateClassroom(@Valid Classroom classroom) {
        if (classroom.getClassroomId() == null)
            throw new FebsException("教室ID为空");
        classroomService.updateClassroom(classroom);
        return new FebsResponse().success();
    }

    @RequestMapping("delete/{classroomIds}")
    @RequiresPermissions("classroom:delete")
    @ControllerEndpoint(operation = "删除教室", exceptionMessage = "删除教室失败")
    public FebsResponse deleteClassrooms(@NotBlank(message = "{required}") @PathVariable String classroomIds) {
        String[] ids = classroomIds.split(StringPool.COMMA);
        classroomService.deleteClassrooms(ids);
        return new FebsResponse().success();
    }

    @RequestMapping("excel")
    @RequiresPermissions("classroom:export")
    @ControllerEndpoint(exceptionMessage = "导出Excel失败")
    public void export(QueryRequest queryRequest, Classroom classroom, HttpServletResponse response) {
        List<Classroom> classrooms = classroomService.findClassroomList(classroom, queryRequest).getRecords();
        ExcelKit.$Export(Classroom.class, response).downXlsx(classrooms, false);
    }

    @RequestMapping("listAll")
    public FebsResponse classroomListAll(Classroom classroom) {
        return new FebsResponse().success().data(classroomService.findClassroomListAll(classroom));
    }
}
