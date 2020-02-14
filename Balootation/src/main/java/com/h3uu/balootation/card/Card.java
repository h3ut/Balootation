package com.h3uu.balootation.card;

import lombok.Getter;
import lombok.Setter;

import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;

/*
 * @author Talal Al-Mutairi 
 */

@Getter @Setter
public class Card implements Comparable{
    private final Suit suit;
    private final Rank rank;
    private Image image;
    
    // drawing position
    private Point currentPoint = new Point(0,0);
    private Point FinishPoint = new Point(0,0);
    private int Zlevel = 0;
    

    public Card(Rank rank,Suit suit){
        this.suit = suit;
        this.rank = rank;
    }

    public Card(Rank rank,Suit suit,ImageIcon imageLocation){
        this.suit = suit;
        this.rank = rank;
        image = imageLocation.getImage();
    }


    public void advanceCurrentToFinish(int speed){
        if(this.currentPoint.x < this.FinishPoint.x){
            this.currentPoint.x = Math.min(this.currentPoint.x + speed, this.FinishPoint.x);
        }else if(this.currentPoint.x > this.FinishPoint.x){
            this.currentPoint.x = Math.max(this.currentPoint.x - speed, this.FinishPoint.x);
        }
        
        if(this.currentPoint.y < this.FinishPoint.y){
            this.currentPoint.y = Math.min(this.currentPoint.y + speed, this.FinishPoint.y);
        }else if(this.currentPoint.y > this.FinishPoint.y){
            this.currentPoint.y = Math.max(this.currentPoint.y - speed, this.FinishPoint.y);
        }
    }

    @Override
    public boolean equals(Object o) {
        if(this.getClass() != o.getClass()){
            return false;
        }
        Card that = (Card) o;
        return this.getRank().equals(that.getRank()) && this.getSuit().equals(that.getSuit());
    }

    @Override
    public String toString() {
        return String.format("%s of %s",this.suit.toString(),this.rank.toString());
    }

    @Override
    public int compareTo(Object o) {
        Card that = (Card) o;
        if(this.rank == that.rank)
        {
            return -this.suit.compareTo(that.getSuit());
        }
        else
        {
            return this.rank.compareTo(that.getRank());
        }
    }
}
