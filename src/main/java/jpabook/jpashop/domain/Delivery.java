package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter@Setter
public class Delivery {

    @Id
    @GeneratedValue
    @Column(name="delivery_id")
    private Long id ;

    @OneToOne(mappedBy = "delivery")
    private Order oder ;

    @Embedded
    private Adress adress;

      // ordinal 가 default 하지만 쓰면 망함 .. 숫자로 들어감 // 반드시 string 으로
    private DeliveryStatus status ; // READY, COMP
}
