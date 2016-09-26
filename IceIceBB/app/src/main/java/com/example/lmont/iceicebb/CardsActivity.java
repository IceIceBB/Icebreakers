package com.example.lmont.iceicebb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

//The activity where Cards are randomly picked from a simulated deck
public class CardsActivity extends AppCompatActivity {

    Random random = new Random();
    ArrayList<ArrayList<Integer>> playingCards = new ArrayList<>();
    ArrayList<Integer> spadesLeft = new ArrayList<>();
    ArrayList<Integer> heartsLeft = new ArrayList<>();
    ArrayList<Integer> diamondsLeft = new ArrayList<>();
    ArrayList<Integer> clubsLeft = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);

        if (playingCards == null) {
            shuffleDeck();
        }
    }

    //Shuffle deck to replace all cards.
    private void shuffleDeck() {
        //Set each suit to 13 cards, with 1 at each index to represent 1 of each card. (Set index to 0 to represent drawn card)
        for (int i = 0; i < 13; i++) {
            spadesLeft.add(1);
            heartsLeft.add(1);
            diamondsLeft.add(1);
            clubsLeft.add(1);
        }
        playingCards.add(0, spadesLeft);
        playingCards.add(1, heartsLeft);
        playingCards.add(2, diamondsLeft);
        playingCards.add(3, clubsLeft);
    }

    // A method to pick a card and receive both value and suit in a single Integer[]. [0] = Value 1-13, [1] = Suit 1-4
    //Values: 1=A, 2=2, 3=3 . . . 10=10, 11=J, 12=Q, 13=K
    //Suits: 1=Spade, 2=Heart, 3=Diamond, 4=Club
    private int[] drawCard() {
        int[] cardResult = new int[2];

        //Generate random value and suit
        int value = random.nextInt(13) + 1;
        int suit = random.nextInt(4) + 1;
        cardResult[0] = value;
        cardResult[1] = suit;

        //Remove card from deck
        removeFromDeck(cardResult);

        return cardResult;
    }
    //Access Playing Cards Array List at index = suit value, then set 1 at index = val to be 0, representing no more cards of that kind in deck.
    private void removeFromDeck(int[] drawResult){
        int suit = (drawResult[1]-1);
        int val = (drawResult[0]-1);
        ArrayList<Integer> suitLeft = playingCards.get(suit);
        
        suitLeft.add(val, 0);
    }
}
