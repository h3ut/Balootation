package com.h3uu.balootation;

import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;

/*
 * @author Talal Al-Mutairi 
 */
public class Card implements Comparable{
    
    //Ranks
    public static final int CLUBS = 1;
    public static final int DIAMODS = 2;
    public static final int SPADES = 3;
    public static final int HEARTS = 4;
    
    
    //Suits
    public static final int TWO = 2;
    public static final int THREE = 3;
    public static final int FOUR = 4;
    public static final int FIVE = 5;
    public static final int SIX = 6;
    public static final int SEVEN = 7;
    public static final int EIGHT = 8;
    public static final int NINE = 9;
    public static final int TEN = 10;
    public static final int JACK = 11;
    public static final int QUEEN = 12;
    public static final int KING = 13;
    public static final int ACE = 14;
    
    private final int suit;
    private final int rank;
    private final Image image;
    
    // drawing position
    private Point currentPoint = new Point(0,0);
    private Point FinishPoint = new Point(0,0);
    private int Zlevel = 0;
    
    
    public Card(int rank,int suit,ImageIcon imageLocation){
        this.suit = suit;
        this.rank = rank;
        image = imageLocation.getImage();
    }

    public Image getImage() {
        return image;
    }
    
    public int getRank() {
        return rank;
    }

    public int getSuit() {
        return suit;
    }

    public Point getCurrentPoint(){
        return this.currentPoint;
    }
    
    public Point getFinishPoint(){
        return this.FinishPoint;
    }
    
    public void setCurrentPoint(Point p){
        this.currentPoint = p;
    }
    
    public void setFinishPoint(Point p){
        this.FinishPoint = p;
    }
    
    public void advanceCurrentToFinish(int speed){
        if(this.currentPoint.x < this.FinishPoint.x){
            this.currentPoint.x = (this.currentPoint.x+speed > this.FinishPoint.x) ? this.FinishPoint.x : this.currentPoint.x+speed;
        }else if(this.currentPoint.x > this.FinishPoint.x){
            this.currentPoint.x = (this.currentPoint.x-speed < this.FinishPoint.x) ? this.FinishPoint.x : this.currentPoint.x-speed;
        }
        
        if(this.currentPoint.y < this.FinishPoint.y){
            this.currentPoint.y = (this.currentPoint.y+speed > this.FinishPoint.y) ? this.FinishPoint.y : this.currentPoint.y+speed;
        }else if(this.currentPoint.y > this.FinishPoint.y){
            this.currentPoint.y = (this.currentPoint.y-speed < this.FinishPoint.y) ? this.FinishPoint.y : this.currentPoint.y-speed;
        }
    }
    
    private String stringOfRank(){
        switch(rank){
            case CLUBS: return "Clubs";
            case DIAMODS: return "Diamods";
            case HEARTS: return "Hearts";
            case SPADES: return "Spades";
            default: return null;
        }
    }
    private String stringOfSuit(){
        if(suit <= 10){
            return suit+"";
        }
        
        switch(suit){
            case JACK: return "J";
            case QUEEN: return "Q";
            case KING: return "K";
            case ACE: return "A";
            default: return null;
        }
    }
    
    @Override
    public String toString() {
        return String.format("%s of %s",stringOfSuit(),stringOfRank());
    }

    @Override
    public int compareTo(Object o) {
        Card that = (Card) o;
        if(this.rank == that.rank)
        {
            if(this.suit == that.suit)  return 0;
            else                        return -(this.suit - that.suit);
        }
        else
        {
            return ( this.rank - that.rank );
        }
    }
}
