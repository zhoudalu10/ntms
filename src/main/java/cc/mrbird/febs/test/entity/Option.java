package cc.mrbird.febs.test.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("T_OPTION")
@KeySequence(value = "SEQ_T_OPTION")
public class Option implements Serializable {

    private static final long serialVersionUID = 3693655293852917478L;

    @TableId(value = "OPTION_ID", type = IdType.INPUT)
    private Long optionId;

    @TableField("OPTION_QUESTION_ID")
    private Long optionQuestionId;

    @TableField("OPTION_CONTENT")
    private String optionContent;

    @TableField("OPTION_ISKEY")
    private String optionIsKey;
}
