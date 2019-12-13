package com.h3uu.balootation;

import com.h3uu.balootation.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author RedDragon
 */
public class CardDealer {
    
    
    public static CardHolder dealCardsNormal(CardDeck deck, int startDealingFromPlayer){

        ArrayList<ArrayList<Card>> dealtCards = new ArrayList<>();

        dealtCards.add(new ArrayList<>());
        dealtCards.add(new ArrayList<>());
        dealtCards.add(new ArrayList<>());
        dealtCards.add(new ArrayList<>());

        for (int i = 0; i < 4; i++) {
            startDealingFromPlayer = (startDealingFromPlayer + 1) % 4;
            dealtCards.get(startDealingFromPlayer).addAll(deck.deal(3));
        }
        
        for (int player = 0; player < 4; player++) {
            startDealingFromPlayer = (startDealingFromPlayer + 1) % 4;
            dealtCards.get(startDealingFromPlayer).addAll(deck.deal(2));
        }
        
        return new CardHolder(dealtCards);
    }
    
    public static void dealRestOfCardsNormal(CardHolder cardHolder, CardDeck deck, int startDealingFromPlayer, int boughtByPlayer){
        ArrayList<ArrayList<Card>> dealtCards = cardHolder.getCardsAll();
        if(boughtByPlayer < 0 || boughtByPlayer >= dealtCards.size()){
            System.err.println("Invalid player bought card. Cannot deal the rest of the cards");
        }

        dealtCards.get(boughtByPlayer).add(deck.dealOneCard());
        
        for (int i = 0; i < 4; i++) {
            startDealingFromPlayer = (startDealingFromPlayer + 1) % 4;


            dealtCards.get(startDealingFromPlayer).addAll(deck.deal((boughtByPlayer == startDealingFromPlayer)? 2: 3));


        }
    }
    
    
    
}
