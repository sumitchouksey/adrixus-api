package adrixus.com.repository;

import adrixus.com.entities.CardEntity;
import adrixus.com.entities.CustomerEntity;
import com.nidavellir.book.repository.HibernateRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class CardApiRepository extends HibernateRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public CardEntity addCardEntity(CardEntity cardEntity){
        saveOrUpdateEntity(cardEntity);
        return cardEntity;
    }
}
