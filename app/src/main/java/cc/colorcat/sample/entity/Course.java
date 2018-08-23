package cc.colorcat.sample.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by cxx on 18-8-23.
 * xx.ch@outlook.com
 */
public class Course {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("picSmall")
    private String picSmall;
    @SerializedName("picBig")
    private String picBig;
    @SerializedName("description")
    private String description;
    @SerializedName("learner")
    private int learner;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicSmall() {
        return picSmall;
    }

    public void setPicSmall(String picSmall) {
        this.picSmall = picSmall;
    }

    public String getPicBig() {
        return picBig;
    }

    public void setPicBig(String picBig) {
        this.picBig = picBig;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLearner() {
        return learner;
    }

    public void setLearner(int learner) {
        this.learner = learner;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", picSmall='" + picSmall + '\'' +
                ", picBig='" + picBig + '\'' +
                ", description='" + description + '\'' +
                ", learner=" + learner +
                '}';
    }
}
