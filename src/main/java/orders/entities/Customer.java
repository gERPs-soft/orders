package orders.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by szypows_local on 17.11.2018.
 */
@Getter
@Setter
@Entity
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;
    private String companyName;
    private String address;
    private String nip;
    private String phoneNumber;
    private String email;
    private boolean active = true;

    @Enumerated(EnumType.STRING)
    private CustomerType customerType;

    public Customer() {
    }
}
