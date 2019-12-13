package com.h3uu.balootation;

import com.h3uu.balootation.card.Card;
import com.h3uu.balootation.card.Rank;
import com.h3uu.balootation.card.Suit;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class CardDeck {

    private List<Card> deck;
    private int cardsDealt = 0;

    public CardDeck() {
        this.deck = new ArrayList<>();
        for(Rank rank: Rank.values()){
            for(Suit suit: Suit.values()){
                if(suit.getValue() <= 6)    continue;
                this.loadCard(rank, suit);
            }
        }
    }

    public CardDeck(Rank[] ranks, Suit[] suits){
        this.deck = new ArrayList<>();
        for(Rank rank: ranks){
            for(Suit suit: suits){
                this.loadCard(rank, suit);
            }
        }
    }

    private void loadCard(Rank rank, Suit suit){
        String path = String.format("/cardsimages/%s-%s.gif", rank.toString(), suit.toString());
        this.deck.add(new Card(rank, suit,new ImageIcon(getClass().getResource(path))));
    }

    public void reset(){
        this.cardsDealt = 0;
    }


    public List<Card> deal(int numberOfCards){
        List<Card> cards = new ArrayList<>();
        new ArrayList<>();
        return new ArrayList<>(this.deck.subList(this.cardsDealt, Math.min(this.cardsDealt++ + numberOfCards, this.deck.size())));
    }

    public Card dealOneCard(){
        return this.deck.get(this.cardsDealt++);
    }


}
