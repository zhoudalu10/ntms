package cc.mrbird.febs.job.entity;


import cc.mrbird.febs.common.converter.TimeConverter;
import com.baomidou.mybatisplus.annotation.*;
import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author MrBird
 */
@Data
@TableName("t_job_log")
@Excel("调度日志信息表")
@KeySequence(value = "seq_t_job_log")
public class JobLog implements Serializable {

    private static final long serialVersionUID = -7114915445674333148L;
    // 任务执行成功
    public static final String JOB_SUCCESS = "0";
    // 任务执行失败
    public static final String JOB_FAIL = "1";

    @TableId(value = "LOG_ID", type = IdType.INPUT)
    private Long logId;

    @TableField("job_id")
    private Long jobId;

    @TableField("bean_name")
    @ExcelField(value = "Bean名称")
    private String beanName;

    @TableField("method_name")
    @ExcelField(value = "方法名称")
    private String methodName;

    @TableField("params")
    @ExcelField(value = "方法参数")
    private String params;

    @TableField("status")
    @ExcelField(value = "状态", writeConverterExp = "0=成功,1=失败")
    private String status;

    @TableField("error")
    @ExcelField(value = "异常信息")
    private String error;

    @TableField("times")
    @ExcelField(value = "耗时（毫秒）")
    private Long times;

    @TableField("create_time")
    @ExcelField(value = "执行时间", writeConverter = TimeConverter.class)
    private Date createTime;

    private transient String createTimeFrom;
    private transient String createTimeTo;

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Long getTimes() {
        return times;
    }

    public void setTimes(Long times) {
        this.times = times;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateTimeFrom() {
        return createTimeFrom;
    }

    public void setCreateTimeFrom(String createTimeFrom) {
        this.createTimeFrom = createTimeFrom;
    }

    public String getCreateTimeTo() {
        return createTimeTo;
    }

    public void setCreateTimeTo(String createTimeTo) {
        this.createTimeTo = createTimeTo;
    }
}
