/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Cards.CardDeck;

/**
 *
 * @author Gurpartap Gill
 */
public class ChanceSpace extends Space{
    String cardType;
    CardDeck deck;
    
      
    public ChanceSpace(){
        super();
    }
    public ChanceSpace(int location, String cardType, CardDeck deck){
        super(location);
        this.cardType = cardType;
        this.deck = deck;
    }
    public void intialise(){
        //deck.initialize(cardDeck, name);
    }
    public void playCard(){
        
    }
    public String getCardType(){
        return cardType;
        
    }
    public void setName(String cardType){
        this.cardType = cardType;
    }
    public void setDeck(CardDeck deck){
        this.deck = deck;
    }
    public CardDeck getDeck(){
        return deck;
    }
}
