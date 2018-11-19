package entities;

import javax.persistence.*;

/**
 * Created by szypows_local on 17.11.2018.
 */
@Entity
public class Customer {

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;
    private String lastName;
    private String companyName;
    private String address;
    private String nip;
    private String phoneNumber;
    private String email;

    @Enumerated(EnumType.STRING)
    private CustomerType customerType;

}
