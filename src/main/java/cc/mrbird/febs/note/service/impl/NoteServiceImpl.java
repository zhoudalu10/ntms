package cc.mrbird.febs.note.service.impl;

import cc.mrbird.febs.note.entity.Note;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.utils.SortUtil;
import cc.mrbird.febs.note.mapper.NoteMapper;
import cc.mrbird.febs.note.service.NoteService;
import cc.mrbird.febs.note.service.NoteToNoteFileService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Slf4j
@Service
public class NoteServiceImpl extends ServiceImpl<NoteMapper, Note> implements NoteService {

    @Autowired
    private NoteToNoteFileService noteToNoteFileService;

    @Override
    public IPage<Note> findNoteList(Note note, QueryRequest request) {
        Page<Note> page = new Page<>(request.getPageNum(), request.getPageSize());
        SortUtil.handlePageSort(request, page, "createTime", FebsConstant.ORDER_ASC, false);
        return this.baseMapper.findNoteList(page, note);
    }

    @Override
    public Note findById(String noteId) {
        return null;
    }

    @Override
    public void addNote(Note note) {
        note.setCreateTime(new Date());
        save(note);
        note.getNoteToNoteFileList().forEach(noteToNoteFile -> {
            noteToNoteFile.setNoteId(note.getNoteId());
            noteToNoteFileService.addNoteToNoteFile(noteToNoteFile);
        });
    }

    @Override
    public void updateNote(Note note) {

    }

    @Override
    public void deleteNotes(String[] ids) {

    }
}
