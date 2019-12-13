package com.h3uu.balootation;


import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author RedDragon
 */
public class CardShuffler {
    
    // normal random shit
    public static void RandomShuffle(ArrayList<Card> cards){
        Collections.shuffle(cards);
    }
    
    // me player will get great cards
    public static void threeAces(ArrayList<Card> cards){
        RandomShuffle(cards);
        for (int myAceNumber = 0; myAceNumber < 3; myAceNumber++) {
            for (int lookForAce = myAceNumber+1; lookForAce < cards.size(); lookForAce++) {
                if(cards.get(lookForAce).getSuit() == Card.ACE){
                    Card temp = cards.get(myAceNumber);
                    cards.set(myAceNumber, cards.get(lookForAce));
                    cards.set(lookForAce, temp);
                    break;
                }
            }
        }
    }
    
    
    
    
    
}
