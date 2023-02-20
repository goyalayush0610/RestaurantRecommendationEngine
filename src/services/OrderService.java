package services;

import models.Restaurant;

public class OrderService {
    private UserService userService;
    private RestaurantService restaurantService;

    public OrderService(UserService userService, RestaurantService restaurantService) {
        this.userService = userService;
        this.restaurantService = restaurantService;
    }

    public void order(String userId, String restaurantId){
        // Add order: Skipping

        // Add tracking data for recommendations
        Restaurant restaurant = restaurantService.getRestaurant(restaurantId);
        userService.trackUserPreferences(userId, restaurant.getCuisine(), restaurant.getCostBracket());
    }
}
