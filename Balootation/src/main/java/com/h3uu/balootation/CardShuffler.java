package com.h3uu.balootation;


import com.h3uu.balootation.card.Card;
import com.h3uu.balootation.card.Suit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author RedDragon
 */
public class CardShuffler {
    
    // normal random shit
    public static void RandomShuffle(CardDeck deck){
        Collections.shuffle(deck.getDeck());
    }
    
    // me player will get great cards
    public static void threeAces(CardDeck deck){
        RandomShuffle(deck);
        List<Card> cards = deck.getDeck();
        for (int myAceNumber = 0; myAceNumber < 3; myAceNumber++) {
            for (int lookForAce = myAceNumber+1; lookForAce < cards.size(); lookForAce++) {
                if(cards.get(lookForAce).getSuit() == Suit.ACE){
                    Card temp = cards.get(myAceNumber);
                    cards.set(myAceNumber, cards.get(lookForAce));
                    cards.set(lookForAce, temp);
                    break;
                }
            }
        }
    }
    
    
    
    
    
}
