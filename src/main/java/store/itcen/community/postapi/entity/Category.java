package store.itcen.community.postapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Category {
    BACKEND("BACKEND"),
    FRONTEND("FRONTEND"),
    DATABASE("DATABASE"),
    AWS("AWS");

    private String cat;



}
