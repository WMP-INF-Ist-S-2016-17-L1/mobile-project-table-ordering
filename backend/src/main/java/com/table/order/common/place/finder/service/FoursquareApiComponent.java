package com.table.order.common.place.finder.service;

import com.table.order.common.place.finder.model.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class FoursquareApiComponent {

	public static final String FOURSQUARE_API_URL = "https://api.foursquare.com/v2/venues";

	@Value("${foursquare.clientId}")
	private String clientId;

	@Value("${foursquare.clientSecret}")
	private String clientSecret;

	@Value("${foursquare.result.limit}")
	private int limit;

	@Value("${foursquare.version}")
	private String version;

	@Value("${foursquare.category.food.id}")
	private String foodCategoryId;

	public String getExploreUrlFormat() {
		return FOURSQUARE_API_URL + "/explore"
				+ "?client_id={clientId}"
				+ "&client_secret={clientSecret}&"
				+ "v={version}"
				+ "&limit={limit}"
				+ "&ll={lat},{lng}"
				+ "&query={query}&"
				+ "fbclid=IwAR3NFUJgyrfv2D4469fepWHwpkAq8ntBpFakzYjciZSXoGkj3gmJuKu5tSM";
	}

	public String getExploreUrlByCityFormat() {
		return FOURSQUARE_API_URL + "/explore"
				+ "?client_id={clientId}"
				+ "&client_secret={clientSecret}&"
				+ "v={version}"
				+ "&limit={limit}"
				+ "&near={city}"
				+ "&query={query}&"
				+ "fbclid=IwAR3NFUJgyrfv2D4469fepWHwpkAq8ntBpFakzYjciZSXoGkj3gmJuKu5tSM";
	}
	
	public String getSearchUrlByCategoryFoodFormat() {
		return FOURSQUARE_API_URL + "/search"
                + "?client_id={clientId}"
                + "&client_secret={clientSecret}"
                + "&near={near}"
                + "&categoryId={category_id}"
                + "&query={query}"
                + "&limit={limit}"
                + "&v={version}";
	}
	
	public String getDetailsUrlFormat() {
		return FOURSQUARE_API_URL + "/{venueId}"
                + "?client_id={clientId}"
                + "&client_secret={clientSecret}"
                + "&v={version}";
	}
	
	public Map<String, String> getDetailsUrlData(String venueId) {
        Map<String, String> urlData = new HashMap<>();
        urlData.put("clientId", clientId);
        urlData.put("clientSecret", clientSecret);
        urlData.put("venueId", venueId);
        urlData.put("version", version);

        return urlData;
    }

	public Map<String, Object> getSearchUrlDataWithFoodCategory(String query, String near) {
		Map<String, Object> uriVariables = getBaseUriVariables(query);
		uriVariables.put("near", near);
		uriVariables.put("category_id", foodCategoryId);

		return uriVariables;
	}

	public Map<String, Object> getFoursquareUriVariables(Request request) {
		Map<String, Object> uriVariables = getBaseUriVariables(request.getQuery());
		uriVariables.put("lat", request.getLat());
		uriVariables.put("lng", request.getLng());

		return uriVariables;
	}

	public Map<String, Object> getFoursquareUriVariablesByCity(String query, String city) {
		Map<String, Object> uriVariables = getBaseUriVariables(query);
		uriVariables.put("city", city);

		return uriVariables;
	}

	private Map<String, Object> getBaseUriVariables(String query) {
		Map<String, Object> uriVariables = new HashMap<>();
		uriVariables.put("clientId", clientId);
		uriVariables.put("clientSecret", clientSecret);
		uriVariables.put("version", version);
		uriVariables.put("limit", limit);
		uriVariables.put("query", query);

		return uriVariables;
	}
}
