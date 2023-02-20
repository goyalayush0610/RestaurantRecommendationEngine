package controllers;

import models.CostBracket;
import models.Cuisine;
import models.Restaurant;
import services.RestaurantService;

import java.util.List;

public class RestaurantController {
    private RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    public void onboardRestaurant(Cuisine cuisine, CostBracket costBracket, float rating, boolean isRecommended){
        this.restaurantService.onboardRestaurant(cuisine, costBracket, rating, isRecommended);
    }

    public List<Restaurant> getAvailableRestaurants(){
        return this.restaurantService.getAvailableRestaurants();
    }
}
