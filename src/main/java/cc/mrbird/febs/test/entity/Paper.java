package cc.mrbird.febs.test.entity;

import cc.mrbird.febs.common.converter.TimeConverter;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@TableName("T_PAPER")
@Excel("试卷表")
@KeySequence(value = "SEQ_T_PAPER")
public class Paper implements Serializable {

    private static final long serialVersionUID = 8702281974996237442L;

    public static final String STATE_PROGRESS = "1";

    public static final String STATE_EXAMINATION = "2";

    public static final String STATE_END = "3";


    @TableId(value = "PAPER_ID", type = IdType.INPUT)
    private Long paperId;

    @TableField("PAPER_COURSE_ID")
    private Long paperCourseId;

    @TableField(exist = false)
    @ExcelField(value = "课程名称")
    private String paperCourseName;

    @TableField(exist = false)
    private Long paperCourseTeacherId;

    @TableField(exist = false)
    @ExcelField(value = "任课老师")
    private String paperCourseTeacherName;

    @TableField("PAPER_NAME")
    @NotBlank(message = "{required}")
    @Size(max = 20, message = "{noMoreThan}")
    @ExcelField(value = "试卷名称")
    private String paperName;

    @TableField("PAPER_TEST_TIME")
    @ExcelField(value = "测验时间")
    @Max(value = 999)
    private Integer paperTestTime;

    @TableField("PAPER_STATE")
    @ExcelField(value = "试卷状态", writeConverterExp = "1=组卷中,2=测验中,3=测验结束")
    private String paperState;

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

    @TableField(exist = false)
    @ExcelField(value = "试题数量")
    private String paperQuestionAmount;

    @TableField(exist = false)
    private List<Question> paperQuestionList;
}
