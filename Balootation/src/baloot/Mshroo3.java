package baloot;

import java.util.ArrayList;

/**
 *
 * @author RedDragon
 */
public class Mshroo3 {
    
    static final int m400 = 40;
    static final int m100 = 20;
    static final int m50 = 10;
    static final int mSra = 4;
    static final int mBaloot = 4;
    
    ArrayList<Card> mshroo3;
    private int type;
    
    public Mshroo3(ArrayList<Card> cards, int mshroo3Type) {
        mshroo3 = cards;
        type = mshroo3Type;
    }
    
    
    
    
    private static boolean checkFor400(ArrayList<Card> playerCards, ArrayList<Mshroo3> mshare3){
        //check for 400
        int countAces = 0;
        for (int i = 0; i < playerCards.size(); i++)
            if(playerCards.get(i).getSuit() == Card.ACE)
                countAces++;
        
        if(countAces == 4){
            // there is 400 in hand
            ArrayList<Card> aces = new ArrayList<>();
            for (int i = 0; i < playerCards.size(); i++)
                if(playerCards.get(i).getSuit() == Card.ACE)
                    aces.add(playerCards.get(i));
            
            playerCards.removeAll(aces);
            mshare3.add(new Mshroo3(aces, Mshroo3.m400));
            return true;
        }
        
        return false;
    }
    
    
    public static ArrayList<Mshroo3> findAllMshroo3(ArrayList<Card> cards, int gameType){
        // a list to be returned after the method finished
        ArrayList<Mshroo3> mshare3 = new ArrayList();
        
        // make copy of the arraylist to moddify later
        ArrayList<Card> playerCards = new ArrayList<Card>();
        for (int i = 0; i < cards.size(); i++) {
            playerCards.add(cards.get(i));
        }
        
        // check for baloot if it was hokom
        if(gameType != GameType.SUN){
            
            
        }
        
        // check for 400
        checkFor400(playerCards, mshare3);
        
        // check for 100
        
        // check for 50
        
        // check for sra
        
        
        
        
        return null;
    }
    
}
