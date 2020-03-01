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
@TableName("t_announcement")
@Excel("公告表")
@KeySequence(value = "seq_t_announcement")
public class Announcement implements Serializable {

    private static final long serialVersionUID = -2940936575877003941L;

    @TableId(value = "announcement_id", type = IdType.INPUT)
    private Long announcementId;

    @TableField("announcement_title")
    @Size(min = 1, max = 12, message = "{range}")
    @ExcelField(value = "公告标题")
    private String announcementTitle;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("create_time")
    @ExcelField(value = "创建时间", writeConverter = TimeConverter.class)
    private Date createTime;

    @TableField("create_user_id")
    private Long createUserId;

    @TableField(exist = false)
    @ExcelField(value = "创建人")
    private String createUserName;

    @TableField("announcement_content")
    @Size(min = 1, max = 1000, message = "{range}")
    @ExcelField(value = "公告内容")
    private String announcementContent;

    @TableField(exist = false)
    private String createTimeFrom;

    @TableField(exist = false)
    private String createTimeTo;

    @TableField("announcement_state")
    @NotBlank(message = "{required}")
    @ExcelField(value = "状态", writeConverterExp = "0=隐藏,1=展示")
    private String announcementState;

    public Long getAnnouncementId() {
        return announcementId;
    }

    public void setAnnouncementId(Long announcementId) {
        this.announcementId = announcementId;
    }

    public String getAnnouncementTitle() {
        return announcementTitle;
    }

    public void setAnnouncementTitle(String announcementTitle) {
        this.announcementTitle = announcementTitle;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public String getAnnouncementContent() {
        return announcementContent;
    }

    public void setAnnouncementContent(String announcementContent) {
        this.announcementContent = announcementContent;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getCreateTimeFrom() {
        return createTimeFrom;
    }

    public void setCreateTimeFrom(String createTimeFrom) {
        this.createTimeFrom = createTimeFrom;
    }

    public String getCreateTimeTo() {
        return createTimeTo;
    }

    public void setCreateTimeTo(String createTimeTo) {
        this.createTimeTo = createTimeTo;
    }

    public String getAnnouncementState() {
        return announcementState;
    }

    public void setAnnouncementState(String announcementState) {
        this.announcementState = announcementState;
    }
}
