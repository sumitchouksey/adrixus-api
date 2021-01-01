package adrixus.com.services;


import adrixus.com.entities.Cities;
import adrixus.com.entities.Countries;
import adrixus.com.entities.CustomerEntity;
import adrixus.com.entities.States;
import adrixus.com.repository.CustomerApiRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nidavellir.book.response.json.handler.ResponseJsonHandler;
import com.nidavellir.book.response.json.handler.util.ResponseConstants;
import com.nidavellir.book.response.json.handler.util.ResponseJsonUtil;
import com.nidavellir.book.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public ResponseJsonHandler getCustomers(int index,int itemsPerIndex){
        ArrayNode data  = objectMapper.createArrayNode();
        List<CustomerEntity> customerEntities  = customerApiRepository.getCustomers(index,itemsPerIndex);
        customerEntities.forEach(e->{
            ObjectNode node  = objectMapper.createObjectNode();
            ObjectNode customer  = objectMapper.createObjectNode();

            customer.put("customerId",e.getId());
            customer.put("name",e.getName());
            customer.put("addressLine1",e.getAddressLine1());
            customer.put("addressLine2",e.getAddressLine2());
            customer.put("city",e.getCities().getName());
            customer.put("state",e.getStates().getName());
            customer.put("country",e.getCountries().getName());

            node.set("customer",customer);
            customer.set("cards",objectMapper.valueToTree(e.getCards()));

            data.add(node);
        });


        return ResponseJsonUtil.getResponse(
                data,200, ResponseConstants.SUCCESS.getStatus(),false
        );
    }

}
