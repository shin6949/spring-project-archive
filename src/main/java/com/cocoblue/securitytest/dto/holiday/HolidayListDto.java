package com.cocoblue.securitytest.dto.holiday;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "response")
public class HolidayListDto {
    private List<ItemDto> itemDtoList;

    @XmlElement(name = "header")
    private HeaderDto headerDto;

    @XmlElementWrapper(name = "items")
    @XmlElement(name = "item")
    public List<ItemDto> getItemDtoList() {
        return itemDtoList;
    }

    public void setItemDtoList(List<ItemDto> itemDtoList) {
        this.itemDtoList = itemDtoList;
    }

    @XmlAttribute(name = "numOfRows")
    private long numOfRows;

    @XmlAttribute(name = "pageNo")
    private long pageNo;

    @XmlAttribute(name = "totalCount")
    private long totalCount;
}
