package com.example.lmont.iceicebb.Fragments;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.lmont.iceicebb.Game;
import com.example.lmont.iceicebb.IcebreakerDBHelper;
import com.example.lmont.iceicebb.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeelingLuckyFragment extends Fragment {

    Button feelingLucky;
    ImageButton devilButton, angelButton;
    Game.Question question;
    TextView questionView;
    static String currentQuestionText;

    private TextView mTextDetails;

    private CallbackManager mCallbackManger;

    //Calling Back the FaceBook Login Result
    private FacebookCallback<LoginResult> mCallback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            AccessToken accessToken = loginResult.getAccessToken();
            Profile profile = Profile.getCurrentProfile();
            if (profile != null) {
                mTextDetails.setText("Welcome " + profile.getName());
            }
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException e) {

        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
//        mCallbackManger = CallbackManager.Factory.create();
    }

    //onCreateView will run our Setup Method that contains our Animations
    //as well as setting the new question when a button is clicked
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feeling_lucky, container, false);

        setup(view);
        return view;

    }

    //TODO try to make the switch for

    public void setup(View view) {

        //declaring the bounce animation to the TextView(questionView)
        final Animation bounce = AnimationUtils.loadAnimation(getContext(), R.anim.bounce);

        //Calling DatabaseHelper method getRandomQuestion to generate a question for our questions api
        IcebreakerDBHelper dbHelper = IcebreakerDBHelper.getInstance(getContext());
        question = dbHelper.getRandomQuestion(1);

        questionView = (TextView) view.findViewById(R.id.questionView);
        questionView.setText(question.text);
        questionView.setAnimation(bounce);


        angelButton = (ImageButton) view.findViewById(R.id.angel_btn);
        devilButton = (ImageButton) view.findViewById(R.id.devil_btn);
        feelingLucky = (Button) view.findViewById(R.id.feeling_lucky_btn);

        //Setting OnClickListeners for the FeelingLucky/Devil/Angel buttons
        feelingLucky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                IcebreakerDBHelper dbHelper = IcebreakerDBHelper.getInstance(getContext());
                question = dbHelper.getRandomQuestion(-1);
                questionView.setText(question.text);
                questionView.startAnimation(bounce);
                currentQuestionText = question.text;
            }
        });
        devilButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                IcebreakerDBHelper dbHelper = IcebreakerDBHelper.getInstance(getContext());
                question = dbHelper.getRandomQuestion(0);
                questionView.setText(question.text);
                questionView.startAnimation(bounce);
                currentQuestionText = question.text;
            }
        });
        angelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                IcebreakerDBHelper dbHelper = IcebreakerDBHelper.getInstance(getContext());
                question = dbHelper.getRandomQuestion(1);
                questionView.setText(question.text);
                questionView.startAnimation(bounce);
                currentQuestionText = question.text;
            }
        });


        // Sharing the question of the day to FaceBook with the FaceBook Share Button

        ShareButton shareButton = (ShareButton) view.findViewById(R.id.feeling_lucky_share_button);
        ShareLinkContent linkContent = new ShareLinkContent.Builder()
                .setContentUrl(Uri.parse("https://github.com/IceIceBB/Icebreakers"))
                .setContentTitle("Question of the Day")
                .setContentDescription(currentQuestionText)
                .build();

        shareButton.setShareContent(linkContent);
    }
// Essential for the FaceBook Login but is incorpated in the share
//    @Override
//    public void onViewCreated(View view, Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//        LoginButton loginButton = (LoginButton) view.findViewById(R.id.login_button);
//
//        loginButton.setReadPermissions("email");
//
//        loginButton.setFragment(this);
//
//        loginButton.registerCallback(mCallbackManger, mCallback);
//    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        mCallbackManger.onActivityResult(requestCode, resultCode, data);
//    }

}



