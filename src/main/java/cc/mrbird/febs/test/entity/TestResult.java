package cc.mrbird.febs.test.entity;

import cc.mrbird.febs.common.converter.TimeConverter;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("T_TEST_RESULT")
@Excel("测验成绩表")
@KeySequence(value = "SEQ_T_TEST_RESULT")
public class TestResult implements Serializable {

    private static final long serialVersionUID = -6679236987661002378L;

    @TableId(value = "RESULT_ID", type = IdType.INPUT)
    private Long resultId;

    @TableField("RESULT_USER_ID")
    private Long resultUserId;

    @TableField(exist = false)
    @ExcelField(value = "用户名")
    private String resultUserName;

    @TableField("RESULT_PAPER_ID")
    private Long resultPaperId;

    @TableField(exist = false)
    @ExcelField(value = "试卷名称")
    private String resultPaperName;

    @TableField(exist = false)
    private Long resultCourseId;

    @TableField(exist = false)
    @ExcelField(value = "课程名称")
    private String resultCourseName;

    @TableField(exist = false)
    private Long resultCourseTeacherId;

    @TableField(exist = false)
    @ExcelField(value = "任课老师")
    private String resultCourseTeacherName;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("CREATE_TIME")
    @ExcelField(value = "测验时间", writeConverter = TimeConverter.class)
    private Date createTime;

    @TableField("RESULT_SCORE")
    @ExcelField(value = "分数")
    private Integer resultScore;

    @TableField(exist = false)
    private String createTimeFrom;

    @TableField(exist = false)
    private String createTimeTo;

    @TableField(exist = false)
    private String resultScoreFrom;

    @TableField(exist = false)
    private String resultScoreTo;
}
