package cc.mrbird.febs.note.service.impl;

import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.note.entity.NoteFile;
import cc.mrbird.febs.note.mapper.NoteFileMapper;
import cc.mrbird.febs.note.service.NoteFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Slf4j
@Service
public class NoteFileServiceImpl extends ServiceImpl<NoteFileMapper, NoteFile> implements NoteFileService {

    @Override
    public void addNoteFile(NoteFile noteFile) {
        save(noteFile);
    }

    @Override
    public NoteFile uploadFiles(MultipartFile file) {
        NoteFile noteFile = new NoteFile();

        if (file.isEmpty()) {
            noteFile.setNoteFileUploadState(NoteFile.FILE_IS_EMPTY);
            return noteFile;
        }
        if (file.getOriginalFilename() == null) {
            noteFile.setNoteFileUploadState(NoteFile.FILE_NAME_IS_EMPTY);
            return noteFile;
        }
        String[] originalFilename = file.getOriginalFilename().split("\\.");
        String fileName = originalFilename[0];
        int size = (int) file.getSize();
        log.info(fileName + "-->" + size);
        StringBuffer filePathName = new StringBuffer();
        filePathName.append(FebsConstant.NOTE_FILE_UPLOAD_PATH);
        filePathName.append(fileName);
        filePathName.append("_");
        filePathName.append(FebsUtil.getCurrentUser().getUserId());
        filePathName.append("_");
        filePathName.append(System.currentTimeMillis());
        filePathName.append(".");
        filePathName.append(originalFilename[originalFilename.length - 1]);
        File dest = new File(filePathName.toString());
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (Exception e) {
            noteFile.setNoteFileUploadState(NoteFile.FILE_SAVE_ERROR);
            return noteFile;
        }

        noteFile.setFilePath(filePathName.toString());
        noteFile.setNoteFileName(file.getOriginalFilename());
        addNoteFile(noteFile);
        noteFile.setNoteFileUploadState(NoteFile.SUCCESS);
        return noteFile;
    }
}
