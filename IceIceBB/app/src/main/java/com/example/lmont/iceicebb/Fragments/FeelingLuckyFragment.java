package com.example.lmont.iceicebb.Fragments;


import android.content.Intent;
import android.media.Image;
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
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeelingLuckyFragment extends Fragment {

    Button feelingLucky;
    ImageButton devilButon, angelButton;
    Game.Question question;
    TextView questionView;

    private TextView mTextDetails;

    private CallbackManager mCallbackManger;
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
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        mCallbackManger = CallbackManager.Factory.create();
    }

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

        final Animation bounce = AnimationUtils.loadAnimation(getContext(), R.anim.bounce);


        IcebreakerDBHelper dbHelper = IcebreakerDBHelper.getInstance(getContext());
        question = dbHelper.getRandomQuestion(1);

        questionView = (TextView) view.findViewById(R.id.questionView);
        questionView.setText(question.text);
        questionView.setAnimation(bounce);


        angelButton = (ImageButton) view.findViewById(R.id.angel_btn);
        devilButon = (ImageButton) view.findViewById(R.id.devil_btn);
        feelingLucky = (Button) view.findViewById(R.id.feeling_lucky_btn);
        feelingLucky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                IcebreakerDBHelper dbHelper = IcebreakerDBHelper.getInstance(getContext());
                question = dbHelper.getRandomQuestion(-1);
                questionView.setText(question.text);
                questionView.startAnimation(bounce);
            }
        });
        devilButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                IcebreakerDBHelper dbHelper = IcebreakerDBHelper.getInstance(getContext());
                question = dbHelper.getRandomQuestion(0);
                questionView.setText(question.text);
                questionView.startAnimation(bounce);
            }
        });
        angelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                IcebreakerDBHelper dbHelper = IcebreakerDBHelper.getInstance(getContext());
                question = dbHelper.getRandomQuestion(1);
                questionView.setText(question.text);
                questionView.startAnimation(bounce);
            }
        });
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LoginButton loginButton = (LoginButton) view.findViewById(R.id.login_button);

        loginButton.setReadPermissions("email");

        loginButton.setFragment(this);

        loginButton.registerCallback(mCallbackManger, mCallback);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManger.onActivityResult(requestCode, resultCode, data);
    }

}



