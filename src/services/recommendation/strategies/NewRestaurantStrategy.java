package services.recommendation.strategies;

import models.CostBracket;
import models.Cuisine;
import models.Restaurant;

import java.util.List;

public class NewRestaurantStrategy implements RestaurantRecommendationStrategy{
    private static final int count = 4;
    @Override
    public List<Restaurant> recommendRestaurants(List<Restaurant> restaurants, Cuisine primaryCuisine, CostBracket primaryCostBracket) {
        List<Restaurant> recommendations = restaurants.stream().sorted((o1, o2) -> o2.getOnboardedTime().compareTo(o1.getOnboardedTime())).toList();
        return recommendations.subList(0, count);
    }
}
