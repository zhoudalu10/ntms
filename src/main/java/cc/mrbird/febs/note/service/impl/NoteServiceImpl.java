package cc.mrbird.febs.note.service.impl;

import cc.mrbird.febs.note.entity.Note;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.utils.SortUtil;
import cc.mrbird.febs.note.mapper.NoteMapper;
import cc.mrbird.febs.note.service.NoteService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class NoteServiceImpl extends ServiceImpl<NoteMapper, Note> implements NoteService {

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

    }

    @Override
    public void updateNote(Note note) {

    }

    @Override
    public void deleteNotes(String[] ids) {

    }
}
