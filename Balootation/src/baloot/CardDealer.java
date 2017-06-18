
package baloot;

import java.util.ArrayList;

/**
 *
 * @author RedDragon
 */
public class CardDealer {
    
    
    public static ArrayList<ArrayList<Card>> dealCardsNormal(ArrayList<Card> cards, int startDealingFromPlayer){
        
        ArrayList<ArrayList<Card>> dealedCards = new ArrayList<ArrayList<Card>>();
        
        dealedCards.add(new ArrayList<Card>());
        dealedCards.add(new ArrayList<Card>());
        dealedCards.add(new ArrayList<Card>());
        dealedCards.add(new ArrayList<Card>());
        
        int nextCardToDealIndex = 0;
        for (int i = 0; i < 4; i++) {
            startDealingFromPlayer = (startDealingFromPlayer + 1) % 4;
            switch(startDealingFromPlayer){
                case 0: 
                    
                    dealedCards.get(0).add(cards.get(nextCardToDealIndex++));
                    dealedCards.get(0).add(cards.get(nextCardToDealIndex++));
                    dealedCards.get(0).add(cards.get(nextCardToDealIndex++));
                    break;
                case 1:  
                    
                    dealedCards.get(1).add(cards.get(nextCardToDealIndex++));
                    dealedCards.get(1).add(cards.get(nextCardToDealIndex++));
                    dealedCards.get(1).add(cards.get(nextCardToDealIndex++));
                    break;
                case 2:  
                    
                    dealedCards.get(2).add(cards.get(nextCardToDealIndex++));
                    dealedCards.get(2).add(cards.get(nextCardToDealIndex++));
                    dealedCards.get(2).add(cards.get(nextCardToDealIndex++));
                    break;
                case 3:  
                    
                    dealedCards.get(3).add(cards.get(nextCardToDealIndex++));
                    dealedCards.get(3).add(cards.get(nextCardToDealIndex++));
                    dealedCards.get(3).add(cards.get(nextCardToDealIndex++));
                    break;
            }
        }
        
        
        for (int player = 0; player < 4; player++) {
            startDealingFromPlayer = (startDealingFromPlayer + 1) % 4;
            switch(startDealingFromPlayer){
                case 0: 
                    
                    dealedCards.get(0).add(cards.get(nextCardToDealIndex++));
                    dealedCards.get(0).add(cards.get(nextCardToDealIndex++));
                    break;
                case 1:  
                    
                    dealedCards.get(1).add(cards.get(nextCardToDealIndex++));
                    dealedCards.get(1).add(cards.get(nextCardToDealIndex++));
                    break;
                case 2:  
                    
                    dealedCards.get(2).add(cards.get(nextCardToDealIndex++));
                    dealedCards.get(2).add(cards.get(nextCardToDealIndex++));
                    break;
                case 3:  
                    
                    dealedCards.get(3).add(cards.get(nextCardToDealIndex++));
                    dealedCards.get(3).add(cards.get(nextCardToDealIndex++));
                    break;
            }
        }
        
        
        return dealedCards;
    }
    
    public static ArrayList<ArrayList<Card>> dealRestOfCardsNormal(ArrayList<Card> cards, ArrayList<ArrayList<Card>> dealedCards, int startDealingFromPlayer){
        int nextCardToDealIndex = 20;
        
        for (int i = 0; i < 4; i++) {
            startDealingFromPlayer = (startDealingFromPlayer + 1) % 4;
            switch(startDealingFromPlayer){
                case 0: 
                    
                    dealedCards.get(0).add(cards.get(nextCardToDealIndex++));
                    dealedCards.get(0).add(cards.get(nextCardToDealIndex++));
                    dealedCards.get(0).add(cards.get(nextCardToDealIndex++));
                    break;
                case 1:  
                    
                    dealedCards.get(1).add(cards.get(nextCardToDealIndex++));
                    dealedCards.get(1).add(cards.get(nextCardToDealIndex++));
                    dealedCards.get(1).add(cards.get(nextCardToDealIndex++));
                    break;
                case 2:  
                    
                    dealedCards.get(2).add(cards.get(nextCardToDealIndex++));
                    dealedCards.get(2).add(cards.get(nextCardToDealIndex++));
                    dealedCards.get(2).add(cards.get(nextCardToDealIndex++));
                    break;
                case 3:  
                    
                    dealedCards.get(3).add(cards.get(nextCardToDealIndex++));
                    dealedCards.get(3).add(cards.get(nextCardToDealIndex++));
                    dealedCards.get(3).add(cards.get(nextCardToDealIndex++));
                    break;
            }
        }
        
        return dealedCards;
    }
    
    
    
}
