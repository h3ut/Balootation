package com.h3uu.balootation;

import com.h3uu.balootation.card.Card;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class Hand {

    List<Card> cards;

    public Hand(){
        cards = new ArrayList<>();
    }

    public void addCard(Card card){
        this.cards.add(card);
    }

    public void addCard(List<Card> card){
        this.cards.addAll(card);
    }

    public void sort(){
        Collections.sort(this.cards);
    }
}
