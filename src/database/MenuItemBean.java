package database;

public class MenuItemBean extends Bean {

	private static final long serialVersionUID = 1L;
	private static final String table = "menuitem";

	private long menu;
	private long item;
	private long itemCategory;

	public MenuItemBean() {
		super();
		isNewRecord = true;
	}

	public MenuItemBean(long menu, long item, long itemCategory) {
		super();
		this.menu = menu;
		this.item = item;
		this.itemCategory = itemCategory;
		isNewRecord = true;
	}

	public long getMenu() {
		return menu;
	}

	public void setMenu(long menu) {
		this.menu = menu;
	}

	public long getItem() {
		return item;
	}

	public void setItem(long item) {
		this.item = item;
	}

	public long getItemCategory() {
		return itemCategory;
	}

	public void setItemCategory(long itemCategory) {
		this.itemCategory = itemCategory;
	}

	protected String getDeleteQuery() {
		return "DELETE FROM " + table + " WHERE menu = " + menu + " AND item = " + item + " AND itemcategory = " + itemCategory;
	}

	protected String getUpdateQuery() {
		return null;
	}

	protected String getInsertQuery() {
		return "INSERT INTO " + table + " (menu, item, itemcategory) VALUES (" + menu + ", " + item + ", "
				+ itemCategory + ")";
	}

}
