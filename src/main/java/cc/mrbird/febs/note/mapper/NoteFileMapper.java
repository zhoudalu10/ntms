package cc.mrbird.febs.note.mapper;

import cc.mrbird.febs.note.entity.NoteFile;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NoteFileMapper extends BaseMapper<NoteFile> {

    List<NoteFile> findNoteFileListByNoteId(@Param("noteId") String noteId);

    String findNoteFilePathById(@Param("fileId") String fileId);

    void deleteByNoteId(@Param("noteId") String noteId);
}
