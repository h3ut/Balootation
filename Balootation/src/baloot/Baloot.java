package baloot;

import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JFrame;

/**
 *
 * @author RedDragon
 */
public class Baloot {
    
    public static void main(String[] args) throws InterruptedException {
        
        System.out.println("game is starting...");
        System.out.println("yeah");
        
        JFrame frame = new JFrame();
        
        GraphicsEngine graf = new GraphicsEngine();
        frame.add(graf);
        frame.setVisible(true);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addMouseListener(graf);
        
        CardShuffler.RandomShuffle(graf.getCards());
        ArrayList<ArrayList<Card>> dealed = CardDealer.dealCardsNormal(graf.getCards(), 3);
        CardDealer.dealRestOfCardsNormal(graf.getCards(), dealed, 3);
        
        
        
        graf.setPlayerMe(dealed.get(0));
        graf.setPlayerThemFirst(dealed.get(1));
        graf.setPlayerFriend(dealed.get(2));
        graf.setPlayerThemSecond(dealed.get(3));
        
        
        
        while(true){
            System.out.println(graf.getUserInput());
            CardShuffler.threeAces(graf.getCards());
            dealed = CardDealer.dealCardsNormal(graf.getCards(), 3);
            CardDealer.dealRestOfCardsNormal(graf.getCards(), dealed, 3);
        
            Collections.sort(dealed.get(0));
            Collections.sort(dealed.get(1));
            Collections.sort(dealed.get(2));
            Collections.sort(dealed.get(3));
            
            
            graf.setPlayerMe(dealed.get(0));
            graf.setPlayerThemFirst(dealed.get(1));
            graf.setPlayerFriend(dealed.get(2));
            graf.setPlayerThemSecond(dealed.get(3));
        }
        
    }
    
}
