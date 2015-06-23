package com.ljaymori.hidingandshowingactionbar;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private RecyclerView recyclerView;
    private RecyclerAdapter mAdapter;

    private ActionBar mActionBar;
    private float mActionBarHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Action bar overlay
        // styles.xml:6 instead of
        // getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get action bar sizes
        final TypedArray styledAttributes = getTheme().obtainStyledAttributes(
                new int[] { android.R.attr.actionBarSize}
        );

        // Set default action bar height
        mActionBarHeight = styledAttributes.getDimension(0, 0);

        styledAttributes.recycle();
        mActionBar = getSupportActionBar();
        Log.i("actionbar height", mActionBarHeight+"");

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Add ScrollChangedListener
        recyclerView.addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                //TODO PLMK how to get a current position of recyclerView.
                if(mActionBar.isShowing() && dy > mActionBarHeight/6) {
                    mActionBar.hide();

                } else if (dy <= 0) {
                    mActionBar.show();

                }
            }
        });

        mAdapter = new RecyclerAdapter(this, initData());

        recyclerView.setAdapter(mAdapter);

    }

    private ArrayList<ItemData> initData() {
        ArrayList<ItemData> list = new ArrayList<ItemData>();
        ItemData header = new ItemData();
        header.setType(RecyclerAdapter.TYPE_HEADER);
        list.add(header);

        for(int i=0; i<30; i++) {
            ItemData id = new ItemData();
            id.setType(RecyclerAdapter.TYPE_ITEM);
            id.setTitle("Title - " + i);
            id.setContent("Content - " + i);

            list.add(id);
        }

        return list;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //    @Override
//    public void onScrollChanged() {
//        float y = recyclerView.getScrollY();
//        if(y >= mActionBarHeight && mActionBar.isShowing()) {
//            mActionBar.hide();
//        } else if (y == 0 && !mActionBar.isShowing()) {
//            mActionBar.show();
//        }
//    }

}
