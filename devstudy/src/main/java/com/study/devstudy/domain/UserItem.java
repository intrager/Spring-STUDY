package com.study.devstudy.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of={"uno", "itemId"})
public class UserItem {
    private int uno;
    private int itemId;

    private String itemName;
    private Integer price;
    private String description;
}
