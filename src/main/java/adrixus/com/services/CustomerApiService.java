package adrixus.com.services;


import adrixus.com.entities.Cities;
import adrixus.com.entities.Countries;
import adrixus.com.entities.CustomerEntity;
import adrixus.com.entities.States;
import adrixus.com.repository.CustomerApiRepository;
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
public class CustomerApiService {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CustomerApiRepository customerApiRepository;


    public ResponseJsonHandler addCustomer(String name,String addressLine1,String addressLine2 , Long countryId,Long stateId,Long cityId){
        CustomerEntity customerEntity  = new CustomerEntity();

        customerEntity.setName(name);
        customerEntity.setAddressLine1(addressLine1);
        customerEntity.setAddressLine2(addressLine2);

        customerEntity.setCountries((Countries) customerApiRepository.getProxyEntity(Countries.class,countryId));
        customerEntity.setStates((States) customerApiRepository.getProxyEntity(States.class,stateId));
        customerEntity.setCities((Cities) customerApiRepository.getProxyEntity(Cities.class,countryId));
        customerEntity.setIsActive(true);
        customerEntity.setCreatedOn(Utility.getCustomTimestamp());
        customerEntity.setModifiedOn(Utility.getCustomTimestamp());

        customerEntity=customerApiRepository.addCustomerEntity(customerEntity);
        ObjectNode data  = objectMapper.createObjectNode();
        data.put("customerId",customerEntity.getId());
        return ResponseJsonUtil.getResponse(
                data,200, ResponseConstants.SUCCESS.getStatus(),false
        );
    }

}
