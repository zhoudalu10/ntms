package cc.mrbird.febs.note.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("T_NOTE_FILE")
@KeySequence(value = "SEQ_T_NOTE_FILE")
public class NoteFile implements Serializable {

    private static final long serialVersionUID = -2576610012507403992L;

    public static final String SUCCESS = "0";

    public static final String FILE_IS_EMPTY = "1";

    public static final String FILE_NAME_IS_EMPTY = "2";

    public static final String FILE_SAVE_ERROR = "3";

    public static final String FILE_NAME_TOO_LONG = "4";

    @TableId(value = "FILE_ID", type = IdType.INPUT)
    private Long fileId;

    @TableField("FILE_PATH")
    private String filePath;

    @TableField(exist = false)
    private String noteFileUploadState;

    @TableField("FILE_NAME")
    private String noteFileName;
}
