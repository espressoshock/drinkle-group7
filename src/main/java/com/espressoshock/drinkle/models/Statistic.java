package com.espressoshock.drinkle.models;

import java.util.ArrayList;
import java.util.List;

public class Statistic {
    private long views;
    private List<Like>likes;

    public Statistic(long views, List<Like> likes) {
        this.views = views;
        this.likes = likes;
    }

    public Statistic(long views) {
        this.views = views;
        this.likes = new ArrayList<>();
    }

    public long getViews() {
        return views;
    }

    public List<Like> getLikes() {
        return likes;
    }
}
