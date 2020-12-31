package adrixus.com.services;

import adrixus.com.entities.*;
import adrixus.com.repository.CardApiRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nidavellir.book.response.json.handler.ResponseJsonHandler;
import com.nidavellir.book.response.json.handler.util.ResponseConstants;
import com.nidavellir.book.response.json.handler.util.ResponseJsonUtil;
import com.nidavellir.book.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CardApiService {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CardApiRepository cardApiRepository;

    public ResponseJsonHandler addCard(Long cardNo,String cardType,Short cvv,Short password , Long customerId){
        CardEntity cardEntity  = new CardEntity();

        cardEntity.setCardNo(cardNo);
        cardEntity.setCardType(cardType);
        cardEntity.setCvc(cvv);
        cardEntity.setPassword(password);

        cardEntity.setCustomers((CustomerEntity) cardApiRepository.getProxyEntity(CustomerEntity.class,customerId));
        cardEntity.setIsActive(true);
        cardEntity.setCreatedOn(Utility.getCustomTimestamp());
        cardEntity.setModifiedOn(Utility.getCustomTimestamp());

        cardEntity=cardApiRepository.addCardEntity(cardEntity);
        ObjectNode data  = objectMapper.createObjectNode();
        data.put("cardId",cardEntity.getId());
        return ResponseJsonUtil.getResponse(
                data,200, ResponseConstants.SUCCESS.getStatus(),false
        );
    }
}
