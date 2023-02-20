package services.recommendation.strategies;

import models.CostBracket;
import models.Cuisine;
import models.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class FeaturedRestaurantStrategy implements RestaurantRecommendationStrategy {
    @Override
    public List<Restaurant> recommendRestaurants(List<Restaurant> restaurants, Cuisine primaryCuisine, CostBracket primaryCostBracket) {
        List<Restaurant> recommendations = new ArrayList<>();
        for (Restaurant restaurant : restaurants){
            if (restaurant.isRecommended() &&
                    restaurant.getCuisine().equals(primaryCuisine) &&
                    restaurant.getCostBracket().equals(primaryCostBracket) ){
                recommendations.add(restaurant);
            }
        }

        if (recommendations.isEmpty()){
            for (Restaurant restaurant : restaurants){
                if (restaurant.isRecommended() &&
                        (restaurant.getCuisine().equals(primaryCuisine) ||
                        restaurant.getCostBracket().equals(primaryCostBracket)) ){
                    recommendations.add(restaurant);
                }
            }
        }
        return recommendations;
    }
}
