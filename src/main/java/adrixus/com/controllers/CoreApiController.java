package adrixus.com.controllers;

import adrixus.com.services.CoreApiService;
import com.nidavellir.book.request.json.handler.RequestJsonHandler;
import com.nidavellir.book.response.json.handler.ResponseJsonHandler;
import com.nidavellir.book.response.json.handler.util.ResponseJsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/core")
public class CoreApiController {

    /** Initialize Core Api Service Instance **/
    @Autowired
    private CoreApiService coreApiService;


    /**
     * Api end point to return all countries
     * @return
     */

    @GetMapping("/get-countries")
    public ResponseJsonHandler getCountries(){
        return coreApiService.getCountries();
    }

    /**
     * Api end point to return all states of provided country
     * Handle validation Also
     * @return
     */
    @PostMapping("/get-states")
    public ResponseJsonHandler getStates(@RequestBody RequestJsonHandler requestJsonHandler){
        Long countryId  = requestJsonHandler.getLongValue("countryId");
        if(countryId==null){
            return ResponseJsonUtil.getResponse("Please provide countryId",400,"Bad Request",true);

        }
        return coreApiService.getStates(countryId);
    }
    /**
     * Api end point to return all cities of provided states
     * Handle validation Also
     * @return
     */
    @PostMapping("/get-cities")
    public ResponseJsonHandler getCities(@RequestBody RequestJsonHandler requestJsonHandler){
        Long stateId  = requestJsonHandler.getLongValue("stateId");
        if(stateId==null){
            return ResponseJsonUtil.getResponse("Please provide stateId",400,"Bad Request",true);

        }
        return coreApiService.getCities(stateId);
    }

}
