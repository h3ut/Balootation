package baloot;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author RedDragon
 */
public class CardHolder {

    private ArrayList<ArrayList<Card>> cards;

    public CardHolder(ArrayList<ArrayList<Card>> cards) {
        this.cards = cards;
    }
    
    
    public void sort(){
        Collections.sort(cards.get(0));
        Collections.sort(cards.get(1));
        Collections.sort(cards.get(2));
        Collections.sort(cards.get(3));
    }
    
    public ArrayList<ArrayList<Card>> getCardsAll(){
        return cards;
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
