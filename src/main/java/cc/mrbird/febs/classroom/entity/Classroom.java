package cc.mrbird.febs.classroom.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@TableName("T_CLASSROOM")
@Excel("教室表")
@KeySequence(value = "SEQ_T_CLASSROOM")
public class Classroom implements Serializable {

    private static final long serialVersionUID = 8705483361127896763L;

    @TableId(value = "CLASSROOM_ID", type = IdType.INPUT)
    private Long classroomId;

    @TableField("BUILDING_NAME")
    @Size(min = 1, max = 8, message = "{range}")
    @ExcelField(value = "教学楼")
    private String buildingName;

    @TableField("CLASSROOM_NAME")
    @Size(min = 1, max = 8, message = "{range}")
    @ExcelField(value = "教室名称")
    private String classroomName;

    @TableField("CLASSROOM_STUDENT_AMOUNT")
    @Max(value = 999)
    @ExcelField(value = "教室人数")
    private Integer classroomStudentAmount;

    @TableField("CLASSROOM_STATE")
    @NotBlank(message = "{required}")
    @ExcelField(value = "状态", writeConverterExp = "0=禁用,1=有效")
    private String classroomState;

    @TableField(exist = false)
    private Integer classroomStudentAmountFrom;

    @TableField(exist = false)
    private Integer classroomStudentAmountTo;


    public Long getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(Long classroomId) {
        this.classroomId = classroomId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getClassroomName() {
        return classroomName;
    }

    public void setClassroomName(String classroomName) {
        this.classroomName = classroomName;
    }

    public Integer getClassroomStudentAmount() {
        return classroomStudentAmount;
    }

    public void setClassroomStudentAmount(Integer classroomStudentAmount) {
        this.classroomStudentAmount = classroomStudentAmount;
    }

    public Integer getClassroomStudentAmountFrom() {
        return classroomStudentAmountFrom;
    }

    public void setClassroomStudentAmountFrom(Integer classroomStudentAmountFrom) {
        this.classroomStudentAmountFrom = classroomStudentAmountFrom;
    }

    public Integer getClassroomStudentAmountTo() {
        return classroomStudentAmountTo;
    }

    public void setClassroomStudentAmountTo(Integer classroomStudentAmountTo) {
        this.classroomStudentAmountTo = classroomStudentAmountTo;
    }

    public String getClassroomState() {
        return classroomState;
    }

    public void setClassroomState(String classroomState) {
        this.classroomState = classroomState;
    }
}
