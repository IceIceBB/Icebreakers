package com.example.lmont.iceicebb.Fragments;


import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lmont.iceicebb.Game;
import com.example.lmont.iceicebb.GameListRecyclerAdapter;
import com.example.lmont.iceicebb.IcebreakerDBHelper;
import com.example.lmont.iceicebb.R;

import static android.content.Context.ACCOUNT_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class GamesFragment extends Fragment {

    public static final String ACCOUNT_TYPE = "example.com";
    public static final String ACCOUNT = "default_account";
    public static final String AUTHORITY = "com.example.lmont.iceicebb.IcebreakerContentProvider";

    IcebreakerDBHelper dbHelper = IcebreakerDBHelper.getInstance(getContext());
    Game[] gameArray;
    Account mAccount;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_games, container, false);

        setup(view);
        setupContentResolver();

    return view;
    }
    public void setup(View view) {
        gameArray = dbHelper.getAllGames();
        RecyclerView gameList = (RecyclerView)view.findViewById(R.id.gameList);
        gameList.setHasFixedSize(true);

        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            gameList.setLayoutManager(new GridLayoutManager(getContext(), 2));
        }
        else{
            gameList.setLayoutManager(new GridLayoutManager(getContext(), 4));
        }

        GameListRecyclerAdapter adapter = new GameListRecyclerAdapter(gameArray);
        gameList.setAdapter(adapter);
    }



    public static Account createSyncAccount(Context context) {
        // Create the account type and default account
        Account newAccount = new Account(
                ACCOUNT, ACCOUNT_TYPE);
        // Get an instance of the Android account manager
        AccountManager accountManager =
                (AccountManager) context.getSystemService(
                        ACCOUNT_SERVICE);
        /*
         * Add the account and account type, no password or user data
         * If successful, return the Account object, otherwise report an error.
         */
        if (accountManager.addAccountExplicitly(newAccount, null, null)) {
            /*
             * If you don't set android:syncable="true" in
             * in your <provider> element in the manifest,
             * then call context.setIsSyncable(account, AUTHORITY, 1)
             * here.
             */
        } else {
            /*
             * The account exists or some other error occurred. Log this, report it,
             * or handle it internally.
             */
        }
        return newAccount;
    }

    public void setupContentResolver()  {
        mAccount = createSyncAccount(getContext());

        Bundle settingsBundle = new Bundle();
        settingsBundle.putBoolean(
                ContentResolver.SYNC_EXTRAS_MANUAL, true);
        settingsBundle.putBoolean(
                ContentResolver.SYNC_EXTRAS_EXPEDITED, true);
        /*
         * Request the sync for the default account, authority, and
         * manual sync settings
         */
        ContentResolver.requestSync(mAccount, AUTHORITY, settingsBundle);

        ContentResolver.setSyncAutomatically(mAccount,AUTHORITY,true);
        ContentResolver.addPeriodicSync(
                mAccount,
                AUTHORITY,
                Bundle.EMPTY,
                30);

    }

}
