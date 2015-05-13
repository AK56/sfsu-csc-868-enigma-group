/*
 * This class holds the card decks and will be basis for the Community Chest and Chance card decks.
 * The class draws from the top of deck and recycles the deck when all the cards have been traversed.
 * The class also contains a shuffle method to randomize the deck at the begining of the game.
 */
package Cards;


import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Kenneth Robertson
 */
public class CardDeck 
{
    private ArrayList<Card> deck;
    private Random randomGenerator;
    private int index;
    private String cardStackType;

    public CardDeck() 
    {
        index = 0;
        randomGenerator = new Random();
    }

    public CardDeck(ArrayList<Card> deck, String cardStackType) 
    {
        this.deck = deck;
        this.index = 0;
        this.randomGenerator = new Random(); 
        this.cardStackType = cardStackType;
    }
    
    public void initialize(ArrayList<Card> deck, String cardStackType) 
    {
        this.setIndex(0);
        this.setRandomGenerator(new Random());
        this.setDeck(deck);
        this.setCardStackType(cardStackType);
    }
    
    public Card getCard()
    {
        //Get next card
        Card nextCard = deck.get(index);
        
        //If the card has been taken (because its get out of jail card) keep looking
        while(nextCard == null)
        {
            index++;
            
            //Make sure the index doesn't go over the deck size
            if(index > deck.size()-1)
                index = 0;
            
            nextCard = deck.get(index);
        }
        
        //If the card is a get out of jail card remove it from the deck
        if(nextCard.getClass() == GetOutOfJailFreeCard.class)
            deck.set(index, null);
        
        index++;
        
        //If the end of the deck is reached start from the begining again
        if(index > deck.size()-1)
            index = 0;
        
        return nextCard;
    } 
    
    public void shuffle()
    {      
        for(int i = deck.size(); i > 1; i--)
        {
            int randomValue = randomGenerator.nextInt(deck.size());
            Card temp = deck.get(i-1);
            
            deck.set(i-1, deck.get(randomValue));
            deck.set(randomValue, temp);  
        } 
    }
    
    public boolean putBackGetOutOfJailCard(GetOutOfJailFreeCard outOfJailCard)
    {
        for(int i = 0; i < deck.size(); i++)
        {
            if(deck.get(i) == null)
            {
                deck.set(i, outOfJailCard);
                
                return true;
            }
        }
        
        return false;
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public void setDeck(ArrayList<Card> deck) {
        this.deck = deck;
    }

    public Random getRandomGenerator() {
        return randomGenerator;
    }

    public void setRandomGenerator(Random randomGenerator) {
        this.randomGenerator = randomGenerator;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getCardStackType() {
        return cardStackType;
    }

    public void setCardStackType(String cardStackType) {
        this.cardStackType = cardStackType;
    }
    
    
}
