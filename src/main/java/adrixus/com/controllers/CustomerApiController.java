package adrixus.com.controllers;


import adrixus.com.services.CustomerApiService;
import com.nidavellir.book.request.json.handler.RequestJsonHandler;
import com.nidavellir.book.response.json.handler.ResponseJsonHandler;
import com.nidavellir.book.response.json.handler.util.ResponseJsonUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerApiController {

    /** Initialize Customers Api Service Instance **/
    @Autowired
    private CustomerApiService customerApiService;

    /**
     * Add New Customers,
     * Handle validation Also
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
            return ResponseJsonUtil.getResponse("Please provide country",400,"Bad Request",true);

        Long stateId  = requestJsonHandler.getLongValue("stateId");
        if(stateId==null)
            return ResponseJsonUtil.getResponse("Please provide state",400,"Bad Request",true);


        Long cityId  = requestJsonHandler.getLongValue("cityId");
        if(cityId==null)
            return ResponseJsonUtil.getResponse("Please provide city",400,"Bad Request",true);

        return  customerApiService.addCustomer(name.trim(),addressLine1,addressLine2,countryId,stateId,cityId);
    }

    /**
     * Get customer and their cards details in paginated mode
     * Also handle validation of page index input
     * @param requestJsonHandler
     * @return
     */
    @PostMapping("/get-customers")
    public ResponseJsonHandler getCustomers(@RequestBody RequestJsonHandler requestJsonHandler){
        Integer index  = requestJsonHandler.getIntegerValue("index");
        if(index==null)
            return ResponseJsonUtil.getResponse("Please provide index",400,"Bad Request",true);

        Integer itemsPerIndex  = requestJsonHandler.getIntegerValue("itemsPerIndex");
        if(itemsPerIndex==null)
            return ResponseJsonUtil.getResponse("Please provide itemsPerIndex",400,"Bad Request",true);
        return customerApiService.getCustomers(index,itemsPerIndex);
    }
}
