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
        CardHolder cardHolder = CardDealer.dealCardsNormal(graf.getCards(), 3);
        CardDealer.dealRestOfCardsNormal(cardHolder, graf.getCards(), 3, 0);
        
//        ArrayList<ArrayList<Card>> dealed = CardDealer.dealCardsNormal(graf.getCards(), 3);
//        CardDealer.dealRestOfCardsNormal(graf.getCards(), dealed, 3);
        
        
        graf.setAllPlayers(cardHolder);
//        graf.setPlayerMe(dealed.get(0));
//        graf.setPlayerThemFirst(dealed.get(1));
//        graf.setPlayerFriend(dealed.get(2));
//        graf.setPlayerThemSecond(dealed.get(3));
        
        
        
        while(true){
            
            System.out.println(graf.getUserInput());
            CardShuffler.threeAces(graf.getCards());
            cardHolder = CardDealer.dealCardsNormal(graf.getCards(), 3);
            
            //CardDealer.dealRestOfCardsNormal(cardHolder, graf.getCards(), 3, 0);
            graf.setPlayedByPlayerMe(graf.getCards().get(20));
            
            cardHolder.sort();
            graf.setAllPlayers(cardHolder);
            
            
            ObjectClicked userInput = graf.getUserInput();
            int boughtBy = -1;
            if(userInput.isHandMe())
                boughtBy = 0;
            if(userInput.isHandThemFirst())
                boughtBy = 1;
            if(userInput.isHandFriend())
                boughtBy = 2;
            if(userInput.isHandThemSecond())
                boughtBy = 3;
            CardDealer.dealRestOfCardsNormal(cardHolder, graf.getCards(), 3, boughtBy);
            
            
            
            
            cardHolder.sort();
            cardHolder.calculateMshare3(GameType.SUN);
            
            ArrayList<ArrayList<Mshroo3>> mshare3 = cardHolder.getMshare3();
            StringBuilder msh = new StringBuilder();
            for (int i = 0; i < mshare3.size(); i++) {
                msh.append("Player " + i + ": [ ");
                
                for (int j = 0; j < mshare3.get(i).size(); j++) {
                    msh.append(mshare3.get(i).get(j).toString());
                    msh.append(" , ");
                }
                msh.append("]\n");
            }
            System.out.println(msh.toString());
            graf.setPlayedByPlayerMe(null);
            graf.setAllPlayers(cardHolder);
            
            
        }
        
    }
    
}
