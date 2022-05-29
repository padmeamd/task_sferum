package net.bookmarket.entities.input;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MarketAccount {

    private final int money;
    private final List<Book> books;

}
