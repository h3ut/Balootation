package com.h3uu.balootation.player;

import com.h3uu.balootation.card.Card;
import com.h3uu.balootation.card.Rank;
import com.h3uu.balootation.card.Suit;

import java.util.ArrayList;
import java.util.List;

public class PlayerMemory {

    private List<Card> heartsOnPlay;
    private List<Card> clubsOnPlay;
    private List<Card> diamondsOnPlay;
    private List<Card> spadesOnPlay;


    private List<List<Card>> playersCards;
    private List<List<Rank>> playerPossibleRanks;


    public PlayerMemory() {

        this.heartsOnPlay = new ArrayList<>();
        this.clubsOnPlay = new ArrayList<>();
        this.diamondsOnPlay = new ArrayList<>();
        this.spadesOnPlay = new ArrayList<>();



        // fill the list with all cards to see what have not been played
        for (int i = 0; i < 8; i++) {
            this.heartsOnPlay.add(new Card(Rank.HEARTS, Suit.values()[i + 6]));
        }

        for (int i = 0; i < 8; i++) {
            this.clubsOnPlay.add(new Card(Rank.CLUBS, Suit.values()[i + 6]));
        }

        for (int i = 0; i < 8; i++) {
            this.spadesOnPlay.add(new Card(Rank.SPADES, Suit.values()[i + 6]));
        }

        for (int i = 0; i < 8; i++) {
            this.diamondsOnPlay.add(new Card(Rank.DIAMONDS, Suit.values()[i + 6]));
        }


        // Fill players possible ranks to see if they no longer have a rank
        Rank[] ranks = Rank.values();
        this.playerPossibleRanks = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            this.playerPossibleRanks.add(new ArrayList<>());
            for (Rank rank : ranks) {
                this.playerPossibleRanks.get(i).add(rank);
            }
        }

        // Fill All possible cards for each player
        this.playersCards = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            this.playersCards.add(new ArrayList<>());
            this.playersCards.get(i).addAll(this.clubsOnPlay);
            this.playersCards.get(i).addAll(this.diamondsOnPlay);
            this.playersCards.get(i).addAll(this.heartsOnPlay);
            this.playersCards.get(i).addAll(this.spadesOnPlay);
        }
    }


    public void updateCardPlayed(Card card) {
        switch(card.getRank()){
            case CLUBS:
                this.clubsOnPlay.remove(card);
                break;
            case HEARTS:
                this.heartsOnPlay.remove(card);
                break;
            case DIAMONDS:
                this.diamondsOnPlay.remove(card);
                break;
            case SPADES:
                this.spadesOnPlay.remove(card);
                break;
        }

        for (List<Card> playerCards : this.playersCards) {
            playerCards.remove(card);
        }
    }

    public void updatePossibleRank(Rank rank, int playedBy){
        this.playerPossibleRanks.get(playedBy - 2).remove(rank);
        this.playersCards.get(playedBy - 2).removeIf(card -> card.getRank().equals(rank));
    }




































}
