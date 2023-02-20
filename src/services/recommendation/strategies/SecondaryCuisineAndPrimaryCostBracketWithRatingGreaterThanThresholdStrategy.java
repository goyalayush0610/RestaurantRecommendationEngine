package services.recommendation.strategies;

import models.CostBracket;
import models.Cuisine;
import models.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class SecondaryCuisineAndPrimaryCostBracketWithRatingGreaterThanThresholdStrategy implements RestaurantRecommendationStrategy{
    private static final float rating = 4.5F;
    @Override
    public List<Restaurant> recommendRestaurants(List<Restaurant> restaurants, Cuisine primaryCuisine, CostBracket primaryCostBracket) {
        List<Restaurant> recommendations = new ArrayList<>();
        for (Restaurant restaurant : restaurants){
            if (!restaurant.getCuisine().equals(primaryCuisine) &&
                    restaurant.getCostBracket().equals(primaryCostBracket) &&
                    restaurant.getRating() >= rating){
                recommendations.add(restaurant);
            }
        }
        return recommendations;
    }
}
