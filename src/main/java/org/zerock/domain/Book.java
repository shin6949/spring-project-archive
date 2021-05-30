package org.zerock.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private long bookId;
    private String name;
    private long isbn;
    private long location;
    private boolean isBorrowed;
}
