package com.menufinder.soap;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import database.RestaurantBean;

@WebService()
public class MenuFinderSOAP {

	@WebMethod()
	public List<RestaurantBean> getRestaurants() {
		return RestaurantBean.getAllBeans();
	}
}
