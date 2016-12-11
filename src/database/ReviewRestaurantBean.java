package database;

import java.util.List;

public class ReviewRestaurantBean extends ReviewBean {

	private static final long serialVersionUID = 1L;
	private static final String parentType = "restaurant";

	public ReviewRestaurantBean() {
		super();
		super.setParentType(parentType);
	}

	public ReviewRestaurantBean(String review, long parentId, String account) {
		super(review, parentType, parentId, account);
	}

	public ReviewRestaurantBean(long id, String review, long parentId, String account) {
		super(id, review, parentType, parentId, account);
	}

	public static List<ReviewBean> getReviewsOfRestaurant(long restaurantId) {
		return getReviewsOfParent(parentType, restaurantId);
	}

}
