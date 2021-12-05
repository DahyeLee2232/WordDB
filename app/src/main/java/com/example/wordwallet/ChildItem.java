package com.example.wordwallet;

public class ChildItem {
    public int id_pk;      //db primary key(삭제용)
    public String word;
    public String meaning;
    public String imageLink;

    public ChildItem(int id_pk, String word, String meaning, String imageLink){
        this.id_pk = id_pk;
        this.word = word;
        this.meaning = meaning;
        this.imageLink = imageLink;
    }

}
