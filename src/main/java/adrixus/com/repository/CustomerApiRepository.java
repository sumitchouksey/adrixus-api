package adrixus.com.repository;


import adrixus.com.entities.CustomerEntity;
import com.nidavellir.book.repository.HibernateRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class CustomerApiRepository extends HibernateRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public CustomerEntity addCustomerEntity(CustomerEntity customerEntity){
        saveOrUpdateEntity(customerEntity);
        return customerEntity;
    }

}
