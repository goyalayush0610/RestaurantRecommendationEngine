package services.recommendation.strategies;

import models.CostBracket;
import models.Cuisine;
import models.Restaurant;

import java.util.List;

public class AllRestaurantStrategy implements RestaurantRecommendationStrategy{
    @Override
    public List<Restaurant> recommendRestaurants(List<Restaurant> restaurants, Cuisine primaryCuisine, CostBracket primaryCostBracket) {
        return restaurants;
    }
}
