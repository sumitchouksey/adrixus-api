package adrixus.com.controllers;


import adrixus.com.services.CustomerApiService;
import com.nidavellir.book.request.json.handler.RequestJsonHandler;
import com.nidavellir.book.response.json.handler.ResponseJsonHandler;
import com.nidavellir.book.response.json.handler.util.ResponseJsonUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerApiController {

    @Autowired
    private CustomerApiService customerApiService;

    /**
     * Add New Customers
     * @param requestJsonHandler
     * @return
     */
    @PostMapping("/add-customer")
    public ResponseJsonHandler addCustomer(@RequestBody RequestJsonHandler requestJsonHandler){

        String name =requestJsonHandler.getStringValue("name");
        if(name==null || StringUtils.isBlank(name))
            return ResponseJsonUtil.getResponse("Please provide name",400,"Bad Request",true);

        String addressLine1 =requestJsonHandler.getStringValue("addressLine1");
        String addressLine2 =requestJsonHandler.getStringValue("addressLine2");

        Long countryId  = requestJsonHandler.getLongValue("countryId");
        if(countryId==null)
            return ResponseJsonUtil.getResponse("Please provide countryId",400,"Bad Request",true);

        Long stateId  = requestJsonHandler.getLongValue("stateId");
        if(stateId==null)
            return ResponseJsonUtil.getResponse("Please provide stateId",400,"Bad Request",true);


        Long cityId  = requestJsonHandler.getLongValue("cityId");
        if(cityId==null)
            return ResponseJsonUtil.getResponse("Please provide cityId",400,"Bad Request",true);

        return  customerApiService.addCustomer(name.trim(),addressLine1,addressLine2,countryId,stateId,cityId);
    }
}
