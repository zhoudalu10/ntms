package cc.mrbird.febs.course.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@TableName("t_course")
@Excel("教室表")
@KeySequence(value = "seq_t_course")
public class Course implements Serializable {

    private static final long serialVersionUID = -6191126333629326690L;

    @TableId(value = "course_id", type = IdType.INPUT)
    private Long courseId;

    @TableField("course_name")
    @Size(min = 1, max = 20, message = "{range}")
    @ExcelField(value = "课程名称")
    private String courseName;

    @TableField("course_grade")
    @Max(value = 4)
    @ExcelField(value = "年级", writeConverterExp = "1=大一,2=大二,3=大三,4=大四")
    private Integer courseGrade;

    @TableField("course_term")
    @NotBlank(message = "{required}")
    @ExcelField(value = "学期", writeConverterExp = "1=上学期,2=下学期")
    private String courseTerm;

    @TableField("course_state")
    @NotBlank(message = "{required}")
    @ExcelField(value = "状态", writeConverterExp = "0=禁用,1=有效")
    private String courseState;

    @TableField("course_teacher_id")
    private Long courseTeacherId;

    @TableField("course_period")
    @Max(value = 99)
    @ExcelField(value = "周课时数")
    private Integer coursePeriod;

    @TableField(exist = false)
    @ExcelField(value = "任课老师")
    private String courseTeacherName;

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getCourseGrade() {
        return courseGrade;
    }

    public void setCourseGrade(Integer courseGrade) {
        this.courseGrade = courseGrade;
    }

    public String getCourseTerm() {
        return courseTerm;
    }

    public void setCourseTerm(String courseTerm) {
        this.courseTerm = courseTerm;
    }

    public String getCourseState() {
        return courseState;
    }

    public void setCourseState(String courseState) {
        this.courseState = courseState;
    }

    public Long getCourseTeacherId() {
        return courseTeacherId;
    }

    public void setCourseTeacherId(Long courseTeacherId) {
        this.courseTeacherId = courseTeacherId;
    }

    public Integer getCoursePeriod() {
        return coursePeriod;
    }

    public void setCoursePeriod(Integer coursePeriod) {
        this.coursePeriod = coursePeriod;
    }

    public String getCourseTeacherName() {
        return courseTeacherName;
    }

    public void setCourseTeacherName(String courseTeacherName) {
        this.courseTeacherName = courseTeacherName;
    }
}
