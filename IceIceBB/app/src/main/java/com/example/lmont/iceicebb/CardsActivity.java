package com.example.lmont.iceicebb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

//The activity where Cards are randomly picked from a simulated deck
public class CardsActivity extends AppCompatActivity {
    //TODO: make values left Array Lists into int[]s
    Random random = new Random();
    int[] newCard;
    int discardSize;

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

    ImageView cardFaceImage;

    ImageView aSuitIcon;
    ImageView bSuitIconOne;
    ImageView bSuitIconTwo;
    RelativeLayout cIconsOne;
    ImageView cSuitIconOne;
    ImageView cSuitIconTwo;
    RelativeLayout cIconsTwo;
    ImageView cSuitIconThree;
    ImageView cSuitIconFour;
    RelativeLayout dIcons;
    ImageView dSuitIconOne;
    ImageView dSuitIconTwo;
    RelativeLayout eIcons;
    ImageView eSuitIconOne;
    ImageView eSuitIconTwo;

    //Animate card flipping to reveal new card.
//    Animation flipAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);
        discardSize = 0;

        //Make deck clickable to draw new card.
        nextCard = (Button) findViewById(R.id.deckBack);
        reshuffleButton = (Button) findViewById(R.id.reshuffleButton);

        topLeftValue = (TextView) findViewById(R.id.topLeftValue);
        topLeftSuit = (ImageView) findViewById(R.id.topLeftSuit);
        bottomRightValue = (TextView) findViewById(R.id.bottomRightValue);
        bottomRightSuit = (ImageView) findViewById(R.id.bottomRightSuit);

        cardFaceImage = (ImageView) findViewById(R.id.cardFaceImage);

        //Find all 10 suits icons
        aSuitIcon = (ImageView) findViewById(R.id.aSuitIcon);
        bSuitIconOne = (ImageView) findViewById(R.id.bSuitIconOne);
        bSuitIconTwo = (ImageView) findViewById(R.id.bSuitIconTwo);
        cIconsOne = (RelativeLayout) findViewById(R.id.cIconsOne);
        cSuitIconOne = (ImageView) findViewById(R.id.cSuitIconOne);
        cSuitIconTwo = (ImageView) findViewById(R.id.cSuitIconTwo);
        cIconsTwo = (RelativeLayout) findViewById(R.id.cIconsTwo);
        cSuitIconThree = (ImageView) findViewById(R.id.cSuitIconThree);
        cSuitIconFour = (ImageView) findViewById(R.id.cSuitIconFour);
        dIcons = (RelativeLayout) findViewById(R.id.dIcons);
        dSuitIconOne = (ImageView) findViewById(R.id.dSuitIconOne);
        dSuitIconTwo = (ImageView) findViewById(R.id.dSuitIconTwo);
        eIcons = (RelativeLayout) findViewById(R.id.eIcons);
        eSuitIconOne = (ImageView) findViewById(R.id.eSuitIconOne);
        eSuitIconTwo = (ImageView) findViewById(R.id.eSuitIconTwo);

//        flipAnimation = AnimationUtils.loadAnimation(CardsActivity.this, R.anim.card_flip);


        nextCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                nextCard.startAnimation(flipAnimation);

                setNewCard();
            }
        });

        reshuffleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shuffleDeck();
            }
        });


        shuffleDeck();
        setNewCard();

    }

    private void setNewCard() {
        newCard = drawCard();
        setCardValue(newCard);
        setSuitView(newCard);
        cardIconPopulater(newCard);
        discardSize++;
        if (discardSize >= 52){
            shuffleDeck();
            discardSize = 0;
        }
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
        if (playingCards.size() < 4) {
            playingCards.add(0, spadesLeft);
        } else {
            playingCards.set(0, spadesLeft);
        }
        if (playingCards.size() < 4) {
            playingCards.add(1, heartsLeft);
        } else {
            playingCards.set(1, heartsLeft);
        }
        if (playingCards.size() < 4) {
            playingCards.add(2, diamondsLeft);
        } else {
            playingCards.set(2, diamondsLeft);
        }
        if (playingCards.size() < 4) {
            playingCards.add(3, clubsLeft);
        } else {
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
        Toast.makeText(this, "Deck Shuffled", Toast.LENGTH_SHORT).show();
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

    private void setCardValue(int[] newCard) {
        if (newCard[0] < 11) {
            topLeftValue.setText(String.valueOf(newCard[0]));
            bottomRightValue.setText(String.valueOf(newCard[0]));
        } else {
            if (newCard[0] == 11) {
                topLeftValue.setText("J");
                bottomRightValue.setText("J");
            }
            if (newCard[0] == 12) {
                topLeftValue.setText("Q");
                bottomRightValue.setText("Q");
            }
            if (newCard[0] == 13) {
                topLeftValue.setText("K");
                bottomRightValue.setText("K");
            }
        }
    }

    private void setSuitView(int[] newCard) {
        int suit = newCard[1];
        if (suit == 1) {
            topLeftSuit.setBackgroundResource(R.drawable.spade_suit);
            bottomRightSuit.setBackgroundResource((R.drawable.spade_suit));

            //Set all suit icons to spade
            aSuitIcon.setBackgroundResource((R.drawable.spade_suit));
            bSuitIconOne.setBackgroundResource((R.drawable.spade_suit));
            bSuitIconTwo.setBackgroundResource((R.drawable.spade_suit));
            cSuitIconOne.setBackgroundResource((R.drawable.spade_suit));
            cSuitIconTwo.setBackgroundResource((R.drawable.spade_suit));
            cSuitIconThree.setBackgroundResource((R.drawable.spade_suit));
            cSuitIconFour.setBackgroundResource((R.drawable.spade_suit));
            dSuitIconOne.setBackgroundResource((R.drawable.spade_suit));
            dSuitIconTwo.setBackgroundResource((R.drawable.spade_suit));
            eSuitIconOne.setBackgroundResource((R.drawable.spade_suit));
            eSuitIconTwo.setBackgroundResource((R.drawable.spade_suit));

        }
        if (suit == 2) {
            topLeftSuit.setBackgroundResource(R.drawable.heart_suit);
            bottomRightSuit.setBackgroundResource((R.drawable.heart_suit));

            //Set all suit icons to heart
            aSuitIcon.setBackgroundResource((R.drawable.heart_suit));
            bSuitIconOne.setBackgroundResource((R.drawable.heart_suit));
            bSuitIconTwo.setBackgroundResource((R.drawable.heart_suit));
            cSuitIconOne.setBackgroundResource((R.drawable.heart_suit));
            cSuitIconTwo.setBackgroundResource((R.drawable.heart_suit));
            cSuitIconThree.setBackgroundResource((R.drawable.heart_suit));
            cSuitIconFour.setBackgroundResource((R.drawable.heart_suit));
            dSuitIconOne.setBackgroundResource((R.drawable.heart_suit));
            dSuitIconTwo.setBackgroundResource((R.drawable.heart_suit));
            eSuitIconOne.setBackgroundResource((R.drawable.heart_suit));
            eSuitIconTwo.setBackgroundResource((R.drawable.heart_suit));
        }
        if (suit == 3) {
            topLeftSuit.setBackgroundResource(R.drawable.diamond_suit);
            bottomRightSuit.setBackgroundResource((R.drawable.diamond_suit));

            //Set all suit icons to diamond
            aSuitIcon.setBackgroundResource((R.drawable.diamond_suit));
            bSuitIconOne.setBackgroundResource((R.drawable.diamond_suit));
            bSuitIconTwo.setBackgroundResource((R.drawable.diamond_suit));
            cSuitIconOne.setBackgroundResource((R.drawable.diamond_suit));
            cSuitIconTwo.setBackgroundResource((R.drawable.diamond_suit));
            cSuitIconThree.setBackgroundResource((R.drawable.diamond_suit));
            cSuitIconFour.setBackgroundResource((R.drawable.diamond_suit));
            dSuitIconOne.setBackgroundResource((R.drawable.diamond_suit));
            dSuitIconTwo.setBackgroundResource((R.drawable.diamond_suit));
            eSuitIconOne.setBackgroundResource((R.drawable.diamond_suit));
            eSuitIconTwo.setBackgroundResource((R.drawable.diamond_suit));
        }
        if (suit == 4) {
            topLeftSuit.setBackgroundResource(R.drawable.club_suit);
            bottomRightSuit.setBackgroundResource((R.drawable.club_suit));

            //Set all suit icons to club
            aSuitIcon.setBackgroundResource((R.drawable.club_suit));
            bSuitIconOne.setBackgroundResource((R.drawable.club_suit));
            bSuitIconTwo.setBackgroundResource((R.drawable.club_suit));
            cSuitIconOne.setBackgroundResource((R.drawable.club_suit));
            cSuitIconTwo.setBackgroundResource((R.drawable.club_suit));
            cSuitIconThree.setBackgroundResource((R.drawable.club_suit));
            cSuitIconFour.setBackgroundResource((R.drawable.club_suit));
            dSuitIconOne.setBackgroundResource((R.drawable.club_suit));
            dSuitIconTwo.setBackgroundResource((R.drawable.club_suit));
            eSuitIconOne.setBackgroundResource((R.drawable.club_suit));
            eSuitIconTwo.setBackgroundResource((R.drawable.club_suit));
        }
    }

    private void cardIconPopulater(int[] newCard) {
        if (newCard[0] == 1) {
            aSuitIcon.setVisibility(View.VISIBLE);
            bSuitIconOne.setVisibility(View.INVISIBLE);
            bSuitIconTwo.setVisibility(View.INVISIBLE);
            cIconsOne.setVisibility(View.INVISIBLE);
            cIconsTwo.setVisibility(View.INVISIBLE);
            dIcons.setVisibility(View.INVISIBLE);
            eIcons.setVisibility(View.INVISIBLE);
            cardFaceImage.setVisibility(View.INVISIBLE);
        }

        if (newCard[0] == 2) {
            aSuitIcon.setVisibility(View.INVISIBLE);
            bSuitIconOne.setVisibility(View.VISIBLE);
            bSuitIconTwo.setVisibility(View.VISIBLE);
            cIconsOne.setVisibility(View.INVISIBLE);
            cIconsTwo.setVisibility(View.INVISIBLE);
            dIcons.setVisibility(View.INVISIBLE);
            eIcons.setVisibility(View.INVISIBLE);
            cardFaceImage.setVisibility(View.INVISIBLE);
        }

        if (newCard[0] == 3) {
            aSuitIcon.setVisibility(View.VISIBLE);
            bSuitIconOne.setVisibility(View.VISIBLE);
            bSuitIconTwo.setVisibility(View.VISIBLE);
            cIconsOne.setVisibility(View.INVISIBLE);
            cIconsTwo.setVisibility(View.INVISIBLE);
            dIcons.setVisibility(View.INVISIBLE);
            eIcons.setVisibility(View.INVISIBLE);
            cardFaceImage.setVisibility(View.INVISIBLE);
        }

        if (newCard[0] == 4) {
            aSuitIcon.setVisibility(View.INVISIBLE);
            bSuitIconOne.setVisibility(View.INVISIBLE);
            bSuitIconTwo.setVisibility(View.INVISIBLE);
            cIconsOne.setVisibility(View.VISIBLE);
            cIconsTwo.setVisibility(View.VISIBLE);
            dIcons.setVisibility(View.INVISIBLE);
            eIcons.setVisibility(View.INVISIBLE);
            cardFaceImage.setVisibility(View.INVISIBLE);
        }

        if (newCard[0] == 5) {
            aSuitIcon.setVisibility(View.VISIBLE);
            bSuitIconOne.setVisibility(View.INVISIBLE);
            bSuitIconTwo.setVisibility(View.INVISIBLE);
            cIconsOne.setVisibility(View.VISIBLE);
            cIconsTwo.setVisibility(View.VISIBLE);
            dIcons.setVisibility(View.INVISIBLE);
            eIcons.setVisibility(View.INVISIBLE);
            cardFaceImage.setVisibility(View.INVISIBLE);
        }

        if (newCard[0] == 6) {
            aSuitIcon.setVisibility(View.INVISIBLE);
            bSuitIconOne.setVisibility(View.INVISIBLE);
            bSuitIconTwo.setVisibility(View.INVISIBLE);
            cIconsOne.setVisibility(View.VISIBLE);
            cIconsTwo.setVisibility(View.VISIBLE);
            dIcons.setVisibility(View.VISIBLE);
            eIcons.setVisibility(View.INVISIBLE);
            cardFaceImage.setVisibility(View.INVISIBLE);
        }

        if (newCard[0] == 7) {
            aSuitIcon.setVisibility(View.VISIBLE);
            bSuitIconOne.setVisibility(View.INVISIBLE);
            bSuitIconTwo.setVisibility(View.INVISIBLE);
            cIconsOne.setVisibility(View.VISIBLE);
            cIconsTwo.setVisibility(View.VISIBLE);
            dIcons.setVisibility(View.VISIBLE);
            eIcons.setVisibility(View.INVISIBLE);
            cardFaceImage.setVisibility(View.INVISIBLE);
        }

        if (newCard[0] == 8) {
            aSuitIcon.setVisibility(View.INVISIBLE);
            bSuitIconOne.setVisibility(View.VISIBLE);
            bSuitIconTwo.setVisibility(View.VISIBLE);
            cIconsOne.setVisibility(View.VISIBLE);
            cIconsTwo.setVisibility(View.VISIBLE);
            dIcons.setVisibility(View.VISIBLE);
            eIcons.setVisibility(View.INVISIBLE);
            cardFaceImage.setVisibility(View.INVISIBLE);
        }

        if (newCard[0] == 9) {
            aSuitIcon.setVisibility(View.VISIBLE);
            bSuitIconOne.setVisibility(View.INVISIBLE);
            bSuitIconTwo.setVisibility(View.INVISIBLE);
            cIconsOne.setVisibility(View.VISIBLE);
            cIconsTwo.setVisibility(View.VISIBLE);
            dIcons.setVisibility(View.VISIBLE);
            eIcons.setVisibility(View.VISIBLE);
            cardFaceImage.setVisibility(View.INVISIBLE);
        }

        if (newCard[0] == 10) {
            aSuitIcon.setVisibility(View.INVISIBLE);
            bSuitIconOne.setVisibility(View.VISIBLE);
            bSuitIconTwo.setVisibility(View.VISIBLE);
            cIconsOne.setVisibility(View.VISIBLE);
            cIconsTwo.setVisibility(View.VISIBLE);
            dIcons.setVisibility(View.VISIBLE);
            eIcons.setVisibility(View.VISIBLE);
            cardFaceImage.setVisibility(View.INVISIBLE);
        }

        if (newCard[0] == 11) {
            aSuitIcon.setVisibility(View.INVISIBLE);
            bSuitIconOne.setVisibility(View.INVISIBLE);
            bSuitIconTwo.setVisibility(View.INVISIBLE);
            cIconsOne.setVisibility(View.INVISIBLE);
            cIconsTwo.setVisibility(View.INVISIBLE);
            dIcons.setVisibility(View.INVISIBLE);
            eIcons.setVisibility(View.INVISIBLE);
            cardFaceImage.setVisibility(View.VISIBLE);
            cardFaceImage.setBackgroundResource(R.drawable.jack_face);
        }

        if (newCard[0] == 12) {
            aSuitIcon.setVisibility(View.INVISIBLE);
            bSuitIconOne.setVisibility(View.INVISIBLE);
            bSuitIconTwo.setVisibility(View.INVISIBLE);
            cIconsOne.setVisibility(View.INVISIBLE);
            cIconsTwo.setVisibility(View.INVISIBLE);
            dIcons.setVisibility(View.INVISIBLE);
            eIcons.setVisibility(View.INVISIBLE);
            cardFaceImage.setVisibility(View.VISIBLE);
            cardFaceImage.setBackgroundResource(R.drawable.queen_face);
        }
        if (newCard[0] == 13) {
            aSuitIcon.setVisibility(View.INVISIBLE);
            bSuitIconOne.setVisibility(View.INVISIBLE);
            bSuitIconTwo.setVisibility(View.INVISIBLE);
            cIconsOne.setVisibility(View.INVISIBLE);
            cIconsTwo.setVisibility(View.INVISIBLE);
            dIcons.setVisibility(View.INVISIBLE);
            eIcons.setVisibility(View.INVISIBLE);
            cardFaceImage.setVisibility(View.VISIBLE);
            cardFaceImage.setBackgroundResource(R.drawable.king_face);
        }
    }
}

