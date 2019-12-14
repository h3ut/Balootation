package com.h3uu.balootation;

import com.h3uu.balootation.card.Card;
import com.h3uu.balootation.card.Rank;
import com.h3uu.balootation.card.Suit;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author RedDragon
 */
public class GraphicsEngine extends JPanel implements Runnable, MouseListener{

    private final String imagesPath = "/cardsimages/";
    private Image coveredImage;
    private List<Card> PlayerMe;
    private List<Card> PlayerThemFirst;
    private List<Card> PlayerFriend;
    private List<Card> PlayerThemSecond;
    
    // players hands
    private Card playedByPlayerMe;
    private Card playedByPlayerThemFirst;
    private Card playedByPlayerFriend;
    private Card playedByPlayerThemSecond;

    // Score
    private int us,them;

    // Shra
    private boolean shouldBuy;
    private Image hokmImage;
    private Image sunImage;

    
    // communicate with main class
    private ObjectClicked anythingClicked;
    
    // Drawing constants
    private final int padding = 20;
    private boolean showOtherPlayersCards = true;
    public GraphicsEngine(){
        anythingClicked = null;
        
        PlayerMe = new ArrayList<>();
        PlayerFriend = new ArrayList<>();
        PlayerThemFirst = new ArrayList<>();
        PlayerThemSecond = new ArrayList<>();
        
        shouldBuy = false;
        getCoveredCardImage();
        getShraImages();

        us = 0;
        them = 0;

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Sizing
        Dimension size = this.getSize();
        Point pos;

        // Draw player me position
        for (int i = 0; i < PlayerMe.size(); i++) {
            pos = getCardPositionForMe(i, size);
            g.drawImage(PlayerMe.get(i).getImage(), pos.x, pos.y, this);
        }

        // Draw player friend position
        for (int i = 0; i < PlayerFriend.size(); i++) {
            pos = getCardPositionForFriend(i, size);
            if(showOtherPlayersCards)
                g.drawImage(PlayerFriend.get(i).getImage(), pos.x, pos.y, this);
            else
                g.drawImage(coveredImage, pos.x, pos.y, this);
        }

        // Draw player them first position
        for (int i = 0; i < PlayerThemFirst.size(); i++) {
            pos = getCardPositionForThemFirst(i, size);
            if(showOtherPlayersCards)
                g.drawImage(PlayerThemFirst.get(i).getImage(), pos.x, pos.y, this);
            else
                g.drawImage(coveredImage, pos.x, pos.y, this);
        }

        // Draw player them second position
        for (int i = 0; i < PlayerThemSecond.size(); i++) {
            pos = getCardPositionForThemSecond(i, size);
            if(showOtherPlayersCards)
                g.drawImage(PlayerThemSecond.get(i).getImage(), pos.x, pos.y, this);
            else
                g.drawImage(coveredImage, pos.x, pos.y, this);
        }

        // Draw played cards //
        if(playedByPlayerMe != null){
            pos = getCardPositionForPlayedByMe(size);
            g.drawImage(playedByPlayerMe.getImage(), pos.x, pos.y, this);
        }

        if(playedByPlayerFriend != null){
            pos = getCardPositionForPlayedByFriend(size);
            g.drawImage(playedByPlayerFriend.getImage(), pos.x, pos.y, this);
        }

        if(playedByPlayerThemFirst != null){
            pos = getCardPositionForPlayedByThemFirst(size);
            g.drawImage(playedByPlayerThemFirst.getImage(), pos.x, pos.y, this);
        }

        if(playedByPlayerThemSecond != null){
            pos = getCardPositionForPlayedByThemSecond(size);
            g.drawImage(playedByPlayerThemSecond.getImage(), pos.x, pos.y, this);
        }

        // draw score
        g.drawString("US  : " + us, 10, 20);
        g.drawString("THEM: " + them, 10, 40);

        // buy?
        if(shouldBuy){
            pos = getBuySun(size);
            g.drawImage(sunImage, pos.x, pos.y, this);
            pos = getBuy7km(size);
            g.drawImage(hokmImage, pos.x, pos.y, this);
        }
    }

    // cards in hands positions
    private Point getCardPositionForMe(int cardNumber, Dimension size){
        int XStartForPlayerMe = (size.width - (8 * 36 + 36)) / 2 + cardNumber * 36;
        int YStartForPlayerMe = size.height - 97 - padding;
        return new Point(XStartForPlayerMe,YStartForPlayerMe);
    }
    private Point getCardPositionForFriend(int cardNumber, Dimension size){
        int XStartForPlayerFriend = (size.width - (8 * 36 + 36)) / 2+ cardNumber * 36;
        int YStartForPlayerFriend = padding;
        return new Point(XStartForPlayerFriend ,YStartForPlayerFriend);
    }
    private Point getCardPositionForThemFirst(int cardNumber, Dimension size){
        int XStartForPlayerThemFirst = size.width - padding - 73;
        int YStartForPlayerThemFirst = (size.height - (8 * 48 + 48)) / 2 + cardNumber * 48;
        return new Point(XStartForPlayerThemFirst,YStartForPlayerThemFirst);
    }
    private Point getCardPositionForThemSecond(int cardNumber, Dimension size){
        int XStartForPlayerThemSecond = padding;
        int YStartForPlayerThemSecond = (size.height - (8 * 48 + 48)) / 2 + cardNumber * 48;
        return new Point(XStartForPlayerThemSecond,YStartForPlayerThemSecond);
    }

    // cards played positions
    private Point getCardPositionForPlayedByMe(Dimension size){
        int xPosition = (size.width - 73) / 2;
        int yPosition= (size.height - 97) / 2 + 50;
        return new Point(xPosition, yPosition);
    }
    private Point getCardPositionForPlayedByFriend(Dimension size){
        int xPosition = (size.width - 73) / 2;
        int yPosition= (size.height - 97) / 2 - 50;
        return new Point(xPosition, yPosition);
    }
    private Point getCardPositionForPlayedByThemFirst(Dimension size){
        int xPosition = (size.width - 97) / 2 + 88;
        int yPosition= (size.height - 97) / 2;
        return new Point(xPosition, yPosition);
    }
    private Point getCardPositionForPlayedByThemSecond(Dimension size){
        int xPosition = (size.width - 97) / 2 - 65;
        int yPosition= (size.height - 97) / 2;
        return new Point(xPosition, yPosition);
    }

    // buy positions
    private Point getBuySun(Dimension size){
        int xPosition = (size.width - 97) / 2 + 85;
        int yPosition= size.height - (size.height - 97) / 3;
        return new Point(xPosition, yPosition);
    }
    private Point getBuy7km(Dimension size){
        int xPosition = (size.width - 97) / 2 - 65;
        int yPosition= size.height - (size.height - 97) / 3;
        return new Point(xPosition, yPosition);
    }

    // handle mouse click and find who was clicked
    private void handleMouseRelease(Point placeClicked){
        placeClicked.x -= 8;
        placeClicked.y -= 31;
        
        Dimension size = this.getSize();
        // maybe buying card?
        if(shouldBuy){
            // is it sun?
            if(isPointInBox(placeClicked, getBuySun(size), new Point(getBuySun(size).x + sunImage.getWidth(null), getBuySun(size).y + sunImage.getHeight(null)))){
                anythingClicked = new ObjectClicked();
                anythingClicked.setIsSun(true);
            }
            // is it 7km?
            if(isPointInBox(placeClicked, getBuy7km(size), new Point(getBuy7km(size).x + hokmImage.getWidth(null), getBuy7km(size).y + hokmImage.getHeight(null)))){
                anythingClicked = new ObjectClicked();
                anythingClicked.setIsHokm(true);
            }
        }
        
        Point pos;
        // maybe clicked a card in Me hand?
        for (int i = PlayerMe.size() - 1; i >= 0; i--) {
            pos = getCardPositionForMe(i, size);
            if(isPointInBox(placeClicked, pos, new Point(pos.x + PlayerMe.get(i).getImage().getWidth(null), pos.y + PlayerMe.get(i).getImage().getHeight(null)))){
                anythingClicked = new ObjectClicked();
                anythingClicked.setWhatCard(PlayerMe.get(i));
                anythingClicked.setHandMe(true);
                break;
            }
        }
        // only clickable if they are not covered
        if(showOtherPlayersCards){

            // maybe clicked a card in Friend hand?
            for (int i = PlayerFriend.size() - 1; i >= 0; i--) {
                pos = getCardPositionForFriend(i, size);
                if(isPointInBox(placeClicked, pos, new Point(pos.x + PlayerFriend.get(i).getImage().getWidth(null), pos.y + PlayerFriend.get(i).getImage().getHeight(null)))){
                    anythingClicked = new ObjectClicked();
                    anythingClicked.setWhatCard(PlayerFriend.get(i));
                    anythingClicked.setHandFriend(true);
                    break;
                }
            }

            // maybe clicked a card in Them First hand?
            for (int i = PlayerThemFirst.size() - 1; i >= 0; i--) {
                pos = getCardPositionForThemFirst(i, size);
                if(isPointInBox(placeClicked, pos, new Point(pos.x + PlayerThemFirst.get(i).getImage().getWidth(null), pos.y + PlayerThemFirst.get(i).getImage().getHeight(null)))){
                    anythingClicked = new ObjectClicked();
                    anythingClicked.setWhatCard(PlayerThemFirst.get(i));
                    anythingClicked.setHandThemFirst(true);
                    break;
                }
            }

            // maybe clicked a card in Them Second hand?
            for (int i = PlayerThemSecond.size() - 1; i >= 0; i--) {
                pos = getCardPositionForThemSecond(i, size);
                if(isPointInBox(placeClicked, pos, new Point(pos.x + PlayerThemSecond.get(i).getImage().getWidth(null), pos.y + PlayerThemSecond.get(i).getImage().getHeight(null)))){
                    anythingClicked = new ObjectClicked();
                    anythingClicked.setWhatCard(PlayerThemSecond.get(i));
                    anythingClicked.setHandThemSecond(true);
                    break;
                }
            }
        }
        // maybe clicked played cards? //
        // card played by Me
        if(playedByPlayerMe != null){
            pos = getCardPositionForPlayedByMe(size);
            if(isPointInBox(placeClicked, pos, new Point(pos.x + playedByPlayerMe.getImage().getWidth(null), pos.y + playedByPlayerMe.getImage().getHeight(null)))){
                anythingClicked = new ObjectClicked();
                anythingClicked.setWhatCard(playedByPlayerMe);
                anythingClicked.setPlayedByMe(true);
            }
        }
        // card played by Friend
        if(playedByPlayerFriend != null){
            pos = getCardPositionForPlayedByFriend(size);
            if(isPointInBox(placeClicked, pos, new Point(pos.x + playedByPlayerFriend.getImage().getWidth(null), pos.y + playedByPlayerFriend.getImage().getHeight(null)))){
                anythingClicked = new ObjectClicked();
                anythingClicked.setWhatCard(playedByPlayerFriend);
                anythingClicked.setPlayedByFriend(true);
            }
        }
        // card played by Them First
        if(playedByPlayerThemFirst != null){
            pos = getCardPositionForPlayedByThemFirst(size);
            if(isPointInBox(placeClicked, pos, new Point(pos.x + playedByPlayerThemFirst.getImage().getWidth(null), pos.y + playedByPlayerThemFirst.getImage().getHeight(null)))){
                anythingClicked = new ObjectClicked();
                anythingClicked.setWhatCard(playedByPlayerThemFirst);
                anythingClicked.setPlayedByThemFirst(true);
            }
        }
        // card played by Them Second
        if(playedByPlayerThemSecond != null){
            pos = getCardPositionForPlayedByThemSecond(size);
            if(isPointInBox(placeClicked, pos, new Point(pos.x + playedByPlayerThemSecond.getImage().getWidth(null), pos.y + playedByPlayerThemSecond.getImage().getHeight(null)))){
                anythingClicked = new ObjectClicked();
                anythingClicked.setWhatCard(playedByPlayerThemSecond);
                anythingClicked.setPlayedByThemSecond(true);
            }
        }
    }
    
    private boolean isPointInBox(Point p, Point boxBegin, Point boxEnd){
        if(boxBegin.x <= p.x && p.x <= boxEnd.x)
            return boxBegin.y <= p.y && p.y <= boxEnd.y;
        return false;
    }
    
    public ObjectClicked getUserInput() throws InterruptedException{
        while(anythingClicked == null) Thread.sleep(50);
        
        ObjectClicked answer = anythingClicked;
        anythingClicked = null;
        
        return answer;
    }
    
    
    public void setPlayerMe(List<Card> p){
        this.PlayerMe = p;
    }
    public void setPlayerFriend(List<Card> p){
        this.PlayerFriend = p;
    }
    public void setPlayerThemFirst(List<Card> p){
        this.PlayerThemFirst = p;
    }
    public void setPlayerThemSecond(List<Card> p){
        this.PlayerThemSecond = p;
        this.repaint();
    }

    public void setAllPlayers(CardHolder cardHolder){
        setPlayerMe(cardHolder.getCardsMe());
        setPlayerFriend(cardHolder.getCardsFriend());
        setPlayerThemFirst(cardHolder.getCardsThemFirst());
        setPlayerThemSecond(cardHolder.getCardsThemSecond());
    }
    
    
    public void setPlayedByPlayerMe(Card playedByPlayerMe) {
        this.playedByPlayerMe = playedByPlayerMe;
    }
    public void setPlayedByPlayerThemFirst(Card playedByPlayerThemFirst) {
        this.playedByPlayerThemFirst = playedByPlayerThemFirst;
    }
    public void setPlayedByPlayerFriend(Card playedByPlayerFriend) {
        this.playedByPlayerFriend = playedByPlayerFriend;
    }
    public void setPlayedByPlayerThemSecond(Card playedByPlayerThemSecond) {
        this.playedByPlayerThemSecond = playedByPlayerThemSecond;
    }

    public boolean isShouldBuy() {
        return shouldBuy;
    }
    public void setShouldBuy(boolean shouldBuy) {
        this.shouldBuy = shouldBuy;
    }

    public void setUs(int us) {
        this.us = us;
    }
    public void setThem(int them) {
        this.them = them;
    }
    public int getUs() {
        return us;
    }
    public int getThem() {
        return them;
    }


    private void getShraImages(){
        hokmImage = new ImageIcon(getClass().getResource(imagesPath + "7km.gif")).getImage();
        sunImage = new ImageIcon(getClass().getResource(imagesPath + "sun.gif")).getImage();
    }

    private void getCoveredCardImage(){
        coveredImage = new ImageIcon(getClass().getResource(imagesPath + "back_1.gif")).getImage();
    }
    
    
    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public void mouseClicked(MouseEvent e){}
    @Override
    public void mousePressed(MouseEvent e){}
    @Override
    public void mouseReleased(MouseEvent e) {
        handleMouseRelease(e.getPoint());
    }
    @Override
    public void mouseEntered(MouseEvent e){}
    @Override
    public void mouseExited(MouseEvent e){}

}
