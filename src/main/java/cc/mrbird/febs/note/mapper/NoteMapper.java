package cc.mrbird.febs.note.mapper;

import cc.mrbird.febs.note.entity.Note;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

public interface NoteMapper extends BaseMapper<Note> {

    IPage<Note> findNoteList(Page<Note> page, @Param("note") Note note);

    Note findById(@Param("noteId") String noteId);
}
