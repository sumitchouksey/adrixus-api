package adrixus.com.repository;

import adrixus.com.entities.*;
import com.nidavellir.book.repository.HibernateRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@Transactional
public class CoreApiRepository extends HibernateRepository {

    @PersistenceContext
    private EntityManager entityManager;


    /**
     * Get Country Tables Data
     * @return
     */
    public List<Countries> getCountries(){
        CriteriaBuilder criteriaBuilder  = entityManager.getCriteriaBuilder();
        CriteriaQuery<Countries> countriesCriteriaQuery= criteriaBuilder.createQuery(Countries.class);
        Root<Countries> serviceEntityRoot= countriesCriteriaQuery.from(Countries.class);
        countriesCriteriaQuery.select(serviceEntityRoot).distinct(true);
        return entityManager.createQuery( countriesCriteriaQuery )
                .getResultList();
    }

    /**
     * Get State Table Data
     * @return
     */
    public List<States> getStates(Long countryId){
        CriteriaBuilder criteriaBuilder  = entityManager.getCriteriaBuilder();
        CriteriaQuery<States> statesCriteriaQuery= criteriaBuilder.createQuery(States.class);
        Root<States> statesRoot= statesCriteriaQuery.from(States.class);
        statesCriteriaQuery.select(statesRoot).distinct(true);
        statesCriteriaQuery
                .where(
                        criteriaBuilder.equal(statesRoot.get(States_.countries),countryId),
                        criteriaBuilder.equal(statesRoot.get(States_.isActive),true)
                );
        return  entityManager.createQuery( statesCriteriaQuery )
                .getResultList();

    }

    /**
     * Get City Table Data
     * @return
     */
    public List<Cities> getCities(Long stateId){
        CriteriaBuilder criteriaBuilder  = entityManager.getCriteriaBuilder();
        CriteriaQuery<Cities> statesCriteriaQuery= criteriaBuilder.createQuery(Cities.class);
        Root<Cities> statesRoot= statesCriteriaQuery.from(Cities.class);
        statesCriteriaQuery.select(statesRoot).distinct(true);
        statesCriteriaQuery
                .where(
                        criteriaBuilder.equal(statesRoot.get(Cities_.states),stateId),
                        criteriaBuilder.equal(statesRoot.get(Cities_.isActive),true)
                );
        return  entityManager.createQuery( statesCriteriaQuery )
                .getResultList();

    }
}
