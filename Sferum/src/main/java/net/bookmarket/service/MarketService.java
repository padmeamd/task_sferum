package net.bookmarket.service;


import net.bookmarket.entities.input.Book;
import net.bookmarket.entities.input.MarketAccount;
import net.bookmarket.entities.output.*;
import net.bookmarket.exceptions.BookMarketException;
import net.bookmarket.repository.MarketRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class MarketService {

    private final MarketRepository marketRepository;

    @Autowired
    public MarketService(MarketRepository marketRepository) {
        this.marketRepository = marketRepository;
    }

    public AccountResp getAccount() {
        return mapAccount(marketRepository.getAccount());
    }

    public ProductListResp getProducts() {
        return mapProducts(marketRepository.getBooks());
    }

    public void makeDeal(int bookId, int amount) throws BookMarketException {
        marketRepository.makeDeal(bookId, amount);
    }

    private ProductEntryResp bookToProductEntry(Book book, int id) {
        return new ProductEntryResp(id, new BookResp(book.getName(), book.getAuthor()), book.getPrice(), book.getAmount());
    }

    private BookEntryResp bookToBookEntry(Book book) {
        return new BookEntryResp(new BookResp(book.getName(), book.getAuthor()), book.getAmount());
    }

    private ProductListResp mapProducts(List<Book> data) {
        List<ProductEntryResp> list = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            list.add(bookToProductEntry(data.get(i), i));
        }
        return new ProductListResp(list);
    }

    private AccountResp mapAccount(MarketAccount account) {
        List<BookEntryResp> list = new ArrayList<>();
        for (Book book : account.getBooks()) {
            list.add(bookToBookEntry(book));
        }
        return new AccountResp(list, account.getMoney());
    }

}
