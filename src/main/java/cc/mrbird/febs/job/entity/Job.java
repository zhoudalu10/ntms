package cc.mrbird.febs.job.entity;

import cc.mrbird.febs.common.annotation.IsCron;
import cc.mrbird.febs.common.converter.TimeConverter;
import com.baomidou.mybatisplus.annotation.*;
import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Data
@TableName("T_JOB")
@Excel("定时任务信息表")
@KeySequence(value = "SEQ_T_JOB")
public class Job implements Serializable {

    private static final long serialVersionUID = 400066840871805700L;

    /**
     * 任务调度参数 key
     */
    public static final String JOB_PARAM_KEY = "JOB_PARAM_KEY";

    public enum ScheduleStatus {
        /**
         * 正常
         */
        NORMAL("0"),
        /**
         * 暂停
         */
        PAUSE("1");

        private String value;

        ScheduleStatus(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    @TableId(value = "JOB_ID", type = IdType.INPUT)
    private Long jobId;

    @TableField("BEAN_NAME")
    @NotBlank(message = "{required}")
    @Size(max = 50, message = "{noMoreThan}")
    @ExcelField(value = "Bean名称")
    private String beanName;

    @TableField("METHOD_NAME")
    @NotBlank(message = "{required}")
    @Size(max = 50, message = "{noMoreThan}")
    @ExcelField(value = "方法名称")
    private String methodName;

    @TableField("PARAMS")
    @Size(max = 50, message = "{noMoreThan}")
    @ExcelField(value = "方法参数")
    private String params;

    @TableField("CRON_EXPRESSION")
    @NotBlank(message = "{required}")
    @IsCron(message = "{invalid}")
    @ExcelField(value = "Cron表达式")
    private String cronExpression;

    @TableField("STATUS")
    @ExcelField(value = "状态", writeConverterExp = "0=正常,1=暂停")
    private String status;

    @TableField("REMARK")
    @Size(max = 100, message = "{noMoreThan}")
    @ExcelField(value = "备注")
    private String remark;

    @TableField("CREATE_TIME")
    @ExcelField(value = "创建时间", writeConverter = TimeConverter.class)
    private Date createTime;

    private transient String createTimeFrom;
    private transient String createTimeTo;

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

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
