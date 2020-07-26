/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playingCardSequence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.math.*;


/**
 *
 * @author Hp
 */
public class Cards {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //1. One card is represented as suit#rank
        //2. For suit, C is used for Club, H for Hearts, D for Diamond and S for Spade
        //3. For rank, A is used for Ace, K for King, Q for Queen, J for Joker and other numbers from 2-10.
        //4. Multiple cards are represented as "card1,card2,card3" in string format 
        
//        EX 1: "D#K,D#A,D#2" represents King of Diamond,Ace of Diamond and 2 of Diamond
//        EX 2: "H#A,H#2,H#3"  represents Ace of Heart ,2 of Heart, and 3 of Heart
        //5. to check if cards are in sequence String "cards" need to be changed manually and run the main method of this class i.e class Cards.
        
        String cards = "D#K,D#A,D#2";         // Change this String as instructed in above comments
        List rank = new ArrayList();
        List suit = new ArrayList();
        
        //all suits should be only C, H, D, S
        //Rank should be in 2-10 and J,Q,K,A
        
        for(String s : cards.split(",")){
            System.out.println("card "+s);
            String [] card = s.split("#");
         
            if(NumberUtils.isDigits(card[0]))
                rank.add(card[0]);
            else suit.add(card[0]);
            if(NumberUtils.isDigits(card[1]))
                rank.add(card[1]);
            else suit.add(card[1]);
        }
        if(checkSequence(cards))
            System.out.println("In order");
        else
            System.out.println("Not In order");
    }
    
    public enum validSuit{
        C, H, S, D
    }
    public enum validRank{
       J,Q,K,A
    }
    
    public static boolean checkSequence(String cards){
        List<Integer> rank = new ArrayList();
        List<String> suit = new ArrayList();
        
        //converting cards to list of suits and list of ranks
        try{
        convert(cards, rank, suit);
        }catch(NullPointerException ex){
            System.out.println(""+ex.getMessage());
        }
        
        //checking if the cards are from same suit
        for(int i =0 ; i< suit.size();i++){
            
            if((suit.size()-1)!=i){
                if(suit.get(i).equalsIgnoreCase(suit.get(i+1)))
                    continue;
                //if even one card is from different suit then sequence is wrong
            else 
                return false;
            }
        }
        
        //order in ascending
        Collections.sort(rank);
        //check if the numbers are in proper sequense
        for(int i=0 ; i< rank.size(); i++){
            
            //check for cards of rank A, Q and K
            if(rank.get(0)==1 && rank.get(1)==12 && rank.get(2)==13){
                return true;
            }
            
            if(rank.size()-1 !=i){
                if(rank.get(i+1)-rank.get(i)==1)
                    continue;
                else
                    return false;
                
            }
        }
        return true;
    }
    
    public static void convert(String cardsInString, List<Integer> rank, List<String> suit) throws NullPointerException{
        for(String s : cardsInString.split(",")){
            String [] cards = s.split("#");
            
            //adding value found at first place in suit
            //values can be either H, S, D, C
            if(cards[0].equalsIgnoreCase(validSuit.H.toString()) || cards[0].equalsIgnoreCase(validSuit.S.toString()) ||
                    cards[0].equalsIgnoreCase(validSuit.C.toString()) || cards[0].equalsIgnoreCase(validSuit.D.toString()))
                suit.add(cards[0]);
            else {
                throw new NullPointerException("Invalid Card Number "+s);
            }
            
            if(NumberUtils.isDigits(cards[1])){
                if(Integer.parseInt(cards[1])<1 || Integer.parseInt(cards[1])>10){
                    throw new NullPointerException("Invalid Card Number "+s);
                }
                else{
                    rank.add(Integer.parseInt(cards[1]));
                    
                }
            }else{
                if(cards[1].equalsIgnoreCase(validRank.J.toString()))
                    rank.add(11);
                else if(cards[1].equalsIgnoreCase(validRank.Q.toString()))
                    rank.add(12);
                else if(cards[1].equalsIgnoreCase(validRank.K.toString()))
                    rank.add(13);
                else if(cards[1].equalsIgnoreCase(validRank.A.toString()))
                    rank.add(1);
                else{
                    throw new NullPointerException("Invalid Card Number "+s);
                }
            }
            
        }
    }
}
