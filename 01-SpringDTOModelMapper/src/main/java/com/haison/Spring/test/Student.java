package com.haison.Spring.test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Student extends People implements IPeople {
    private String id;
    private String nameSchool;

    public Student(String name, int age, String id, String nameSchool) {
        super(name,age);
        this.id = id;
        this.nameSchool = nameSchool;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id='" + id + '\'' +
                ", nameSchool='" + nameSchool + '\'' +
                '}';
    }

    @Override
    public String talk() {
        return this.toString();
    }

}
