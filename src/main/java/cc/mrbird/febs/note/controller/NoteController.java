package cc.mrbird.febs.note.controller;

import cc.mrbird.febs.note.entity.Note;
import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.note.entity.NoteFile;
import cc.mrbird.febs.note.service.NoteFileService;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.*;
import java.util.List;
import java.util.Map;

@Slf4j
@Validated
@RestController
@RequestMapping("note")
public class NoteController extends BaseController {

    @Autowired
    private NoteService noteService;

    @Autowired
    private NoteFileService noteFileService;

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
    public FebsResponse updateNote(@Valid Note note) {
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

    @RequestMapping("upload")
    @RequiresPermissions("note:add")
    @ControllerEndpoint(operation = "上传笔记附件", exceptionMessage = "上传笔记附件失败")
    public FebsResponse uploadFiles(MultipartFile file) {
        NoteFile noteFile = noteFileService.uploadFiles(file);
        switch (noteFile.getNoteFileUploadState()) {
            case NoteFile.FILE_IS_EMPTY:
                return new FebsResponse().fail().message("文件不能为空");
            case NoteFile.FILE_NAME_IS_EMPTY:
                return new FebsResponse().fail().message("文件名不能为空");
            case NoteFile.FILE_SAVE_ERROR:
                return new FebsResponse().fail().message("文件保存失败");
            case NoteFile.FILE_NAME_TOO_LONG:
                return new FebsResponse().fail().message("文件名过长");
            case NoteFile.SUCCESS:
                return new FebsResponse().success().data(noteFile);
            default:
                return new FebsResponse().fail().message("未知错误");

        }
    }

    @RequestMapping("download/{fileId}")
    @RequiresPermissions("note:view")
    @ControllerEndpoint(operation = "下载笔记附件", exceptionMessage = "下载笔记附件失败")
    public void downloadFiles(@NotBlank(message = "{required}") @PathVariable String fileId, HttpServletResponse response) {
        String filePath = noteFileService.findNoteFilePathById(fileId);
        File file = new File(filePath);
        if (!file.exists()) {
            return;
        }
        response.setContentType("application/force-download");
        response.addHeader("Content-Disposition", "attachment;fileName=" + file.getName());
        byte[] buffer = new byte[1024];
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        try {
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            OutputStream os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
