package cc.mrbird.febs.note.entity;

import cc.mrbird.febs.common.converter.TimeConverter;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@TableName("T_NOTE")
@Excel("笔记表")
@KeySequence(value = "SEQ_T_NOTE")
public class Note implements Serializable {

    private static final long serialVersionUID = 4898504958739755347L;

    @TableId(value = "NOTE_ID", type = IdType.INPUT)
    private Long noteId;

    @TableField("NOTE_COURSE_ID")
    private Long noteCourseId;

    @TableField(exist = false)
    @ExcelField(value = "课程名称")
    private String noteCourseName;

    @TableField("NOTE_TITLE")
    @Size(max = 30, message = "{noMoreThan}")
    @NotBlank(message = "{required}")
    @ExcelField(value = "笔记标题")
    private String noteTitle;

    @TableField("NOTE_CONTENT")
    @Size(max = 1000, message = "{noMoreThan}")
    @ExcelField(value = "笔记内容")
    private String noteContent;

    @TableField("CREATE_USER_ID")
    private Long createUserId;

    @TableField(exist = false)
    @ExcelField(value = "创建人")
    private String createUserName;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("CREATE_TIME")
    @ExcelField(value = "创建时间", writeConverter = TimeConverter.class)
    private Date createTime;

    @TableField("NOTE_STATE")
    @NotBlank(message = "{required}")
    @ExcelField(value = "状态", writeConverterExp = "0=隐藏,1=展示")
    private String noteState;

    @TableField(exist = false)
    private String noteIsBindFile;

    @TableField(exist = false)
    private String createTimeFrom;

    @TableField(exist = false)
    private String createTimeTo;

    @TableField(exist = false)
    private String noteStudentClassId;

    @TableField(exist = false)
    private List<NoteToNoteFile> noteToNoteFileList;

    @TableField(exist = false)
    private List<NoteFile> noteFileList;
}
