package database;

import java.util.List;

public class ReviewItemBean extends ReviewBean {

	private static final long serialVersionUID = 1L;
	private static final String parentType = "item";

	public ReviewItemBean() {
		super();
		super.setParentType(parentType);
	}

	public ReviewItemBean(String review, long parentId, String account) {
		super(review, parentType, parentId, account);
	}

	public ReviewItemBean(long id, String review, long parentId, String account) {
		super(id, review, parentType, parentId, account);
	}

	public static List<ReviewBean> getReviewsOfItem(long itemId) {
		return getReviewsOfParent(parentType, itemId);
	}

}
