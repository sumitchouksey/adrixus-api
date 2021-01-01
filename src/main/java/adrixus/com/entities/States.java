package adrixus.com.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.nidavellir.book.entities.hibernate.BaseEntity;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "states")
public class States implements BaseEntity {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="stateId")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="fipsCode")
    private String fipsCode;

    @Column(name="iso2")
    private String iso2;

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
    @JoinColumn(name = "countryId")
    @JsonBackReference
    private Countries countries;

    @OneToMany(mappedBy = "states",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<Cities> cities;
}
