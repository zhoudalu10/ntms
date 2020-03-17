package cc.mrbird.febs.note.controller;

import cc.mrbird.febs.note.entity.Note;
import cc.mrbird.febs.note.service.NoteService;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.utils.DateUtil;
import cc.mrbird.febs.common.utils.FebsUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("noteView")
@RequestMapping(FebsConstant.VIEW_PREFIX + "note")
public class ViewController extends BaseController {

    @Autowired
    private NoteService noteService;

    @GetMapping("note")
    @RequiresPermissions("note:view")
    public String note() {
        return FebsUtil.view("note/note");
    }

    @GetMapping("add")
    @RequiresPermissions("note:add")
    public String noteAdd() {
        return FebsUtil.view("note/noteAdd");
    }

    @GetMapping("update/{noteId}")
    @RequiresPermissions("note:update")
    public String noteUpdate(@PathVariable String noteId, Model model) {
        Note note = noteService.findById(noteId);
        model.addAttribute("note", note);
        return FebsUtil.view("note/noteUpdate");
    }

    @GetMapping("detail/{noteId}")
    @RequiresPermissions("note:view")
    public String noteDetail(@PathVariable String noteId, Model model) {
        Note note = noteService.findById(noteId);
        model.addAttribute("note", note);
        model.addAttribute("createTime", DateUtil.getDateFormat(note.getCreateTime(), DateUtil.FULL_TIME_SPLIT_PATTERN));
        return FebsUtil.view("note/noteDetail");
    }
}
