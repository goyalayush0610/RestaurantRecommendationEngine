package services;

import models.CostBracket;
import models.Cuisine;
import models.Restaurant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestaurantService {
    private Map<String, Restaurant> restaurants;

    public RestaurantService() {
        this.restaurants = new HashMap<>();
    }

    public void onboardRestaurant(Cuisine cuisine, CostBracket costBracket, float rating, boolean isRecommended){
        Restaurant restaurant = new Restaurant(cuisine, costBracket, rating, isRecommended);
        restaurants.put(restaurant.getId(), restaurant);
    }

    public Restaurant getRestaurant(String id){
        return this.restaurants.get(id);
    }

    public List<Restaurant> getAvailableRestaurants() {
        return restaurants.values().stream().toList();
    }
}
