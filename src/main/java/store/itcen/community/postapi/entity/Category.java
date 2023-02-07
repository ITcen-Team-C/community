package store.itcen.community.postapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Category {
    A("BACKEND"),
    B("FRONTEND"),
    C("DATABASE"),
    D("AWS");

    private String cat;



}
