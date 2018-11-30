package orders.dto;

import lombok.*;

/**
 * Created by szypows_local on 18.11.2018.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String companyName;
    private String address;
    private String nip;
    private String phoneNumber;
    private String email;
    private String customerType;
}
