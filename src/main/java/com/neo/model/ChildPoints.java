package com.neo.model;

public class ChildPoints {
    private String name;  // 儿童名字
    private int points;   // 积分
    private int experience;  // 经验值

    public ChildPoints(String name, int points, int experience) {
        this.name = name;
        this.points = points;
        this.experience = experience;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }
}

