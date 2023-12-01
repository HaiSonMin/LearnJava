package com.haison.Spring.test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PeopleImpl {
    private IPeople iPeople;

    public PeopleImpl(IPeople iPeople) {
        this.iPeople = iPeople;
    }

    public void getInfoPeople() {
        System.out.println(this.iPeople.talk());
    }

}
