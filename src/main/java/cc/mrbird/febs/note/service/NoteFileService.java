package cc.mrbird.febs.note.service;

import cc.mrbird.febs.note.entity.NoteFile;
import org.springframework.web.multipart.MultipartFile;

public interface NoteFileService {

    void addNoteFile(NoteFile noteFile);

    NoteFile uploadFiles(MultipartFile file);
}
