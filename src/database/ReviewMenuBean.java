package database;

import java.util.List;

public class ReviewMenuBean extends ReviewBean {

	private static final long serialVersionUID = 1L;
	private static final String parentType = "menu";

	public ReviewMenuBean() {
		super();
		super.setParentType(parentType);
	}

	public ReviewMenuBean(String review, long parentId, String account) {
		super(review, parentType, parentId, account);
	}

	public ReviewMenuBean(long id, String review, long parentId, String account) {
		super(id, review, parentType, parentId, account);
	}

	public static List<ReviewBean> getReviewsOfMenu(long menuId) {
		return getReviewsOfParent(parentType, menuId);
	}

}
