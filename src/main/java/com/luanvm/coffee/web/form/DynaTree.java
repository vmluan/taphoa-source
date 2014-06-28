package com.luanvm.coffee.web.form;

import java.util.LinkedList;
import java.util.List;

public class DynaTree<T> {

	String title; // : null, // (required) Displayed name of the node (html is
					// allowed here)
	Integer key; // : null;, // May be used with activate(), select(), find(),
					// ...
	Boolean isFolder; // : false;, // Use a folder icon. Also the node is
						// expandable but not selectable.
	Boolean isLazy; // : false;, // Call onLazyRead(), when the node is expanded
					// for the first time to allow for delayed creation of
					// children.
	String tooltip; // : null;, // Show this popup text.
	String href; // : null;, // Added to the generated <a> tag.
	Boolean icon;// : null;, // Use a custom image (filename relative to
					// tree.options.imagePath). 'null' for default icon, 'false'
					// for no icon.
	String addClass; // : null;, // Class name added to the node's span tag.
	String noLink; // : false;, // Use <span> instead of <a> tag for this node
	Boolean activate; // : false;, // Initial active status.
	Boolean focus; // : false;, // Initial focused status.
	Boolean expand; // : false;, // Initial expanded status.
	Boolean select; // : false;, // Initial selected status.
	Boolean hideCheckbox; // : false;, // Suppress checkbox display for this
							// node.
	Boolean unselectable; // : false;, // Prevent selection.
	List<DynaTree<T>> children; // : null; // Array of child nodes.
	Integer parentId;
	Integer position;
	Integer level;

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getKey() {
		return key;
	}

	public void setKey(Integer key) {
		this.key = key;
	}

	public Boolean getIsFolder() {
		return isFolder;
	}

	public void setIsFolder(Boolean isFolder) {
		this.isFolder = isFolder;
	}

	public Boolean getIsLazy() {
		return isLazy;
	}

	public void setIsLazy(Boolean isLazy) {
		this.isLazy = isLazy;
	}

	public String getTooltip() {
		return tooltip;
	}

	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public Boolean getIcon() {
		return icon;
	}

	public void setIcon(Boolean icon) {
		this.icon = icon;
	}

	public String getAddClass() {
		return addClass;
	}

	public void setAddClass(String addClass) {
		this.addClass = addClass;
	}

	public String getNoLink() {
		return noLink;
	}

	public void setNoLink(String noLink) {
		this.noLink = noLink;
	}

	public Boolean getActivate() {
		return activate;
	}

	public void setActivate(Boolean activate) {
		this.activate = activate;
	}

	public Boolean getFocus() {
		return focus;
	}

	public void setFocus(Boolean focus) {
		this.focus = focus;
	}

	public Boolean getExpand() {
		return expand;
	}

	public void setExpand(Boolean expand) {
		this.expand = expand;
	}

	public Boolean getSelect() {
		return select;
	}

	public void setSelect(Boolean select) {
		this.select = select;
	}

	public Boolean getHideCheckbox() {
		return hideCheckbox;
	}

	public void setHideCheckbox(Boolean hideCheckbox) {
		this.hideCheckbox = hideCheckbox;
	}

	public Boolean getUnselectable() {
		return unselectable;
	}

	public void setUnselectable(Boolean unselectable) {
		this.unselectable = unselectable;
	}

	public List<DynaTree<T>> getChildren() {
		return children;
	}

	public void setChildren(List<DynaTree<T>> children) {
		this.children = children;
	}

	public DynaTree(Integer id, String name, Integer position, Integer level, Integer parentId) {
		this.key = id;
		this.title = name;
		this.position = position;
		this.level = level;
		this.parentId  = parentId;
		
		this.children = new LinkedList<DynaTree<T>>();
	}

	public DynaTree<T> addChild(Integer id, String text, Integer position, Integer level, Integer parentId) {
		DynaTree<T> childNode = new DynaTree<T>(id, text, position, level, parentId);
		this.children.add(childNode);
		return childNode;
	}

	public DynaTree<T> addChild(DynaTree<T> childNode) {
		this.children.add(childNode);
		return childNode;
	}

	public DynaTree(String title, Integer key, Boolean isFolder,
			Boolean isLazy, String tooltip, String href, Boolean icon,
			String addClass, String noLink, Boolean activate, Boolean focus,
			Boolean expand, Boolean select, Boolean hideCheckbox,
			Boolean unselectable, List<DynaTree<T>> children, Integer position, Integer level) {
		super();
		this.title = title;
		this.key = key;
		this.isFolder = isFolder;
		this.isLazy = isLazy;
		this.tooltip = tooltip;
		this.href = href;
		this.icon = icon;
		this.addClass = addClass;
		this.noLink = noLink;
		this.activate = activate;
		this.focus = focus;
		this.expand = expand;
		this.select = select;
		this.hideCheckbox = hideCheckbox;
		this.unselectable = unselectable;
		this.children = children;
		this.position = position;
		this.level = level;
	}
}
