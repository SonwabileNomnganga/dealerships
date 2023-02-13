package za.co.group.Dealerships.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Country implements Serializable {

    @Id
    @Column(name = "COUNTRYCODE", nullable = false)
    private String countryCode;

    @Column(name = "name", nullable = false)
    private String name;
}
