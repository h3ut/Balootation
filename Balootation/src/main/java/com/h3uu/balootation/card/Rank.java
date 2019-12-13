package com.h3uu.balootation.card;

public enum Rank {

    CLUBS("Clubs"),
    DIAMONDS("Diamonds"),
    SPADES("Spades"),
    HEARTS("Hearts");

    private String displayName;

    Rank(String displayName){
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
