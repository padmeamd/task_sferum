package net.bookmarket.entities.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookEntryResp {

    private BookResp book;
    private int amount;

}
