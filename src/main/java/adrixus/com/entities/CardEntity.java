package adrixus.com.entities;

import com.nidavellir.book.entities.hibernate.BaseEntity;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Timestamp;


@Setter
@AllArgsConstructor
@Getter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "cards")
public class CardEntity implements BaseEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="cardId")
    private Long id;

    @Column(name="cardNo")
    private Integer cardNo;

    @Column(name="cardType")
    private String cardType;

    @Column(name="cvc")
    private Short cvc;

    @Column(name="password")
    private Short password;


    @Column(name="createdOn")
    private Timestamp createdOn;

    @Column(name="modifiedOn")
    private Timestamp modifiedOn;


    @Column(name="active")
    @Type(type="org.hibernate.type.NumericBooleanType")
    private Boolean isActive;


    @ManyToOne
    @JoinColumn(name = "customerId")
    private CustomerEntity customers;
}
