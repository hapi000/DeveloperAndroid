package com.example.android.adaptivelayout;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    
    private RecyclerView mRecyclerView;
    private ArrayList<Sport> mSportsData;
    private SportsAdapter mAdapter;
    private static String SPORT_TAG = "SPORT_LIST";

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(SPORT_TAG, mSportsData);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        
        mRecyclerView = findViewById(R.id.recyclerView);

        
        int gridColumnCount =
                getResources().getInteger(R.integer.grid_column_count);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, gridColumnCount));

        
        mSportsData = new ArrayList<>();

        if (savedInstanceState != null) {
            mSportsData = savedInstanceState.getParcelableArrayList(SPORT_TAG);
        }

        
        mAdapter = new SportsAdapter(this, mSportsData);
        mRecyclerView.setAdapter(mAdapter);

        if (savedInstanceState == null) {
            
            initializeData();
        }

        int swipeDirs;
        if(gridColumnCount > 1){
            swipeDirs = 0;
        } else {
            swipeDirs = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        }

        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT |
                ItemTouchHelper.DOWN | ItemTouchHelper.UP, swipeDirs) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();

                Collections.swap(mSportsData, from, to);
                mAdapter.notifyItemMoved(from, to);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                mSportsData.remove(viewHolder.getAdapterPosition());
                mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });
        helper.attachToRecyclerView(mRecyclerView);
    }

    private void initializeData() {
        
        String[] sportsList = getResources()
                .getStringArray(R.array.sports_titles);
        String[] sportsInfo = getResources()
                .getStringArray(R.array.sports_info);
        TypedArray sportImageResources = getResources().obtainTypedArray(R.array.sports_images);

        mSportsData.clear();

        
        
        for(int i=0;i<sportsList.length;i++){
            mSportsData.add(new Sport(sportsList[i],sportsInfo[i], sportImageResources.getResourceId(i, 0)));
        }

        
        mAdapter.notifyDataSetChanged();
        sportImageResources.recycle();

    }

    public void resetSports(View view) {
        initializeData();
    }

}