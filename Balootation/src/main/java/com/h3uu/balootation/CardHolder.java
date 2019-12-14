package com.h3uu.balootation;

import com.h3uu.balootation.card.Card;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author RedDragon
 */

@Data
public class CardHolder {
    private List<Hand> hands;
    private ArrayList<ArrayList<Mshroo3>> mshare3;

    public CardHolder(List<Hand> hands){
        this.hands = hands;
    }
    
    public void sort(){
        for(Hand hand: this.hands) hand.sort();
    }
    
    public void calculateMshare3(int gameType){
        mshare3 = new ArrayList<>();
        for(Hand hand: this.hands) {
            mshare3.add(Mshroo3.findAllMshroo3(hand.getCards(), gameType));
        }
    }

    public ArrayList<ArrayList<Mshroo3>> getMshare3(){
        return mshare3;
    }
    
    public List<Card> getCardsMe(){
        return hands.get(0).getCards();
    }
    
    public List<Card> getCardsThemFirst(){
        return hands.get(1).getCards();
    }
    
    public List<Card> getCardsFriend(){
        return hands.get(2).getCards();
    }
    
    public List<Card> getCardsThemSecond(){
        return hands.get(3).getCards();
    }
    
    
    
}
