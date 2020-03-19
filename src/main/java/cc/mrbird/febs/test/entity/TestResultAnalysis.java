package cc.mrbird.febs.test.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("T_TEST_RESULT_ANALYSIS")
public class TestResultAnalysis implements Serializable {

    private static final long serialVersionUID = 1977088570450752531L;

    public static final String RESULT_IS_CORRECT = "1";

    public static final String RESULT_IS_WRONG = "0";

    @TableField("PAPER_ID")
    private Long paperId;

    @TableField("QUESTION_TYPE")
    private String questionType;

    @TableField("QUESTION_ID")
    private Long questionId;

    @TableField("QUESTION_SCORE")
    private Integer questionScore;

    @TableField("QUESTION_CONTENT")
    private String questionContent;

    @TableField("RESULT_USER_ID")
    private Long resultUserId;

    @TableField("RESULT_IS_CORRECT")
    private String resultIsCorrect;

    @TableField("RESULT_ID")
    private Long resultId;
}
