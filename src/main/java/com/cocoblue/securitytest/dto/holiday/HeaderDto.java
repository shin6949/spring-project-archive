package com.cocoblue.securitytest.dto.holiday;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@XmlRootElement(name = "header")
public class HeaderDto {
    @XmlAttribute(name = "resultCode")
    private String resultCode;

    @XmlAttribute(name = "resultMsg")
    private String resultMsg;
}
