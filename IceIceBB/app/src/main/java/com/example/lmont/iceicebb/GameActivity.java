package com.example.lmont.iceicebb;

import android.support.v7.app.AppCompatActivity;

//        Your work must:
//
//   [X]   Include at least 2 prototypes
//   []   Include user stories based on your research and feature prioritization in a Trello board for the complete flow of your app
//   []   Have at least one Activity--more if it makes sense. The activity should include a list of interactive cards implemented with Android’s RecyclerView and CardView APIs.
//   []   Include at least three types of cards:
//        []  One card should present data that is stored and updated on the user’s phone using a Content Provider or Shared Preferences (e.g. To-do card, Reminder card, Alarm card).
//        []    Two cards should present data provided through a web API (e.g. Weather card, Twitter card, Maps card, Photo of the Day). Check out this directory of APIs if you need some API ideas.
//   []   Use the Retrofit library to handle HTTP requests and parse JSON data
//   []   Include at least one Notifications feature (e.g. reminder, alarm)
//   []   Look great in both landscape and portrait modes and reflect Material Design principles
//   []   Not crash or hang and should handle for when networking/internet is slow or unavailable
//   []   Have code that is semantically clean and well-organized
//   []   Integrate with the Twitter and/or Facebook's APIs
//   []   Allow the user to share breaking news developments with friends

//        Bonus:
//   []   Integrate additional APIs


public class GameActivity extends AppCompatActivity {

//    public static final String ACCOUNT_TYPE = "example.com";
//    public static final String ACCOUNT = "default_account";
//    public static final String AUTHORITY = "com.example.lmont.iceicebb.IcebreakerContentProvider";
//
//    IcebreakerDBHelper dbHelper = IcebreakerDBHelper.getInstance(this);
//    Game[] gameArray;
//    Account mAccount;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_game);
//
//
//        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
//        tabLayout.addTab(tabLayout.newTab().setText("Questions"));
//        tabLayout.addTab(tabLayout.newTab().setText("Games"));
//        tabLayout.addTab(tabLayout.newTab().setText("Materials"));
//        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
//
////        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
//
////        final PagerAdapter adapter = new com.example.lmont.iceicebb.Fragments.PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
////
////        viewPager.setAdapter(adapter);
//
//        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
//        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                viewPager.setCurrentItem(tab.getPosition());
//
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
//
//        setup();
//        setupContentResolver();
//        handleIntent(getIntent());
//    }
//
//    public void setup() {
//        gameArray = dbHelper.getAllGames();
//        RecyclerView gameList = (RecyclerView) findViewById(R.id.gameList);
//        gameList.setHasFixedSize(true);
//
//        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
//            gameList.setLayoutManager(new GridLayoutManager(this, 2));
//        }
//        else{
//            gameList.setLayoutManager(new GridLayoutManager(this, 4));
//        }
//
//        GameListRecyclerAdapter adapter = new GameListRecyclerAdapter(gameArray);
//        gameList.setAdapter(adapter);
//    }
//
//    @Override
//    protected void onNewIntent(Intent intent) {
//        handleIntent(intent);
//    }
//
//    private void handleIntent(Intent intent) {
//        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
//            String query = intent.getStringExtra(SearchManager.QUERY);
//            Toast.makeText(this, query, Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.options_menu, menu);
//
//        SearchManager searchManager =
//                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//        SearchView searchView =
//                (SearchView) menu.findItem(R.id.search).getActionView();
//        searchView.setSearchableInfo(
//                searchManager.getSearchableInfo(getComponentName()));
//
//        return true;
//    }
//
//    public static Account createSyncAccount(Context context) {
//        // Create the account type and default account
//        Account newAccount = new Account(
//                ACCOUNT, ACCOUNT_TYPE);
//        // Get an instance of the Android account manager
//        AccountManager accountManager =
//                (AccountManager) context.getSystemService(
//                        ACCOUNT_SERVICE);
//        /*
//         * Add the account and account type, no password or user data
//         * If successful, return the Account object, otherwise report an error.
//         */
//        if (accountManager.addAccountExplicitly(newAccount, null, null)) {
//            /*
//             * If you don't set android:syncable="true" in
//             * in your <provider> element in the manifest,
//             * then call context.setIsSyncable(account, AUTHORITY, 1)
//             * here.
//             */
//        } else {
//            /*
//             * The account exists or some other error occurred. Log this, report it,
//             * or handle it internally.
//             */
//        }
//        return newAccount;
//    }
//
//    public void setupContentResolver()  {
//        mAccount = createSyncAccount(this);
//
//        Bundle settingsBundle = new Bundle();
//        settingsBundle.putBoolean(
//                ContentResolver.SYNC_EXTRAS_MANUAL, true);
//        settingsBundle.putBoolean(
//                ContentResolver.SYNC_EXTRAS_EXPEDITED, true);
//        /*
//         * Request the sync for the default account, authority, and
//         * manual sync settings
//         */
//        ContentResolver.requestSync(mAccount, AUTHORITY, settingsBundle);
//
//        ContentResolver.setSyncAutomatically(mAccount,AUTHORITY,true);
//        ContentResolver.addPeriodicSync(
//                mAccount,
//                AUTHORITY,
//                Bundle.EMPTY,
//                30);
//
//    }
}
