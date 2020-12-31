package adrixus.com.controllers;

import adrixus.com.services.CardApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cards")
public class CardApiController {

    @Autowired
    private CardApiService cardApiService;


}
