package net.bookmarket.entities.output;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntryResp {

    private int id;
    private BookResp book;
    private int price;
    private int amount;

}
