package services.recommendation.strategies;

import models.CostBracket;
import models.Cuisine;
import models.Restaurant;

import java.util.List;

public interface RestaurantRecommendationStrategy {
    List<Restaurant> recommendRestaurants(List<Restaurant> restaurants, Cuisine primaryCuisine, CostBracket primaryCostBracket);
}
