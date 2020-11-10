package com.cocoblue.securitytest.dto.holiday;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@XmlRootElement(name = "item")
public class ItemDto {
    @XmlAttribute(name = "dateKind")
    private long dateKind;

    @XmlAttribute(name = "dateName")
    private String dateName;

    @XmlAttribute(name = "isHoliday")
    private Boolean isHoliday;

    @XmlAttribute(name = "locdate")
    private LocalDate locdate;

    @XmlAttribute(name = "seq")
    private long seq;
}
