package cc.mrbird.febs.test.entity;

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
@TableName("T_QUESTION")
@Excel("试题表")
@KeySequence(value = "SEQ_T_QUESTION")
public class Question implements Serializable {

    private static final long serialVersionUID = -7030891330168331046L;

    @TableId(value = "QUESTION_ID", type = IdType.INPUT)
    private Long questionId;

    @TableField("QUESTION_COURSE_ID")
    private Long questionCourseId;

    @TableField(exist = false)
    @ExcelField(value = "课程名称")
    private String questionCourseName;

    @TableField("QUESTION_CONTENT")
    @NotBlank(message = "{required}")
    @Size(max = 50, message = "{noMoreThan}")
    @ExcelField(value = "试题内容")
    private String questionContent;

    @TableField("QUESTION_SCORE")
    @NotBlank(message = "{required}")
    @ExcelField(value = "分值")
    private String questionScore;

    @TableField("QUESTION_TYPE")
    @NotBlank(message = "{required}")
    @ExcelField(value = "类型", writeConverterExp = "1=单选,2=判断,3=多选")
    private String questionType;

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
    private String questionIsBindPaper;

    @TableField(exist = false)
    private List<Option> optionList;

    @TableField(exist = false)
    private List<Paper> bindingPaperList;

    @TableField(exist = false)
    private Long bindingPaperId;
}
