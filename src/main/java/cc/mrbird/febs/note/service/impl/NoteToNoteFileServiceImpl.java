package cc.mrbird.febs.note.service.impl;

import cc.mrbird.febs.note.entity.NoteToNoteFile;
import cc.mrbird.febs.note.mapper.NoteToNoteFileMapper;
import cc.mrbird.febs.note.service.NoteToNoteFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class NoteToNoteFileServiceImpl extends ServiceImpl<NoteToNoteFileMapper, NoteToNoteFile> implements NoteToNoteFileService {


    @Override
    public void addNoteToNoteFile(NoteToNoteFile noteToNoteFile) {
        save(noteToNoteFile);
    }
}
