package adrixus.com.entities;

import com.nidavellir.book.entities.hibernate.BaseEntity;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;


@Setter
@AllArgsConstructor
@Getter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "customers")
public class CustomerEntity implements BaseEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="customerId")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="addressLine1")
    private String addressLine1;

    @Column(name="addressLine2")
    private String addressLine2;

    @ManyToOne
    @JoinColumn(name = "countryId")
    private Countries countries;

    @ManyToOne
    @JoinColumn(name = "stateId")
    private States states;

    @ManyToOne
    @JoinColumn(name = "cityId")
    private Cities cities;

    @Column(name="createdOn")
    private Timestamp createdOn;

    @Column(name="modifiedOn")
    private Timestamp modifiedOn;

    @Column(name="isActive")
    @Type(type="org.hibernate.type.NumericBooleanType")
    private Boolean isActive;

    @OneToMany(mappedBy = "customers",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<CardEntity> cards;

}
