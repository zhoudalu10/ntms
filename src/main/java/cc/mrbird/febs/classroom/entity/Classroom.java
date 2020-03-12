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
}
