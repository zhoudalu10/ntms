package cc.mrbird.febs.note.controller;

import cc.mrbird.febs.note.entity.Note;
import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.note.service.NoteService;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping("note")
public class NoteController extends BaseController {

    @Autowired
    private NoteService noteService;

    @RequestMapping("list")
    @RequiresPermissions("note:view")
    public FebsResponse noteList(Note note, QueryRequest request) {
        Map<String, Object> dataTable = getDataTable(noteService.findNoteList(note, request));
        return new FebsResponse().success().data(dataTable);
    }

    @RequestMapping("add")
    @RequiresPermissions("note:add")
    @ControllerEndpoint(operation = "新增笔记", exceptionMessage = "新增笔记失败")
    public FebsResponse addNote(@RequestBody @Valid Note note) {
        noteService.addNote(note);
        return new FebsResponse().success();
    }

    @RequestMapping("update")
    @RequiresPermissions("note:update")
    @ControllerEndpoint(operation = "修改笔记", exceptionMessage = "修改笔记失败")
    public FebsResponse updateNote(@RequestBody @Valid Note note) {
        if (note.getNoteId() == null)
            throw new FebsException("笔记ID为空");
        noteService.updateNote(note);
        return new FebsResponse().success();
    }

    @RequestMapping("delete/{noteIds}")
    @RequiresPermissions("note:delete")
    @ControllerEndpoint(operation = "删除笔记", exceptionMessage = "删除笔记失败")
    public FebsResponse deleteNotes(@NotBlank(message = "{required}") @PathVariable String noteIds) {
        String[] ids = noteIds.split(StringPool.COMMA);
        noteService.deleteNotes(ids);
        return new FebsResponse().success();
    }

    @RequestMapping("excel")
    @RequiresPermissions("note:export")
    @ControllerEndpoint(exceptionMessage = "导出Excel失败")
    public void export(QueryRequest queryRequest, Note note, HttpServletResponse response) {
        List<Note> notes = noteService.findNoteList(note, queryRequest).getRecords();
        ExcelKit.$Export(Note.class, response).downXlsx(notes, false);
    }
}
