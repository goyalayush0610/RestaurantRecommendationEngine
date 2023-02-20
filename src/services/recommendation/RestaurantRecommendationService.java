package services.recommendation;

import models.CostBracket;
import models.Cuisine;
import models.Restaurant;
import models.User;
import services.RestaurantService;
import services.UserService;
import services.recommendation.strategies.RestaurantRecommendationStrategy;

import java.util.*;

public class RestaurantRecommendationService {
    private List<RestaurantRecommendationStrategy> recommendationStrategies;
    private RestaurantService restaurantService;
    private UserService userService;
    private int recommendationSize;

    public RestaurantRecommendationService(RestaurantService restaurantService, UserService userService) {
        recommendationStrategies = new ArrayList<>();
        recommendationSize = 0;
        this.restaurantService = restaurantService;
        this.userService = userService;
    }

    public void addRecommendationStrategies(List<RestaurantRecommendationStrategy> recommendationStrategies) {
        this.recommendationStrategies.addAll(recommendationStrategies);
    }

    public void setRecommendationSize(int recommendationSize) {
        this.recommendationSize = recommendationSize;
    }

    public List<Restaurant> getRecommendations(String userId){
        User user = userService.getUser(userId);
        List<Restaurant> restaurants = restaurantService.getAvailableRestaurants();
        Cuisine primaryCuisine = user.getPrimaryCuisine();
        CostBracket primaryCostBracket = user.getPrimaryCostBracket();

        Set<Restaurant> recommendations = new LinkedHashSet<>();

        for (RestaurantRecommendationStrategy recommendationStrategy : recommendationStrategies){
            if (recommendations.size() > recommendationSize){
                break;
            }
            List<Restaurant> recommendedRestaurants = recommendationStrategy.recommendRestaurants(restaurants, primaryCuisine, primaryCostBracket);
            recommendations.addAll(recommendedRestaurants);
        }
        return recommendations.stream().toList().subList(0, recommendationSize-1);
    }

    public void clearRecommendationStrategies() {
        this.recommendationStrategies = new ArrayList<>();
    }
}
