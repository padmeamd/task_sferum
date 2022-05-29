package net.bookmarket.entities.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    private String author;
    private String name;
    private int price;
    private int amount;

}
