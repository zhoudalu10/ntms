package cc.mrbird.febs.common.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class QueryRequest implements Serializable {

    private static final long serialVersionUID = -4869594085374385813L;
    // 当前页面数据量
    private int pageSize = 10;
    // 当前页码
    private int pageNum = 1;
    // 排序字段
    private String field;
    // 排序规则，asc升序，desc降序
    private String order;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
