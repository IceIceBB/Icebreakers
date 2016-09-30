package com.example.lmont.iceicebb;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.SearchManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.SearchView;
import android.transition.ChangeTransform;
import android.transition.TransitionSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.lmont.iceicebb.Fragments.FeelingLuckyFragment;
import com.example.lmont.iceicebb.Fragments.GamesFragment;
import com.example.lmont.iceicebb.Fragments.ToolsFragment;

public class TabMainActivity extends AppCompatActivity {

/*
Your work must:
[X]   Include at least 2 prototypes
[X]   Include at least three types of cards:
[X]    One card should present data that is stored and updated on the user’s phone using a Content Provider or Shared Preferences (e.g. To-do card, Reminder card, Alarm card).
[X]    Two cards should present data provided through a web API (e.g. Weather card, Twitter card, Maps card, Photo of the Day). Check out this directory of APIs if you need some API ideas.
[X]   Use the Retrofit library to handle HTTP requests and parse JSON data
[X]   Include at least one Notifications feature (e.g. reminder, alarm)
[V]   Look great in both landscape and portrait modes and reflect Material Design principles
[X]   Not crash or hang and should handle for when networking/internet is slow or unavailable
[V]   Have code that is semantically clean and well-organized
[N]   Integrate with the Twitter and/or Facebook's APIs
[N]   Allow the user to share breaking news developments with friends

Bonus:
[N]   Integrate additional APIs
[]   Add multiple types of dice for dice roller
[]   Add ability to draw Jokers from deck
*/



    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    public static final String ACCOUNT_TYPE = "example.com";
    public static final String ACCOUNT = "default_account";
    public static final String AUTHORITY = "com.example.lmont.iceicebb.IcebreakerContentProvider";
    public static int gamesTableSize;
    public static boolean first = true;
    public static Context context;
    Account mAccount;

    // Search parameters
    public static String query = "";
    public static String tagsQuery = "";
    public static boolean isCleanQuery = false;
    public static boolean isByRatingQuery = false;
    public static boolean isByAlphabetQuery = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP){
            TransitionSet transition = new TransitionSet();
            transition.addTransition(new ChangeTransform());
            getWindow().setSharedElementEnterTransition(transition);
            getWindow().setSharedElementReturnTransition(transition);
        }

        setContentView(R.layout.activity_tab_main);

        handleIntent(getIntent());
        setup();
        setupContentResolver();
    }

    protected void setup() {
        context = this;
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        mViewPager.setCurrentItem(1);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            query = intent.getStringExtra(SearchManager.QUERY);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.reset:
                query = "";
                tagsQuery = "";
                isCleanQuery = false;
                isByRatingQuery = false;
                isByAlphabetQuery = false;

                Intent i = new Intent(TabMainActivity.this, TabMainActivity.class);
                startActivity(i);
                break;
            case R.id.advancedsearch:
                advancedSearch();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {


            if (getArguments().getInt(ARG_SECTION_NUMBER) == 1) {

                View rootView = inflater.inflate(R.layout.fragment_feeling_lucky, container, false);
                return rootView;

            } else if (getArguments().getInt(ARG_SECTION_NUMBER) == 2) {

                View rootView = inflater.inflate(R.layout.fragment_games, container, false);
                return rootView;
            } else {
                View rootView = inflater.inflate(R.layout.fragment_tools, container, false);
                return rootView;
            }
        }

    }
    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position) {
                case 0:
                    FeelingLuckyFragment tab1 = new FeelingLuckyFragment();

                    return tab1;
                case 1:
                    GamesFragment tab2 = new GamesFragment();

                    return tab2;
                case 2:
                    ToolsFragment tab3 = new ToolsFragment();

                    return tab3;
                default:
                    return null;
            }

        }

        @Override
        public int getCount() {

            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "QUESTIONS";
                case 1:
                    return "GAMES";
                case 2:
                    return "TOOLS";
            }
            return null;
        }

    }

    public void advancedSearch() {
        LayoutInflater inflater = this.getLayoutInflater();
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final View dialogView = inflater.inflate(R.layout.advanced_search, null);
        builder.setView(dialogView);

        final EditText nameEditText = (EditText) dialogView.findViewById(R.id.advanced_search_name_edittext);
        final EditText tagsEditText = (EditText) dialogView.findViewById(R.id.advanced_search_tag_edittext);
        final CheckBox cleanCheckBox = (CheckBox) dialogView.findViewById(R.id.advanced_search_isclean_checkbox);
        final RadioGroup rateByRadioGroup = (RadioGroup) dialogView.findViewById(R.id.andvanced_search_sortby_radiogroup);
        RadioButton ratingRadioButton = (RadioButton) dialogView.findViewById(R.id.advanced_search_rating_radio);
        RadioButton alphabetRadioButton = (RadioButton) dialogView.findViewById(R.id.advanced_search_alphabetical_radio);

        builder.setMessage("Add New Card Game")
                .setPositiveButton("Search", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        query = nameEditText.getText().toString();
                        tagsQuery = tagsEditText.getText().toString();
                        isCleanQuery = cleanCheckBox.isChecked();
                        if (rateByRadioGroup.getCheckedRadioButtonId() == R.id.advanced_search_rating_radio) {
                            isByRatingQuery = true;
                            isByAlphabetQuery = false;
                        }
                        if (rateByRadioGroup.getCheckedRadioButtonId() == R.id.advanced_search_alphabetical_radio){
                            isByRatingQuery = false;
                            isByAlphabetQuery = true;
                        }

                        startActivity(new Intent(TabMainActivity.this, TabMainActivity.class));
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Cancel
                    }
                });
        builder.show();
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
        mAccount = createSyncAccount(this);

        getContentResolver().registerContentObserver(IcebreakerContentProvider.CONTENT_URI,true,new NewsContentObserver(new Handler()));

        Bundle settingsBundle = new Bundle();
        settingsBundle.putBoolean(
                ContentResolver.SYNC_EXTRAS_MANUAL, true);
        settingsBundle.putBoolean(
                ContentResolver.SYNC_EXTRAS_EXPEDITED, true);

        ContentResolver.requestSync(mAccount, AUTHORITY, settingsBundle);
        ContentResolver.setSyncAutomatically(mAccount,AUTHORITY,true);
        ContentResolver.addPeriodicSync(
                mAccount,
                AUTHORITY,
                Bundle.EMPTY,
                60);

    }

    public class NewsContentObserver extends ContentObserver {

        /**
         * Creates a content observer.
         *
         * @param handler The handler to run {@link #onChange} on, or null if none.
         */
        public NewsContentObserver(Handler handler) {
            super(handler);
        }

        @Override
        public void onChange(boolean selfChange, Uri uri) {
            int newSize = IcebreakerDBHelper.getInstance(context).getGamesTableSize();

            if (gamesTableSize == newSize) return;
            gamesTableSize = newSize;

            if (first) {
                first = false;
                return;
            }

            //do stuff on UI thread
            Intent intent = new Intent(TabMainActivity.this, TabMainActivity.class);
            PendingIntent pIntent = PendingIntent.getActivity(TabMainActivity.this, 0, intent, 0);
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(TabMainActivity.this);
            mBuilder.setSmallIcon(R.drawable.cards);
            mBuilder.setContentTitle("Games Updated!");
            mBuilder.setContentText("Click to refresh :D");
            mBuilder.setContentIntent(pIntent);
            mBuilder.setPriority(Notification.PRIORITY_MAX);
            NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.notify(1, mBuilder.build());
        }
    }
}
