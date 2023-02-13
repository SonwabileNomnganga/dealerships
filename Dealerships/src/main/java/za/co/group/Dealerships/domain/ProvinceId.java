package za.co.group.Dealerships.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ProvinceId implements Serializable {

    @Column(name = "PROVINCECODE")
    private String provinceCode;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "COUNTRYCODE", nullable = false)
    private Country countryCode;
}
