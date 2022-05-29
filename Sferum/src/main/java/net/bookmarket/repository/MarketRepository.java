package net.bookmarket.repository;

import net.bookmarket.entities.input.Account;
import net.bookmarket.entities.input.Book;
import net.bookmarket.entities.input.MarketAccount;
import net.bookmarket.entities.input.MarketData;
import net.bookmarket.exceptions.BookMarketException;
import net.bookmarket.exceptions.OutOfBooksException;
import net.bookmarket.exceptions.LowBalanceException;
import net.bookmarket.exceptions.BookNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class MarketRepository {

    private Account account;

    private final Map<Integer, Book> books;

    public MarketRepository() {
        books = new LinkedHashMap<>();
    }

    public MarketAccount getAccount() {
        return new MarketAccount(account.getMoney(), account.getBooks() == null ? new ArrayList<>() : new ArrayList<>(account.getBooks().values()));
    }

    public List<Book> getBooks() {
        return new ArrayList<>(books.values());
    }

    public void makeDeal(int bookId, int amount) throws BookMarketException {
        if (!books.containsKey(bookId)) {
            throw new BookNotFoundException(String.valueOf(bookId));
        }
        makePurchase(bookId, amount);
    }

    private void makePurchase(int bookId, int amount) throws BookMarketException {
        Book book = books.get(bookId);
        if (book.getAmount() < amount || amount < 0) {
            throw new OutOfBooksException(String.valueOf(bookId));
        }
        int cost = book.getPrice() * amount;
        if (account.getMoney() < cost) {
            throw new LowBalanceException();
        }
        handlePurchase(bookId, cost, amount);
    }

    private void handlePurchase(int bookId, int cost, int amount) {

        Book book = books.get(bookId);
        if (amount == book.getAmount()) {
            books.remove(bookId);
        } else {
            book.setAmount(book.getAmount() - amount);
        }

        Book bookCopy = account.getBooks().get(book.getName());
        if (bookCopy == null) {
            bookCopy = new Book(book.getAuthor(), book.getName(), book.getPrice(), 0);
        }
        bookCopy.setAmount(bookCopy.getAmount() + amount);

        account.setMoney(account.getMoney() - cost);
        if (account.getBooks().containsKey(book.getName())) {
            account.getBooks().replace(book.getName(), bookCopy);
        } else {
            account.getBooks().put(book.getName(), bookCopy);
        }

        log.info("New book purchase! Book ID = " + bookId + ", amount = " + amount + ", cost = " + cost);

    }

    public void fillWithData(MarketData marketData) {
        this.account = new Account(marketData.getAccount().getMoney());
        for (int i = 0; i < marketData.getBooks().size(); i++) {
            books.put(i, marketData.getBooks().get(i));
        }
    }

}
