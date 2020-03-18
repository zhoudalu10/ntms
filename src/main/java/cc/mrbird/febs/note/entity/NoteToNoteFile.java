package cc.mrbird.febs.note.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("T_NOTE_TO_NOTE_FILE")
public class NoteToNoteFile implements Serializable {

    private static final long serialVersionUID = -3137412347256232563L;

    @TableField("NOTE_ID")
    private Long noteId;

    @TableField("FILE_ID")
    private Long fileId;

}
