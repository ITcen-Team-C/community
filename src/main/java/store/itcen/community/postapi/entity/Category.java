package store.itcen.community.postapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Category {
    ALL("기타"),
    BACKEND("BACKEND"),
    FRONTEND("FRONTEND"),
    DATABASE("DATABASE"),
    AWS("AWS");

    private String cat;



}
