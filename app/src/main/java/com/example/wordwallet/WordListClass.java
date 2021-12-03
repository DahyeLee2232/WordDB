package com.example.wordwallet;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 *
 */

public class WordListClass {
    public static final int WORDLIST = 0;
    public static final int WORDS = 1;

    //private int viewType;
    private String listName;
    private boolean isExpanded;
    ArrayList<WordItem> wordList;

    public WordListClass(String listName, ArrayList<WordItem> wordList){
        this.listName = listName;
        this.wordList = wordList;
        //리스트가 펼쳐졌는가
        this.isExpanded = false;
    }

    //public int getViewType(){ return viewType;}
    public String getListName(){return listName;}
    public ArrayList<WordItem> getWordList(){return wordList;}
    public boolean getIsExpanded() {return isExpanded;}
    public void setIsExpanded(boolean tf){ isExpanded = tf;}
}

class WordItem{
    private String word;
    private String meaning;
    private String imageLink;

    public WordItem(String word, String meaning, String imageLink){
        this.word = word;
        this.meaning = meaning;
        this.imageLink = imageLink;
    }
}