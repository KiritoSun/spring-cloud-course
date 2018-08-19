package com.zt.pojo;

public class tcourse {
    private String name;
    private String time;

    public tcourse(){}

    public tcourse(String name, String time) {
        this.name = name;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "tcourse{" +
                "name='" + name + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
