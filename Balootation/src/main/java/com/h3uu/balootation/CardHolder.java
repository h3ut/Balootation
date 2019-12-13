package com.h3uu.balootation;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author RedDragon
 */
public class CardHolder {

    private ArrayList<ArrayList<Card>> cards;
    private ArrayList<ArrayList<Mshroo3>> mshare3;
    
    public CardHolder(ArrayList<ArrayList<Card>> cards) {
        this.cards = cards;
    }
    
    
    public void sort(){
        Collections.sort(cards.get(0));
        Collections.sort(cards.get(1));
        Collections.sort(cards.get(2));
        Collections.sort(cards.get(3));
    }
    
    public void calculateMshare3(int gameType){
        mshare3 = new ArrayList<>();
        
        mshare3.add(Mshroo3.findAllMshroo3(cards.get(0), gameType));
        mshare3.add(Mshroo3.findAllMshroo3(cards.get(1), gameType));
        mshare3.add(Mshroo3.findAllMshroo3(cards.get(2), gameType));
        mshare3.add(Mshroo3.findAllMshroo3(cards.get(3), gameType));
    }
    
    
    public ArrayList<ArrayList<Card>> getCardsAll(){
        return cards;
    }
    
    public ArrayList<ArrayList<Mshroo3>> getMshare3(){
        return mshare3;
    }
    
    public ArrayList<Card> getCardsMe(){
        return cards.get(0);
    }
    
    public ArrayList<Card> getCardsThemFirst(){
        return cards.get(1);
    }
    
    public ArrayList<Card> getCardsFriend(){
        return cards.get(2);
    }
    
    public ArrayList<Card> getCardsThemSecond(){
        return cards.get(3);
    }
    
    
    
}
