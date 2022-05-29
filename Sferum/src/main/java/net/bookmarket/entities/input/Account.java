package net.bookmarket.entities.input;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedHashMap;
import java.util.Map;

@ToString
@EqualsAndHashCode
public class Account {

    @Getter
    @Setter
    private int money;

    @Getter
    private final Map<String, Book> books;

    public Account(int money) {
        this.money = money;
        this.books = new LinkedHashMap<>();
    }
}
