package adrixus.com.services;

import adrixus.com.entities.Cities;
import adrixus.com.entities.Countries;
import adrixus.com.entities.States;
import adrixus.com.repository.CoreApiRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nidavellir.book.response.json.handler.ResponseJsonHandler;
import com.nidavellir.book.response.json.handler.util.ResponseConstants;
import com.nidavellir.book.response.json.handler.util.ResponseJsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CoreApiService {

    /** Autowiring repository layers **/

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CoreApiRepository coreApiRepository;

    /**
     * Get countries
     * @return
     */
    public ResponseJsonHandler getCountries() {
        ArrayNode data  = objectMapper.createArrayNode();
        List<Countries> countries = coreApiRepository.getCountries();
        countries.forEach(e->{
            ObjectNode node = objectMapper.createObjectNode();
            node.put("countryId",e.getId());
            node.put("name",e.getName());
            data.add(node);
        });
        return ResponseJsonUtil.getResponse(data,200, ResponseConstants.SUCCESS.getStatus(),false);
    }

    /**
     * Return all state for given countryId
     * @param countryId
     * @return
     */
    public ResponseJsonHandler getStates(Long countryId){
        ArrayNode data  = objectMapper.createArrayNode();
        List<States> states = coreApiRepository.getStates(countryId);
        states.forEach(e->{
            ObjectNode node = objectMapper.createObjectNode();
            node.put("stateId",e.getId());
            node.put("name",e.getName());
            data.add(node);
        });

        return ResponseJsonUtil.getResponse(
                data,200,ResponseConstants.SUCCESS.getStatus(),false
        );
    }

    /**
     * Returns all cities for given stateId
     * @param stateId
     * @return
     */
    public ResponseJsonHandler getCities(Long stateId){
        ArrayNode data  = objectMapper.createArrayNode();
        List<Cities> cities = coreApiRepository.getCities(stateId);
        cities.forEach(e->{
            ObjectNode node = objectMapper.createObjectNode();
            node.put("cityId",e.getId());
            node.put("name",e.getName());
            data.add(node);
        });

        return ResponseJsonUtil.getResponse(
                data,200,ResponseConstants.SUCCESS.getStatus(),false
        );
    }
}
