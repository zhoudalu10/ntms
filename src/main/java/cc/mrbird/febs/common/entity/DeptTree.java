package cc.mrbird.febs.common.entity;

import cc.mrbird.febs.system.entity.Dept;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class DeptTree<T> implements Serializable {

    private static final long serialVersionUID = 7681873362531265829L;

    private String id;
    private String icon;
    private String href;
    private String name;
    private Map<String, Object> state;
    private boolean checked = false;
    private Map<String, Object> attributes;
    private List<DeptTree<T>> children;
    private String parentId;
    private boolean hasParent = false;
    private boolean hasChild = false;

    private Dept data;

    public void initChildren(){
        this.children = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Object> getState() {
        return state;
    }

    public void setState(Map<String, Object> state) {
        this.state = state;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public List<DeptTree<T>> getChildren() {
        return children;
    }

    public void setChildren(List<DeptTree<T>> children) {
        this.children = children;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public boolean isHasParent() {
        return hasParent;
    }

    public void setHasParent(boolean hasParent) {
        this.hasParent = hasParent;
    }

    public boolean isHasChild() {
        return hasChild;
    }

    public void setHasChild(boolean hasChild) {
        this.hasChild = hasChild;
    }

    public Dept getData() {
        return data;
    }

    public void setData(Dept data) {
        this.data = data;
    }
}
