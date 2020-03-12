package cc.mrbird.febs.test.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wuwenze.poi.annotation.Excel;
import lombok.Data;

@Data
@TableName("T_TIMETABLE")
@Excel("课程安排表")
@KeySequence(value = "SEQ_T_TIMETABLE")
public class Question {

}
