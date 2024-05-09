package com;
import java.util.ArrayList;

public class User {
    private String email;
    private String password;
    private ArrayList<Course> courses;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.courses = new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void addCourse(String name, int creditHours, String grade) {
        courses.add(new Course(name, creditHours, grade));
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }
}
