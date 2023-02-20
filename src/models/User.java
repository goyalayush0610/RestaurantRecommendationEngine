package models;

import java.util.*;

public class User {
    private String id;
    private Map<Cuisine, Integer> cuisineTracking;
    private Map<CostBracket, Integer> costTracking;

    private Cuisine primaryCuisine;
    private CostBracket primaryCostBracket;


    public User() {
        this.id = UUID.randomUUID().toString();
        cuisineTracking = new HashMap<>();
        costTracking = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public void addCostPreference(CostBracket costBracket){
        this.costTracking.putIfAbsent(costBracket, 0);
        this.costTracking.replace(costBracket, costTracking.get(costBracket)+1);

        if (primaryCostBracket == null || costTracking.get(costBracket) > costTracking.get(primaryCostBracket)){
            primaryCostBracket = costBracket;
        }
    }

    public void addCuisinePreference(Cuisine cuisine){
        this.cuisineTracking.putIfAbsent(cuisine, 0);
        this.cuisineTracking.replace(cuisine, cuisineTracking.get(cuisine)+1);

        if (primaryCuisine == null || cuisineTracking.get(cuisine) > cuisineTracking.get(primaryCuisine)){
            primaryCuisine = cuisine;
        }
    }

    public Cuisine getPrimaryCuisine(){
        return primaryCuisine;
    }

    public CostBracket getPrimaryCostBracket(){
        return primaryCostBracket;
    }
}
