package cc.mrbird.febs.announcement.entity;

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

@Data
@TableName("T_ANNOUNCEMENT")
@Excel("公告表")
@KeySequence(value = "SEQ_T_ANNOUNCEMENT")
public class Announcement implements Serializable {

    private static final long serialVersionUID = -2940936575877003941L;

    @TableId(value = "ANNOUNCEMENT_ID", type = IdType.INPUT)
    private Long announcementId;

    @TableField("ANNOUNCEMENT_TITLE")
    @Size(min = 1, max = 12, message = "{range}")
    @ExcelField(value = "公告标题")
    private String announcementTitle;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("CREATE_TIME")
    @ExcelField(value = "创建时间", writeConverter = TimeConverter.class)
    private Date createTime;

    @TableField("CREATE_USER_ID")
    private Long createUserId;

    @TableField(exist = false)
    @ExcelField(value = "创建人")
    private String createUserName;

    @TableField("ANNOUNCEMENT_CONTENT")
    @Size(min = 1, max = 1000, message = "{range}")
    @ExcelField(value = "公告内容")
    private String announcementContent;

    @TableField(exist = false)
    private String createTimeFrom;

    @TableField(exist = false)
    private String createTimeTo;

    @TableField("ANNOUNCEMENT_STATE")
    @NotBlank(message = "{required}")
    @ExcelField(value = "状态", writeConverterExp = "0=隐藏,1=展示")
    private String announcementState;
}
