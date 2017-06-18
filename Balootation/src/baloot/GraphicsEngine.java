package baloot;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author RedDragon
 */
public class GraphicsEngine extends JPanel implements Runnable, MouseListener{

    private ArrayList<Card> cards;
    
    ArrayList<Card> PlayerMe;
    ArrayList<Card> PlayerThemFirst;
    ArrayList<Card> PlayerFriend;
    ArrayList<Card> PlayerThemSecond;
    
    // players hands
    Card playedByPlayerMe;
    Card playedByPlayerThemFirst;
    Card playedByPlayerFriend;
    Card playedByPlayerThemSecond;

    // Score
    private int us,them;

    // Shra
    private boolean shouldBuy = true;
    private Image hokmImage;
    private Image sunImage;

    
    // communicate with main class
    ObjectClicked anythingClicked;
    
    // Drawing constants
    private final int padding = 20;
    private boolean showOtherPlayersCards = true;
    public GraphicsEngine(){
        anythingClicked = null;
        
        PlayerMe = new ArrayList();
        PlayerFriend = new ArrayList();
        PlayerThemFirst = new ArrayList();
        PlayerThemSecond = new ArrayList();

        shouldBuy = true;
        cards = new ArrayList();
        fillTheDeck();
        getShraImages();

//        for (int i = 0; i < 8; i++) PlayerMe.add(cards.get(i));
//        for (int i = 8; i < 16; i++) PlayerFriend.add(cards.get(i));
//        for (int i = 16; i < 24; i++) PlayerThemFirst.add(cards.get(i));
//        for (int i = 24; i < 32; i++) PlayerThemSecond.add(cards.get(i));
        
        
//        playedByPlayerMe = cards.get(0);
//        playedByPlayerThemFirst = cards.get(1);
//        playedByPlayerFriend = cards.get(2);
//        playedByPlayerThemSecond = cards.get(3);

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
        }

        // Draw player them first position
        for (int i = 0; i < PlayerThemFirst.size(); i++) {
            pos = getCardPositionForThemFirst(i, size);
            if(showOtherPlayersCards)
                g.drawImage(PlayerThemFirst.get(i).getImage(), pos.x, pos.y, this);
        }

        // Draw player them second position
        for (int i = 0; i < PlayerThemSecond.size(); i++) {
            pos = getCardPositionForThemSecond(i, size);
            if(showOtherPlayersCards)
                g.drawImage(PlayerThemSecond.get(i).getImage(), pos.x, pos.y, this);
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
            if(boxBegin.y <= p.y && p.y <= boxEnd.y)
                return true;
        return false;
    }
    
    public ObjectClicked getUserInput() throws InterruptedException{
        while(anythingClicked == null) Thread.sleep(100);
        
        ObjectClicked answer = anythingClicked;
        anythingClicked = null;
        
        return answer;
    }
    
    
    public void setPlayerMe(ArrayList<Card> p){
        this.PlayerMe = p;
    }
    public void setPlayerFriend(ArrayList<Card> p){
        this.PlayerFriend = p;
    }
    public void setPlayerThemFirst(ArrayList<Card> p){
        this.PlayerThemFirst = p;
    }
    public void setPlayerThemSecond(ArrayList<Card> p){
        this.PlayerThemSecond = p;
        this.repaint();
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
    
    
    public ArrayList<Card> getCards() {
        return cards;
    }
    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
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
    
    


    private void fillTheDeck(){
        cards.add(new Card(Card.CLUBS,Card.ACE,new ImageIcon(getClass().getResource("cardsimages/ac.gif"))));
        cards.add(new Card(Card.CLUBS,Card.SEVEN,new ImageIcon(getClass().getResource("cardsimages/7c.gif"))));
        cards.add(new Card(Card.CLUBS,Card.EIGHT,new ImageIcon(getClass().getResource("cardsimages/8c.gif"))));
        cards.add(new Card(Card.CLUBS,Card.NINE,new ImageIcon(getClass().getResource("cardsimages/9c.gif"))));
        cards.add(new Card(Card.CLUBS,Card.TEN,new ImageIcon(getClass().getResource("cardsimages/tc.gif"))));
        cards.add(new Card(Card.CLUBS,Card.JACK,new ImageIcon(getClass().getResource("cardsimages/jc.gif"))));
        cards.add(new Card(Card.CLUBS,Card.QUEEN,new ImageIcon(getClass().getResource("cardsimages/qc.gif"))));
        cards.add(new Card(Card.CLUBS,Card.KING,new ImageIcon(getClass().getResource("cardsimages/kc.gif"))));

        cards.add(new Card(Card.DIAMODS,Card.ACE,new ImageIcon(getClass().getResource("cardsimages/ad.gif"))));
        cards.add(new Card(Card.DIAMODS,Card.SEVEN,new ImageIcon(getClass().getResource("cardsimages/7d.gif"))));
        cards.add(new Card(Card.DIAMODS,Card.EIGHT,new ImageIcon(getClass().getResource("cardsimages/8d.gif"))));
        cards.add(new Card(Card.DIAMODS,Card.NINE,new ImageIcon(getClass().getResource("cardsimages/9d.gif"))));
        cards.add(new Card(Card.DIAMODS,Card.TEN,new ImageIcon(getClass().getResource("cardsimages/td.gif"))));
        cards.add(new Card(Card.DIAMODS,Card.JACK,new ImageIcon(getClass().getResource("cardsimages/jd.gif"))));
        cards.add(new Card(Card.DIAMODS,Card.QUEEN,new ImageIcon(getClass().getResource("cardsimages/qd.gif"))));
        cards.add(new Card(Card.DIAMODS,Card.KING,new ImageIcon(getClass().getResource("cardsimages/kd.gif"))));

        cards.add(new Card(Card.SPADES,Card.ACE,new ImageIcon(getClass().getResource("cardsimages/as.gif"))));
        cards.add(new Card(Card.SPADES,Card.SEVEN,new ImageIcon(getClass().getResource("cardsimages/7s.gif"))));
        cards.add(new Card(Card.SPADES,Card.EIGHT,new ImageIcon(getClass().getResource("cardsimages/8s.gif"))));
        cards.add(new Card(Card.SPADES,Card.NINE,new ImageIcon(getClass().getResource("cardsimages/9s.gif"))));
        cards.add(new Card(Card.SPADES,Card.TEN,new ImageIcon(getClass().getResource("cardsimages/ts.gif"))));
        cards.add(new Card(Card.SPADES,Card.JACK,new ImageIcon(getClass().getResource("cardsimages/js.gif"))));
        cards.add(new Card(Card.SPADES,Card.QUEEN,new ImageIcon(getClass().getResource("cardsimages/qs.gif"))));
        cards.add(new Card(Card.SPADES,Card.KING,new ImageIcon(getClass().getResource("cardsimages/ks.gif"))));

        cards.add(new Card(Card.HEARTS,Card.ACE,new ImageIcon(getClass().getResource("cardsimages/ah.gif"))));
        cards.add(new Card(Card.HEARTS,Card.SEVEN,new ImageIcon(getClass().getResource("cardsimages/7h.gif"))));
        cards.add(new Card(Card.HEARTS,Card.EIGHT,new ImageIcon(getClass().getResource("cardsimages/8h.gif"))));
        cards.add(new Card(Card.HEARTS,Card.NINE,new ImageIcon(getClass().getResource("cardsimages/9h.gif"))));
        cards.add(new Card(Card.HEARTS,Card.TEN,new ImageIcon(getClass().getResource("cardsimages/th.gif"))));
        cards.add(new Card(Card.HEARTS,Card.JACK,new ImageIcon(getClass().getResource("cardsimages/jh.gif"))));
        cards.add(new Card(Card.HEARTS,Card.QUEEN,new ImageIcon(getClass().getResource("cardsimages/qh.gif"))));
        cards.add(new Card(Card.HEARTS,Card.KING,new ImageIcon(getClass().getResource("cardsimages/kh.gif"))));
    }

    private void getShraImages(){
        hokmImage = new ImageIcon(getClass().getResource("cardsimages/7km.gif")).getImage();
        sunImage = new ImageIcon(getClass().getResource("cardsimages/sun.gif")).getImage();
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
