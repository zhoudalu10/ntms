package cc.mrbird.febs.timetable.entity;

import cc.mrbird.febs.common.converter.TimeConverter;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

@Data
@TableName("T_TIMETABLE")
@Excel("课程安排表")
@KeySequence(value = "SEQ_T_TIMETABLE")
public class Timetable implements Serializable {

    private static final long serialVersionUID = -1605336187864905145L;

    @TableId(value = "TIMETABLE_ID", type = IdType.INPUT)
    private Long timetableId;

    @TableField("TIMETABLE_COURSE_ID")
    private Long timetableCourseId;

    @TableField(exist = false)
    @ExcelField(value = "课程名称")
    private String timetableCourseName;

    @TableField("TIMETABLE_WEEKDAY")
    @NotBlank(message = "{required}")
    @ExcelField(value = "星期", writeConverterExp = "1=一,2=二,3=三,4=四,5=五,6=六,7=日")
    private String timetableWeekday;

    @TableField("TIMETABLE_INDEX")
    @NotBlank(message = "{required}")
    @ExcelField(value = "课序")
    private String timetableIndex;

    @TableField("TIMETABLE_CLASS_ID")
    private Long timetableClassId;

    @TableField(exist = false)
    @ExcelField(value = "班级")
    private String timetableClassName;

    @TableField("TIMETABLE_CLASSROOM_ID")
    private Long timetableClassroomId;

    @TableField(exist = false)
    @ExcelField(value = "教室")
    private String timetableClassroomName;

    @TableField(exist = false)
    @ExcelField(value = "教学楼")
    private String timetableBuildingName;

    @TableField("TIMETABLE_YEAR")
    @NotBlank(message = "{required}")
    @ExcelField(value = "年份")
    private String timetableYear;

    @TableField("TIMETABLE_TERM")
    @NotBlank(message = "{required}")
    @ExcelField(value = "学期", writeConverterExp = "1=上学期,2=下学期")
    private String timetableTerm;

    @TableField("TIMETABLE_STATE")
    @NotBlank(message = "{required}")
    @ExcelField(value = "状态", writeConverterExp = "0=禁用,1=有效")
    private String timetableState;

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

    @TableField(exist = false)
    private String timetableCourseTeacherId;

    @TableField(exist = false)
    @ExcelField(value = "任课老师")
    private String timetableCourseTeacherName;

    @TableField(exist = false)
    private ArrayList<String> timetableIndexList;
}
