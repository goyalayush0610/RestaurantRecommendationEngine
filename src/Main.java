import controllers.OrderController;
import controllers.RestaurantController;
import controllers.RestaurantRecommendationController;
import controllers.UserController;
import models.CostBracket;
import models.Cuisine;
import models.Restaurant;
import models.User;
import services.OrderService;
import services.RestaurantService;
import services.UserService;
import services.recommendation.RestaurantRecommendationService;
import services.recommendation.strategies.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        //Onboard some restaurants
        RestaurantService restaurantService = new RestaurantService();
        RestaurantController restaurantController = new RestaurantController(restaurantService);
        onboardRestaurants(restaurantController);
        List<Restaurant> availableRestaurants = restaurantController.getAvailableRestaurants();

        //Add a user
        UserService userService = new UserService();
        UserController userController = new UserController(userService);
        String userId = userController.addUser();

        //User orders on each restaurant
        OrderService orderService = new OrderService(userService, restaurantService);
        OrderController orderController = new OrderController(orderService);
        for (Restaurant restaurant : availableRestaurants){
            orderController.order(userId, restaurant.getId());
        }

        //Get User Preferences
        User user = userService.getUser(userId);
        System.out.println("User Preferences:");
        System.out.println("Primary Cuisine: "+ user.getPrimaryCuisine());
        System.out.println("Primary Cost Bracket: "+ user.getPrimaryCostBracket());

        //recommend restaurants to the user
        RestaurantRecommendationService restaurantRecommendationService = new RestaurantRecommendationService(restaurantService, userService);
        RestaurantRecommendationController restaurantRecommendationController = new RestaurantRecommendationController(restaurantRecommendationService);
        restaurantRecommendationController.setRecommendationSize(10);

        List<RestaurantRecommendationStrategy> restaurantRecommendationStrategyList = formulateRecommendationStrategy();

        restaurantRecommendationController.clearRecommendationStrategies();
        restaurantRecommendationController.addRecommendationStrategies(restaurantRecommendationStrategyList);

        List<Restaurant> recommendations = restaurantRecommendationController.getRecommendations(userId);
        for (Restaurant recommendation : recommendations){
            System.out.println("Recommendations: ");
            System.out.println(recommendation.toString());
        }
    }

    private static void onboardRestaurants(RestaurantController restaurantController) throws InterruptedException {
        restaurantController.onboardRestaurant(Cuisine.NorthIndian, CostBracket.High, 5, true);
        restaurantController.onboardRestaurant(Cuisine.SouthIndian, CostBracket.Low, 5, false);
        restaurantController.onboardRestaurant(Cuisine.NorthIndian, CostBracket.Medium, 3, false);
        restaurantController.onboardRestaurant(Cuisine.Chinese, CostBracket.High, 5, true);
        restaurantController.onboardRestaurant(Cuisine.NorthIndian, CostBracket.Low, 5, true);
        restaurantController.onboardRestaurant(Cuisine.NorthIndian, CostBracket.High, 3, false);
        restaurantController.onboardRestaurant(Cuisine.Chinese, CostBracket.Medium, 5, true);
        restaurantController.onboardRestaurant(Cuisine.SouthIndian, CostBracket.Low, 5, true);
        restaurantController.onboardRestaurant(Cuisine.NorthIndian, CostBracket.High, 3, false);
        Thread.sleep(1000);
        restaurantController.onboardRestaurant(Cuisine.Chinese, CostBracket.Medium, 5, true);
        restaurantController.onboardRestaurant(Cuisine.SouthIndian, CostBracket.Low, 5, true);
        restaurantController.onboardRestaurant(Cuisine.Chinese, CostBracket.High, 3, false);
    }

    private static List<RestaurantRecommendationStrategy> formulateRecommendationStrategy() {
        List<RestaurantRecommendationStrategy> restaurantRecommendationStrategyList = new ArrayList<>();
        restaurantRecommendationStrategyList.add(new FeaturedRestaurantStrategy());
        restaurantRecommendationStrategyList.add(new PrimaryCuisineAndCostBracketWithRatingGreaterThanThresholdStrategy());
        restaurantRecommendationStrategyList.add(new PrimaryCuisineAndSecondaryCostBracketWithRatingGreaterThanThresholdStrategy());
        restaurantRecommendationStrategyList.add(new SecondaryCuisineAndPrimaryCostBracketWithRatingGreaterThanThresholdStrategy());
        restaurantRecommendationStrategyList.add(new NewRestaurantStrategy());
        restaurantRecommendationStrategyList.add(new PrimaryCuisineAndCostBracketWithRatingLessThanThresholdStrategy());
        restaurantRecommendationStrategyList.add(new PrimaryCuisineAndSecondaryCostBracketWithRatingLessThanThresholdStrategy());
        restaurantRecommendationStrategyList.add(new SecondaryCuisineAndPrimaryCostBracketWithRatingLessThanThresholdStrategy());
        restaurantRecommendationStrategyList.add(new AllRestaurantStrategy());
        return  restaurantRecommendationStrategyList;
    }
}