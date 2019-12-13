package com.h3uu.balootation;

import com.h3uu.balootation.*;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author RedDragon
 */
public class Baloot {
    
    public static void main(String[] args) throws InterruptedException {
        testMsharee3();

    }

    public static void testMsharee3() throws InterruptedException {
        System.out.println("game is starting...");

        JFrame frame = new JFrame();

        GraphicsEngine graph = new GraphicsEngine();
        frame.add(graph);
        frame.setVisible(true);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addMouseListener(graph);

        CardShuffler.RandomShuffle(graph.getCards());
        CardHolder cardHolder = CardDealer.dealCardsNormal(graph.getCards(), 3);
        CardDealer.dealRestOfCardsNormal(cardHolder, graph.getCards(), 3, 0);

//        ArrayList<ArrayList<Card>> dealed = CardDealer.dealCardsNormal(graph.getCards(), 3);
//        CardDealer.dealRestOfCardsNormal(graph.getCards(), dealed, 3);


        graph.setAllPlayers(cardHolder);
//        graph.setPlayerMe(dealed.get(0));
//        graph.setPlayerThemFirst(dealed.get(1));
//        graph.setPlayerFriend(dealed.get(2));
//        graph.setPlayerThemSecond(dealed.get(3));



        while(true){

            System.out.println(graph.getUserInput());
            CardShuffler.threeAces(graph.getCards());
            cardHolder = CardDealer.dealCardsNormal(graph.getCards(), 3);

            //CardDealer.dealRestOfCardsNormal(cardHolder, graph.getCards(), 3, 0);
            graph.setPlayedByPlayerMe(graph.getCards().get(20));

            cardHolder.sort();
            graph.setAllPlayers(cardHolder);


            ObjectClicked userInput = graph.getUserInput();
            int boughtBy = -1;
            if(userInput.isHandMe())
                boughtBy = 0;
            if(userInput.isHandThemFirst())
                boughtBy = 1;
            if(userInput.isHandFriend())
                boughtBy = 2;
            if(userInput.isHandThemSecond())
                boughtBy = 3;
            CardDealer.dealRestOfCardsNormal(cardHolder, graph.getCards(), 3, boughtBy);




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
            graph.setPlayedByPlayerMe(null);
            graph.setAllPlayers(cardHolder);
        }
    }


}
