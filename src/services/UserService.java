package services;

import models.CostBracket;
import models.Cuisine;
import models.User;

import java.util.HashMap;
import java.util.Map;

public class UserService {
    Map<String, User> users;

    public UserService() {
        this.users = new HashMap<>();
    }

    public String addUser(){
        User user = new User();
        users.put(user.getId(), user);
        return user.getId();
    }

    public User getUser(String userId){
        return users.get(userId);
    }

    public void trackUserPreferences(String userId, Cuisine cuisine, CostBracket costBracket){
        User user = users.get(userId);
        user.addCostPreference(costBracket);
        user.addCuisinePreference(cuisine);
    }
}
