package cc.mrbird.febs.note.service;

import cc.mrbird.febs.note.entity.NoteFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface NoteFileService {

    void addNoteFile(NoteFile noteFile);

    NoteFile uploadFiles(MultipartFile file);

    List<NoteFile> findNoteFileListByNoteId(String noteId);

    String findNoteFilePathById(String fileId);

    void deleteByNoteId(String noteId);
}
