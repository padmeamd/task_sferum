package net.bookmarket.controllers;


import net.bookmarket.entities.input.Deal;
import net.bookmarket.entities.output.AccountResp;
import net.bookmarket.entities.output.ProductListResp;
import net.bookmarket.exceptions.BookMarketException;
import net.bookmarket.service.MarketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MarketController {

    private final MarketService service;

    @Autowired
    public MarketController(MarketService service) {
        this.service = service;
    }

    @GetMapping("/account")
    public AccountResp getAccount() {
        return service.getAccount();
    }

    @PostMapping("/market/deal")
    public ResponseEntity<String> makeDeal(@RequestBody Deal entry) {
        log.info("Got a new deal " + entry);
        try {
            service.makeDeal(entry.getId(), entry.getAmount());
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (BookMarketException ex) {
            return new ResponseEntity<>(ex.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/market")
    public ProductListResp getBooks() {
        return service.getProducts();
    }
}
