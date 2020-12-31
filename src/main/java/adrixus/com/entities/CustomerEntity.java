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
@Table(name = "users")
public class CustomerEntity implements BaseEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="userId")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="email")
    private String email;

    @Column(name="contactNo")
    private String contactNo;

    @Column(name="password")
    private String password;

    @Column(name="gender")
    private String gender;

    @Column(name="dateOfBirth")
    private String dateOfBirth;

    @Column(name="address")
    private String address;

    @Column(name="profilePic")
    private String profilePic;

    @Column(name="countryCode")
    private String countryCode;

    @Column(name="country")
    private String country;

    @Column(name="state")
    private String state;

    @Column(name="city")
    private String city;

    @Column(name="latitude")
    private double latitude;

    @Column(name="longitude")
    private double longitude;

    @Column(name="createdOn")
    private Timestamp createdOn;

    @Column(name="modifiedOn")
    private Timestamp modifiedOn;

    @Column(name= "passwordCreatedOn")
    private Timestamp passwordCreatedOn;

    @Column(name="isActive")
    @Type(type="org.hibernate.type.NumericBooleanType")
    private Boolean isActive;

    @Column(name = "isAccountVerified")
    @Type(type="org.hibernate.type.NumericBooleanType")
    private Boolean  isAccountVerified;

    @Column(name = "attempts")
    private int attempts;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(name="users_roles",
            joinColumns={@JoinColumn(name="userId", referencedColumnName="userId")},
            inverseJoinColumns={@JoinColumn(name="roleId", referencedColumnName="roleId")})
    private Set<RolesEntity> rolesEntity;

    @OneToMany(mappedBy = "userEntity",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<UserServicesEntity> userServicesEntities;
}
