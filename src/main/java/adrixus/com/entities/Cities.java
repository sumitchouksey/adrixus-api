package adrixus.com.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.nidavellir.book.entities.hibernate.BaseEntity;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Timestamp;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "cities")
public class Cities implements BaseEntity {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="cityId")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name = "stateCode")
    private String stateCode;

    @Column(name = "countryId")
    private Long countryId;

    @Column(name = "countryCode")
    private String countryCode;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;


    @Column(name="createdOn")
    private Timestamp createdOn;

    @Column(name="modifiedOn")
    private Timestamp modifiedOn;

    @Column(name = "wikiDataId")
    private String wikiDataId;

    @Column(name="isActive")
    @Type(type="org.hibernate.type.NumericBooleanType")
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "stateId")
    @JsonBackReference
    private States states;

}
