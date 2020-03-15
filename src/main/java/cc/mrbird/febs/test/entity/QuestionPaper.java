package cc.mrbird.febs.test.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("T_QUESTION_PAPER")
public class QuestionPaper implements Serializable {

    private static final long serialVersionUID = 872250111635189792L;

    @TableField("QUESTION_ID")
    private String questionId;

    @TableField("PAPER_ID")
    private String paperId;
}
