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
        List<Hand> hands = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            hands.add(new Hand());
        }

        for (int i = 0; i < 4; i++) {
            startDealingFromPlayer = (startDealingFromPlayer + 1) % 4;
            hands.get(startDealingFromPlayer).addCard(deck.deal(3));
        }
        
        for (int player = 0; player < 4; player++) {
            startDealingFromPlayer = (startDealingFromPlayer + 1) % 4;
            hands.get(startDealingFromPlayer).addCard(deck.deal(2));
        }
        
        return new CardHolder(hands);
    }
    
    public static void dealRestOfCardsNormal(CardHolder cardHolder, CardDeck deck, int startDealingFromPlayer, int boughtByPlayer){
        List<Hand> hands = cardHolder.getHands();
        if(boughtByPlayer < 0 || boughtByPlayer >= hands.size()){
            System.err.println("Invalid player bought card. Cannot deal the rest of the cards");
        }
        hands.get(boughtByPlayer).addCard(deck.dealOneCard());
        for (int i = 0; i < 4; i++) {
            startDealingFromPlayer = (startDealingFromPlayer + 1) % 4;
            hands.get(startDealingFromPlayer).addCard(deck.deal((boughtByPlayer == startDealingFromPlayer)? 2: 3));
        }
    }

}
