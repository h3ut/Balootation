package com.h3uu.balootation.card;

import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;

/*
 * @author Talal Al-Mutairi 
 */
public class Card implements Comparable{
    private final Suit suit;
    private final Rank rank;
    private final Image image;
    
    // drawing position
    private Point currentPoint = new Point(0,0);
    private Point FinishPoint = new Point(0,0);
    private int Zlevel = 0;
    
    
    public Card(Rank rank,Suit suit,ImageIcon imageLocation){
        this.suit = suit;
        this.rank = rank;
        image = imageLocation.getImage();
    }

    public Image getImage() {
        return image;
    }
    
    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
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
