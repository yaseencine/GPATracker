package com;

public class Course {
    private String name;
    private int creditHours;
    private String grade;

    public Course(String name, int creditHours, String grade) {
        this.name = name;
        this.creditHours = creditHours;
        this.grade = grade;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public String getGrade() {
        return grade;
    }

    public String getName() {
        return name;
    }
}
