package cc.mrbird.febs.note.service.impl;

import cc.mrbird.febs.note.entity.Note;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.utils.SortUtil;
import cc.mrbird.febs.note.mapper.NoteMapper;
import cc.mrbird.febs.note.service.NoteFileService;
import cc.mrbird.febs.note.service.NoteService;
import cc.mrbird.febs.note.service.NoteToNoteFileService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


@Slf4j
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class NoteServiceImpl extends ServiceImpl<NoteMapper, Note> implements NoteService {

    @Autowired
    private NoteToNoteFileService noteToNoteFileService;

    @Autowired
    private NoteFileService noteFileService;

    @Override
    public IPage<Note> findNoteList(Note note, QueryRequest request) {
        Page<Note> page = new Page<>(request.getPageNum(), request.getPageSize());
        SortUtil.handlePageSort(request, page, "createTime", FebsConstant.ORDER_ASC, false);
        return this.baseMapper.findNoteList(page, note);
    }

    @Override
    public Note findById(String noteId) {
        Note note = this.baseMapper.findById(noteId);
        note.setNoteFileList(noteFileService.findNoteFileListByNoteId(noteId));
        return note;
    }

    @Override
    @Transactional
    public void addNote(Note note) {
        note.setCreateTime(new Date());
        save(note);
        note.getNoteToNoteFileList().forEach(noteToNoteFile -> {
            noteToNoteFile.setNoteId(note.getNoteId());
            noteToNoteFileService.addNoteToNoteFile(noteToNoteFile);
        });
    }

    @Override
    @Transactional
    public void updateNote(Note note) {
        updateById(note);
    }

    @Override
    @Transactional
    public void deleteNotes(String[] ids) {
        List<String> list = Arrays.asList(ids);
        this.removeByIds(list);
        list.forEach(noteId -> {
            noteFileService.deleteByNoteId(noteId);
            noteToNoteFileService.deleteByNoteId(noteId);
        });
    }
}
