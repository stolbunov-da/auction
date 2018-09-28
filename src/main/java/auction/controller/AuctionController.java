package auction.controller;

import auction.service.CopartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by stolbunovd
 * on 9/27/18
 */
@RestController
public class AuctionController {

    @Autowired
    private CopartService copartService;

    @GetMapping("/cars")
    public String getCars() throws IOException {
        return copartService.readCopart2();
    }
}
