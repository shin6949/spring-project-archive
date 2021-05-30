package org.zerock.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KeepBook {
    private long id;
    private String name;
    private String writer;
    private long isbn;
    private int categoryCode;
    private String categoryName;
    private String location;
    private boolean isBorrowed;
}
