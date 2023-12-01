package com.haison.Spring.test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Customer extends People implements IPeople {
    protected String id;
    protected String company;
    protected String position;



    public Customer(String name, int age, String id, String company, String position) {
        super(name,age);
        this.id = id;
        this.company = company;
        this.position = position;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + this.name + '\'' +
                "age='" + this.age + '\'' +
                "id='" + this.id + '\'' +
                ", company='" + this.company + '\'' +
                ", position='" + this.position + '\'' +
                '}';
    }

    @Override
    public String talk() {
        return this.toString();
    }
}
