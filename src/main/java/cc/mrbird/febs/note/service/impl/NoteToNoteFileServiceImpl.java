package cc.mrbird.febs.note.service.impl;

import cc.mrbird.febs.note.entity.NoteToNoteFile;
import cc.mrbird.febs.note.mapper.NoteToNoteFileMapper;
import cc.mrbird.febs.note.service.NoteToNoteFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class NoteToNoteFileServiceImpl extends ServiceImpl<NoteToNoteFileMapper, NoteToNoteFile> implements NoteToNoteFileService {


    @Override
    @Transactional
    public void addNoteToNoteFile(NoteToNoteFile noteToNoteFile) {
        save(noteToNoteFile);
    }

    @Override
    @Transactional
    public void deleteByNoteId(String noteId) {
        this.baseMapper.deleteByNoteId(noteId);
    }
}
