package com.example.lmont.iceicebb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

//The activity where Cards are randomly picked from a simulated deck
public class CardsActivity extends AppCompatActivity {
    //TODO: make values left Array Lists into int[]s
    Random random = new Random();
    int[] newCard;

    ArrayList<ArrayList<Integer>> playingCards = new ArrayList<>();
    ArrayList<Integer> spadesLeft = new ArrayList<>();
    ArrayList<Integer> heartsLeft = new ArrayList<>();
    ArrayList<Integer> diamondsLeft = new ArrayList<>();
    ArrayList<Integer> clubsLeft = new ArrayList<>();

    Button nextCard;
    Button reshuffleButton;

    TextView topLeftValue;
    ImageView topLeftSuit;

    TextView bottomRightValue;
    ImageView bottomRightSuit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);

        //Make deck clickable to draw new card.
        nextCard = (Button) findViewById(R.id.deckBack);
        reshuffleButton = (Button) findViewById(R.id.reshuffleButton);

        topLeftValue = (TextView) findViewById(R.id.topLeftValue);
        topLeftSuit = (ImageView) findViewById(R.id.topLeftSuit);
        bottomRightValue = (TextView) findViewById(R.id.bottomRightValue);
        bottomRightSuit = (ImageView) findViewById(R.id.bottomRightSuit);


        nextCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newCard = drawCard();
                topLeftValue.setText(String.valueOf(newCard[0]));
                bottomRightValue.setText(String.valueOf(newCard[0]));

                //TODO: set suit image.
                setSuitView(newCard);
                //TODO: set card image.
            }
        });

        reshuffleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shuffleDeck();
            }
        });


        shuffleDeck();

    }

    //Shuffle deck to replace all cards.
    private void shuffleDeck() {
        //Set each suit to 13 cards, with 1 at each index to represent 1 of each card. (Set index to 0 to represent drawn card)
        for (int i = 0; i < 13; i++) {
            if (spadesLeft.size() < 13) {
                spadesLeft.add(i, 1);
            } else {
                spadesLeft.set(i, 1);
            }
            if (heartsLeft.size() < 13) {
                heartsLeft.add(i, 1);
            } else {
                heartsLeft.set(i, 1);
            }
            if (diamondsLeft.size() < 13) {
                diamondsLeft.add(i, 1);
            } else {
                diamondsLeft.set(i, 1);
            }
            if (clubsLeft.size() < 13) {
                clubsLeft.add(i, 1);
            } else {
                clubsLeft.set(i, 1);
            }
        }
        if (playingCards.size()<4){
            playingCards.add(0, spadesLeft);
        } else{
            playingCards.set(0, spadesLeft);
        }
        if (playingCards.size()<4){
            playingCards.add(1, heartsLeft);
        } else{
            playingCards.set(1, heartsLeft);
        }
        if (playingCards.size()<4){
            playingCards.add(2, diamondsLeft);
        } else{
            playingCards.set(2, diamondsLeft);
        }
        if (playingCards.size()<4){
            playingCards.add(3, clubsLeft);
        } else{
            playingCards.set(3, clubsLeft);
        }
    }

    // A method to pick a card and receive both value and suit in a single Integer[]. [0] = Value 1-13, [1] = Suit 1-4
    //Values: 1=A, 2=2, 3=3 . . . 10=10, 11=J, 12=Q, 13=K
    //Suits: 1=Spade, 2=Heart, 3=Diamond, 4=Club
    private int[] drawCard() {
        boolean cardInDeck = false;
        int[] cardResult = new int[2];

        //While loop to check if random card is in deck before sending it out
        while (cardInDeck == false) {
            //Generate random value and suit
            int value = random.nextInt(13) + 1;
            int suit = random.nextInt(4) + 1;
            ArrayList<Integer> activeSuit = playingCards.get(suit - 1);
            //If card value index ==1 (not 0), the card is ok to use.
            if (activeSuit.get(value - 1) == 1) {
                cardResult[0] = value;
                cardResult[1] = suit;
                cardInDeck = true;
                //Remove card from deck
                removeFromDeck(cardResult);
            }
        }

        return cardResult;
    }

    //Access Playing Cards Array List at index = suit value, then set 1 at index = val to be 0, representing no more cards of that kind in deck.
    private void removeFromDeck(int[] drawResult) {
        int suit = (drawResult[1] - 1);
        int val = (drawResult[0] - 1);
        ArrayList<Integer> suitLeft = playingCards.get(suit);

        //Remove card value, replace suit
        suitLeft.add(val, 0);
        playingCards.add(suit, suitLeft);
    }

    private void setSuitView (int[] newCard){
        int suit = newCard[1];
        if(suit == 1){
            topLeftSuit.setBackgroundResource(R.drawable.spade_suit);
            bottomRightSuit.setBackgroundResource((R.drawable.spade_suit));
        }
        if(suit == 2){
            topLeftSuit.setBackgroundResource(R.drawable.heart_suit);
            bottomRightSuit.setBackgroundResource((R.drawable.heart_suit));
        }
        if(suit == 3){
            topLeftSuit.setBackgroundResource(R.drawable.diamond_suit);
            bottomRightSuit.setBackgroundResource((R.drawable.diamond_suit));
        }
        if(suit == 4){
            topLeftSuit.setBackgroundResource(R.drawable.club_suit);
            bottomRightSuit.setBackgroundResource((R.drawable.club_suit));
        }
    }
}
