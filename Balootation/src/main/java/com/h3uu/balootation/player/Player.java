package com.h3uu.balootation.player;

import com.h3uu.balootation.Hand;
import com.h3uu.balootation.card.Card;
import lombok.Data;

import java.util.List;

@Data
public abstract class Player {

    private Hand hand;
    private PlayerMemory memory;

    public Card pickCardToPlay(List<Card> cardsOnGround) {

        return null;
    }

    public void choosenCardResult(List<Card> cardsOnGround, int yourTurn, int pointsWon, int pointsLost) {


    }


}
