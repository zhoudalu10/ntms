package cc.mrbird.febs.note.service;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.note.entity.Note;
import com.baomidou.mybatisplus.core.metadata.IPage;

public interface NoteService {

    IPage<Note> findNoteList(Note note, QueryRequest request);

    Note findById(String noteId);

    void addNote(Note note);

    void updateNote(Note note);

    void deleteNotes(String[] ids);
}
