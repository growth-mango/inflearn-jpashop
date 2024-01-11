package jpabook.jpashop.domain;


import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Adress {
    private String city;
    private String street;
    private String zipcode;
}
