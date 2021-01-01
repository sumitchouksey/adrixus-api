package adrixus.com.controllers;

import adrixus.com.services.CardApiService;
import adrixus.com.utility.AdrixusUtility;
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
@RequestMapping("/cards")
public class CardApiController {

    @Autowired
    private CardApiService cardApiService;


    /**
     * Add New Card to Customer
     * @param requestJsonHandler
     * @return
     */
    @PostMapping("/add-card")
    public ResponseJsonHandler addCard(@RequestBody RequestJsonHandler requestJsonHandler){

        Long cardNo  = requestJsonHandler.getLongValue("cardNo");
        if(cardNo==null)
            return ResponseJsonUtil.getResponse("Please provide cardNo",400,"Bad Request",true);
        else{
            if(AdrixusUtility.getSize(cardNo) <16 && AdrixusUtility.getSize(cardNo)> 16)
                return ResponseJsonUtil.getResponse("Please provide valid cardNo",400,"Bad Request",true);
        }

        String cardType =requestJsonHandler.getStringValue("cardType");
        if(cardType==null || StringUtils.isBlank(cardType))
            return ResponseJsonUtil.getResponse("Please provide cardType",400,"Bad Request",true);

        Short cvc  = requestJsonHandler.getShortValue("cvc");
        if(cvc==null)
            return ResponseJsonUtil.getResponse("Please provide cvv",400,"Bad Request",true);


        Short password  = requestJsonHandler.getShortValue("password");
        if(password==null)
            return ResponseJsonUtil.getResponse("Please provide password",400,"Bad Request",true);

        Long customerId  = requestJsonHandler.getLongValue("customerId");
        if(customerId==null)
            return ResponseJsonUtil.getResponse("Please provide customerId",400,"Bad Request",true);

        return  cardApiService.addCard(cardNo,cardType.trim(),cvc,password,customerId);
    }

}
