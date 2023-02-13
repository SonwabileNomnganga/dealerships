package za.co.group.Dealerships.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class Dealership implements Serializable {

    @Id
    @Column(name = "DEALERSHIPID", nullable = false )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dealershipId;

    @Column(name = "DEALERSHIPNAME", nullable = false )
    private String dealershipName;

    @Column(name = "LINE1", nullable = false)
    private String line1;

    @Column(name = "LINE2", nullable = false)
    private String line2;

    @Column(name = "SUBURB", nullable = false)
    private String suburb;

    @Column(name = "CITY", nullable = false)
    private String city;

    @Column(name = "PROVINCE", nullable = false)
    private String province;

    @Column(name = "POSTALCODE", nullable = false)
    private String postalCode;

    @Column(name = "COUNTRY", nullable = false)
    private String country;

    public Dealership(String dealershipName, String line1, String line2, String suburb, String city, String postalCode, String province, String country) {
        this.dealershipName = dealershipName;
        this.line1 = line1;
        this.line2 = line2;
        this.suburb = suburb;
        this.city = city;
        this.postalCode = postalCode;
        this.province = province;
        this.country = country;
    }
}
