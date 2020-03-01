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

    @TableId(value = "timetable_id", type = IdType.INPUT)
    private Long timetableId;

    @TableField("timetable_course_id")
    private Long timetableCourseId;

    @TableField(exist = false)
    @ExcelField(value = "课程名称")
    private String timetableCourseName;

    @TableField("timetable_weekday")
    @NotBlank(message = "{required}")
    @ExcelField(value = "星期", writeConverterExp = "1=一,2=二,3=三,4=四,5=五,6=六,7=日")
    private String timetableWeekday;

    @TableField("timetable_index")
    @NotBlank(message = "{required}")
    @ExcelField(value = "课序")
    private String timetableIndex;

    @TableField("timetable_class_id")
    private Long timetableClassId;

    @TableField(exist = false)
    @ExcelField(value = "班级")
    private String timetableClassName;

    @TableField("timetable_classroom_id")
    private Long timetableClassroomId;

    @TableField(exist = false)
    @ExcelField(value = "教室")
    private String timetableClassroomName;

    @TableField(exist = false)
    @ExcelField(value = "教学楼")
    private String timetableBuildingName;

    @TableField("timetable_year")
    @NotBlank(message = "{required}")
    @ExcelField(value = "年份")
    private String timetableYear;

    @TableField("timetable_term")
    @NotBlank(message = "{required}")
    @ExcelField(value = "学期", writeConverterExp = "1=上学期,2=下学期")
    private String timetableTerm;

    @TableField("timetable_state")
    @NotBlank(message = "{required}")
    @ExcelField(value = "状态", writeConverterExp = "0=禁用,1=有效")
    private String timetableState;

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

    @TableField(exist = false)
    private String timetableCourseTeacherId;

    @TableField(exist = false)
    @ExcelField(value = "任课老师")
    private String timetableCourseTeacherName;

    @TableField(exist = false)
    private ArrayList<String> timetableIndexList;

    public Long getTimetableId() {
        return timetableId;
    }

    public void setTimetableId(Long timetableId) {
        this.timetableId = timetableId;
    }

    public Long getTimetableCourseId() {
        return timetableCourseId;
    }

    public void setTimetableCourseId(Long timetableCourseId) {
        this.timetableCourseId = timetableCourseId;
    }

    public String getTimetableCourseName() {
        return timetableCourseName;
    }

    public void setTimetableCourseName(String timetableCourseName) {
        this.timetableCourseName = timetableCourseName;
    }

    public String getTimetableWeekday() {
        return timetableWeekday;
    }

    public void setTimetableWeekday(String timetableWeekday) {
        this.timetableWeekday = timetableWeekday;
    }

    public String getTimetableIndex() {
        return timetableIndex;
    }

    public void setTimetableIndex(String timetableIndex) {
        this.timetableIndex = timetableIndex;
    }

    public Long getTimetableClassId() {
        return timetableClassId;
    }

    public void setTimetableClassId(Long timetableClassId) {
        this.timetableClassId = timetableClassId;
    }

    public String getTimetableClassName() {
        return timetableClassName;
    }

    public void setTimetableClassName(String timetableClassName) {
        this.timetableClassName = timetableClassName;
    }

    public Long getTimetableClassroomId() {
        return timetableClassroomId;
    }

    public void setTimetableClassroomId(Long timetableClassroomId) {
        this.timetableClassroomId = timetableClassroomId;
    }

    public String getTimetableClassroomName() {
        return timetableClassroomName;
    }

    public void setTimetableClassroomName(String timetableClassroomName) {
        this.timetableClassroomName = timetableClassroomName;
    }

    public String getTimetableYear() {
        return timetableYear;
    }

    public void setTimetableYear(String timetableYear) {
        this.timetableYear = timetableYear;
    }

    public String getTimetableTerm() {
        return timetableTerm;
    }

    public void setTimetableTerm(String timetableTerm) {
        this.timetableTerm = timetableTerm;
    }

    public String getTimetableState() {
        return timetableState;
    }

    public void setTimetableState(String timetableState) {
        this.timetableState = timetableState;
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

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getTimetableCourseTeacherId() {
        return timetableCourseTeacherId;
    }

    public void setTimetableCourseTeacherId(String timetableCourseTeacherId) {
        this.timetableCourseTeacherId = timetableCourseTeacherId;
    }

    public String getTimetableCourseTeacherName() {
        return timetableCourseTeacherName;
    }

    public void setTimetableCourseTeacherName(String timetableCourseTeacherName) {
        this.timetableCourseTeacherName = timetableCourseTeacherName;
    }

    public String getTimetableBuildingName() {
        return timetableBuildingName;
    }

    public void setTimetableBuildingName(String timetableBuildingName) {
        this.timetableBuildingName = timetableBuildingName;
    }

    public ArrayList<String> getTimetableIndexList() {
        return timetableIndexList;
    }

    public void setTimetableIndexList(ArrayList<String> timetableIndexList) {
        this.timetableIndexList = timetableIndexList;
    }
}
