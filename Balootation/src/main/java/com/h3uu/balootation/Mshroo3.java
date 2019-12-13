package com.h3uu.balootation;

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
    static final int mBaloot = 2;
    
    ArrayList<Card> mshroo3;
    private int type;
    
    public Mshroo3(ArrayList<Card> cards, int mshroo3Type) {
        mshroo3 = cards;
        type = mshroo3Type;
    }
    
    
    private static int countCardSuit(ArrayList<Card> playerCards, int cardType){
        int counter = 0;
        for (int i = 0; i < playerCards.size(); i++)
            if(playerCards.get(i).getSuit() == cardType)
                counter++;
        return counter;
    }
    
    private static ArrayList<Card> getCardSuit(ArrayList<Card> playerCards, int cardType){
        ArrayList<Card> cards = new ArrayList<>();
        for (int i = 0; i < playerCards.size(); i++)
            if(playerCards.get(i).getSuit() == cardType)
                cards.add(playerCards.get(i));

        return cards;
    }
    
    private static ArrayList<Card> getConsecutiveCards(ArrayList<Card> playerCards, int numberOfConsecutiveCards){
        
        ArrayList<Card> consecutiveCards = new ArrayList();
        
        int count = 1;
        int CardRank = playerCards.get(0).getRank();
        Card prevCard = playerCards.get(0);
        for (int i = 1; i < playerCards.size() - 1; i++) {
            
            if(playerCards.get(i).getRank() == CardRank){
                if(prevCard.getSuit() - playerCards.get(i).getSuit() == 1){
                    count++;
                    prevCard = playerCards.get(i);
                }else{
                    count = 1;
                    prevCard = playerCards.get(i);
                }
            }else{
                count = 1;
                prevCard = playerCards.get(i);
                CardRank = playerCards.get(i).getRank();
            }
            
            
            if(count == numberOfConsecutiveCards){
                // the mshroo3 exist
                for (int j = 0; j < numberOfConsecutiveCards; j++) {
                    consecutiveCards.add( playerCards.get(i-j) );
                }
                return consecutiveCards;
            }
        }
        return null;
    }
    
    private static boolean checkFor400(ArrayList<Card> playerCards, ArrayList<Mshroo3> mshare3){
        //check for 400
        int countAces = countCardSuit(playerCards, Card.ACE);
        
        if(countAces == 4){
            // there is 400 in hand
            ArrayList<Card> aces = getCardSuit(playerCards, Card.ACE);
            
            playerCards.removeAll(aces);
            mshare3.add(new Mshroo3(aces, Mshroo3.m400));
            return true;
        }
        
        return false;
    }
    
    private static boolean checkFor100(ArrayList<Card> playerCards, ArrayList<Mshroo3> mshare3){
        boolean foundMshroo3 = false;
        
        // check for 4 Kings
        int counter = countCardSuit(playerCards, Card.KING);
        
        if(counter == 4){
            // there are 4 kings (100)
            ArrayList<Card> kings = getCardSuit(playerCards, Card.KING);
            
            playerCards.removeAll(kings);
            mshare3.add(new Mshroo3(kings, Mshroo3.m100));
            foundMshroo3 = true;
        }
        
        // check for 4 Queens
        counter = countCardSuit(playerCards, Card.QUEEN);
        
        if(counter == 4){
            // there are 4 queens (100)
            ArrayList<Card> queens = getCardSuit(playerCards, Card.QUEEN);
            
            playerCards.removeAll(queens);
            mshare3.add(new Mshroo3(queens, Mshroo3.m100));
            foundMshroo3 = true;
        }
        
        // check for 4 jacks
        counter = countCardSuit(playerCards, Card.JACK);
        
        if(counter == 4){
            // there are 4 jacks (100)
            ArrayList<Card> jacks = getCardSuit(playerCards, Card.JACK);
            
            playerCards.removeAll(jacks);
            mshare3.add(new Mshroo3(jacks, Mshroo3.m100));
            foundMshroo3 = true;
        }
        
        // check for 4 tens
        counter = countCardSuit(playerCards, Card.TEN);
        
        if(counter == 4){
            // there are 4 tens (100)
            ArrayList<Card> tens = getCardSuit(playerCards, Card.TEN);
            
            playerCards.removeAll(tens);
            mshare3.add(new Mshroo3(tens, Mshroo3.m100));
            foundMshroo3 = true;
        }
        
        
        // check for 5 consecutive cards
        
        ArrayList<Card> consecutiveCards = getConsecutiveCards(playerCards, 5);
        if(consecutiveCards != null){
            playerCards.removeAll(consecutiveCards);
            mshare3.add(new Mshroo3(consecutiveCards, m100));
        }
        
        return foundMshroo3;
    }
    
    private static boolean checkFor50(ArrayList<Card> playerCards, ArrayList<Mshroo3> mshare3){
        
        // check for 4 consecutive cards
        ArrayList<Card> consecutiveCards = getConsecutiveCards(playerCards, 4);
        if(consecutiveCards != null){
            playerCards.removeAll(consecutiveCards);
            mshare3.add(new Mshroo3(consecutiveCards, m50));
        }else{
            return false;
        }
        
        consecutiveCards = getConsecutiveCards(playerCards, 4);
        if(consecutiveCards != null){
            playerCards.removeAll(consecutiveCards);
            mshare3.add(new Mshroo3(consecutiveCards, m50));
        }
        return true;
    }
    
    private static boolean checkForSra(ArrayList<Card> playerCards, ArrayList<Mshroo3> mshare3){
        
        // check for 3 consecutive cards
        ArrayList<Card> consecutiveCards = getConsecutiveCards(playerCards, 3);
        if(consecutiveCards != null){
            playerCards.removeAll(consecutiveCards);
            mshare3.add(new Mshroo3(consecutiveCards, mSra));
        }else{
            return false;
        }
        
        consecutiveCards = getConsecutiveCards(playerCards, 3);
        if(consecutiveCards != null){
            playerCards.removeAll(consecutiveCards);
            mshare3.add(new Mshroo3(consecutiveCards, mSra));
        }
        return true;
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
            
            int counter;
            switch(gameType){
                case GameType.HOKM_CLUBS:
                    
                    counter = 0;
                    ArrayList<Card> clubsBaloot = new ArrayList();
                    for (int i = 0; i < playerCards.size(); i++) {
                        if(playerCards.get(i).getRank() == Card.CLUBS){
                            if(playerCards.get(i).getSuit() == Card.QUEEN){
                                counter++;
                                clubsBaloot.add(playerCards.get(i));
                            }
                            if(playerCards.get(i).getSuit() == Card.KING){
                                counter++;
                                clubsBaloot.add(playerCards.get(i));
                            }
                        }
                    }
                    if(counter == 2){
                        mshare3.add(new Mshroo3(clubsBaloot, mBaloot));
                    }
                    
                    break;
                case GameType.HOKM_DIAMONDS:
                    
                    counter = 0;
                    ArrayList<Card> diamondBaloot = new ArrayList();
                    for (int i = 0; i < playerCards.size(); i++) {
                        if(playerCards.get(i).getRank() == Card.DIAMODS){
                            if(playerCards.get(i).getSuit() == Card.QUEEN){
                                counter++;
                                diamondBaloot.add(playerCards.get(i));
                            }
                            if(playerCards.get(i).getSuit() == Card.KING){
                                counter++;
                                diamondBaloot.add(playerCards.get(i));
                            }
                        }
                    }
                    if(counter == 2){
                        mshare3.add(new Mshroo3(diamondBaloot, mBaloot));
                    }
                    
                    break;
                case GameType.HOKM_HEARTS:
                    
                    counter = 0;
                    ArrayList<Card> heartBaloot = new ArrayList();
                    for (int i = 0; i < playerCards.size(); i++) {
                        if(playerCards.get(i).getRank() == Card.HEARTS){
                            if(playerCards.get(i).getSuit() == Card.QUEEN){
                                counter++;
                                heartBaloot.add(playerCards.get(i));
                            }
                            if(playerCards.get(i).getSuit() == Card.KING){
                                counter++;
                                heartBaloot.add(playerCards.get(i));
                            }
                        }
                    }
                    if(counter == 2){
                        mshare3.add(new Mshroo3(heartBaloot, mBaloot));
                    }
                    
                    break;
                case GameType.HOKM_SPADES:
                    
                    counter = 0;
                    ArrayList<Card> SpadesBaloot = new ArrayList();
                    for (int i = 0; i < playerCards.size(); i++) {
                        if(playerCards.get(i).getRank() == Card.SPADES){
                            if(playerCards.get(i).getSuit() == Card.QUEEN){
                                counter++;
                                SpadesBaloot.add(playerCards.get(i));
                            }
                            if(playerCards.get(i).getSuit() == Card.KING){
                                counter++;
                                SpadesBaloot.add(playerCards.get(i));
                            }
                        }
                    }
                    if(counter == 2){
                        mshare3.add(new Mshroo3(SpadesBaloot, mBaloot));
                    }
                    
                    break;
                    default:
                        System.out.println("what the fuck it is not hokm");
            }
            
        }
        
        // check for 400
        checkFor400(playerCards, mshare3);
        
        // check for 100
        checkFor100(playerCards, mshare3);
        
        // check for 50
        checkFor50(playerCards, mshare3);
        
        // check for sra
        checkForSra(playerCards, mshare3);
        
        
        return mshare3;
    }

    @Override
    public String toString() {
        
        StringBuilder str = new StringBuilder();
        switch(type){
            case m400: str.append("400: ");
                break;
            case m100: str.append("100: ");
                break;
            case m50: str.append("50: ");
                break;
            case mSra: str.append("Sra: ");
                break;
            case mBaloot: str.append("Baloot: ");
                break;
            default:
                str.append("Error: ");
        }
        str.append("{ ");
        for (int i = 0; i < mshroo3.size(); i++) {
            str.append("(");
            str.append(mshroo3.get(i).toString());
            str.append(")");
            if(i != mshroo3.size()-1)
                str.append(", ");
        }
        str.append(" }");
        return str.toString();
    }
    
    
    
}
