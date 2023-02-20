package models;

import java.util.Date;
import java.util.UUID;

public class Restaurant {
    private String id;
    private Cuisine cuisine;
    private CostBracket costBracket;
    private float rating;
    private boolean isRecommended;
    private Date onboardedTime;

    public Restaurant(Cuisine cuisine, CostBracket costBracket, float rating, boolean isRecommended) {
        this.id = UUID.randomUUID().toString();
        this.cuisine = cuisine;
        this.costBracket = costBracket;
        this.rating = rating;
        this.isRecommended = isRecommended;
        this.onboardedTime = new Date();
    }

    public String getId() {
        return id;
    }

    public Cuisine getCuisine() {
        return cuisine;
    }

    public CostBracket getCostBracket() {
        return costBracket;
    }

    public boolean isRecommended() {
        return isRecommended;
    }

    public float getRating() {
        return rating;
    }

    public Date getOnboardedTime() {
        return onboardedTime;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id='" + id + '\'' +
                ", cuisine=" + cuisine +
                ", costBracket=" + costBracket +
                ", rating=" + rating +
                ", isRecommended=" + isRecommended +
                ", onboardedTime=" + onboardedTime +
                '}';
    }
}
