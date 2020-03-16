package cc.mrbird.febs.job.entity;


import cc.mrbird.febs.common.converter.TimeConverter;
import com.baomidou.mybatisplus.annotation.*;
import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("T_JOB_LOG")
@Excel("调度日志信息表")
@KeySequence(value = "SEQ_T_JOB_LOG")
public class JobLog implements Serializable {

    private static final long serialVersionUID = -7114915445674333148L;
    // 任务执行成功
    public static final String JOB_SUCCESS = "0";
    // 任务执行失败
    public static final String JOB_FAIL = "1";

    @TableId(value = "LOG_ID", type = IdType.INPUT)
    private Long logId;

    @TableField("JOB_ID")
    private Long jobId;

    @TableField("BEAN_NAME")
    @ExcelField(value = "Bean名称")
    private String beanName;

    @TableField("METHOD_NAME")
    @ExcelField(value = "方法名称")
    private String methodName;

    @TableField("PARAMS")
    @ExcelField(value = "方法参数")
    private String params;

    @TableField("STATUS")
    @ExcelField(value = "状态", writeConverterExp = "0=成功,1=失败")
    private String status;

    @TableField("ERROR")
    @ExcelField(value = "异常信息")
    private String error;

    @TableField("TIMES")
    @ExcelField(value = "耗时（毫秒）")
    private Long times;

    @TableField("CREATE_TIME")
    @ExcelField(value = "执行时间", writeConverter = TimeConverter.class)
    private Date createTime;

    private transient String createTimeFrom;

    private transient String createTimeTo;
}
