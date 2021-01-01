package adrixus.com.repository;


import adrixus.com.entities.*;
import com.nidavellir.book.repository.HibernateRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@Transactional
public class CustomerApiRepository extends HibernateRepository {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Save customerEntity to database
     * @param customerEntity - customerEntity instance passed from service layer
     * @return CustomerEntity with primary key on saved.
     */
    public CustomerEntity addCustomerEntity(CustomerEntity customerEntity){
        saveOrUpdateEntity(customerEntity);
        return customerEntity;
    }

    /**
     *
     * @param index
     * @param itemsPerIndex
     * @return
     */
    /**
     * Return all customer and their card details
     * @param index - page index must start from 0
     * @param itemsPerIndex number of items to be fetched per page
     * @return Return list of CustomerEntity
     */
    public List<CustomerEntity> getCustomers(int index,int itemsPerIndex){
        CriteriaBuilder criteriaBuilder  = entityManager.getCriteriaBuilder();
        CriteriaQuery<CustomerEntity> customerEntityCriteriaQuery= criteriaBuilder.createQuery(CustomerEntity.class);
        Root<CustomerEntity> customerEntityRoot= customerEntityCriteriaQuery.from(CustomerEntity.class);
        /*Join<CustomerEntity, CardEntity> cardEntityJoin =(Join<CustomerEntity, CardEntity>)customerEntityRoot.fetch(CustomerEntity_.cards);
        Join<CustomerEntity, Countries> countriesJoin =(Join<CustomerEntity, Countries>)customerEntityRoot.fetch(CustomerEntity_.countries);
        Join<CustomerEntity, States> statesJoin =(Join<CustomerEntity, States>)customerEntityRoot.fetch(CustomerEntity_.states);
        Join<CustomerEntity, Cities> citiesJoin =(Join<CustomerEntity, Cities>)customerEntityRoot.fetch(CustomerEntity_.cities);
*/
        customerEntityCriteriaQuery.select(customerEntityRoot).distinct(true);
        customerEntityCriteriaQuery
                .where(
                        criteriaBuilder.equal(customerEntityRoot.get(CustomerEntity_.isActive),true)
                );
        TypedQuery<CustomerEntity> typedQuery=entityManager.createQuery( customerEntityCriteriaQuery );
        typedQuery.setFirstResult(index);
        typedQuery.setMaxResults(itemsPerIndex);
        return
                typedQuery.getResultList();
    }

}
