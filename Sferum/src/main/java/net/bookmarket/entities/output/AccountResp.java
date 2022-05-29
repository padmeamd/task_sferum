package net.bookmarket.entities.output;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AccountResp {

    private final List<BookEntryResp> books;
    private int money;

}
