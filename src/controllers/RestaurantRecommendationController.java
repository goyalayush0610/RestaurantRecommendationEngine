package controllers;

import models.Restaurant;
import services.recommendation.RestaurantRecommendationService;
import services.recommendation.strategies.RestaurantRecommendationStrategy;

import java.util.List;

public class RestaurantRecommendationController {

    private RestaurantRecommendationService restaurantRecommendationService;

    public RestaurantRecommendationController(RestaurantRecommendationService restaurantRecommendationService) {
        this.restaurantRecommendationService = restaurantRecommendationService;
    }

    public List<Restaurant> getRecommendations(String userId){
        return this.restaurantRecommendationService.getRecommendations(userId);
    }

    public void setRecommendationSize(int size){
        this.restaurantRecommendationService.setRecommendationSize(size);
    }

    public void addRecommendationStrategies(List<RestaurantRecommendationStrategy> recommendationStrategies) {
        this.restaurantRecommendationService.addRecommendationStrategies(recommendationStrategies);
    }

    public void clearRecommendationStrategies() {
        this.restaurantRecommendationService.clearRecommendationStrategies();
    }
}
