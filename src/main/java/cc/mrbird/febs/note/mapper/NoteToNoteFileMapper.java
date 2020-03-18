package cc.mrbird.febs.note.mapper;

import cc.mrbird.febs.note.entity.NoteToNoteFile;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface NoteToNoteFileMapper extends BaseMapper<NoteToNoteFile> {

    void deleteByNoteId(@Param("noteId") String noteId);
}
