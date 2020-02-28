package awd.cloud.internet.app.smsy.utils;

import java.util.List;
public class TreeNode {
    private String id;
    private String parentId;
    private String text;
    private boolean hasChild;
    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isHasChild() {
		return hasChild;
	}
	public void setHasChild(boolean hasChild) {
		this.hasChild = hasChild;
	}
	public TreeNode(String id, String parentId, String text) {
        this.id = id;
        this.parentId = parentId;
        this.text = text;
    }
}
