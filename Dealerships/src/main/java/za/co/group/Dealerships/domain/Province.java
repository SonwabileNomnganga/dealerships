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
@IdClass(ProvinceId.class)
public class Province implements Serializable {

    @Id
    @Column(name = "PROVINCECODE")
    private String provinceCode;

    @Id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "COUNTRYCODE", nullable = false)
    private Country countryCode;

    @Column(nullable = false)
    private String name;

}
