package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView mSandwichRecyclerView;
    SandwichAdapter mSandwichAdapter;
    List<Sandwich> mSandwiches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSandwiches = new ArrayList<>();

//        String[] sandwiches = getResources().getStringArray(R.array.sandwich_names);

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        for (String sandwichJSON : sandwiches) {
            Sandwich sandwich = JsonUtils.parseSandwichJson(sandwichJSON);
            mSandwiches.add(sandwich);
        }


//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_list_item_1, sandwiches);

        // Simplification: Using a ListView instead of a RecyclerView

        mSandwichRecyclerView = findViewById(R.id.rv_sandwich);

        mSandwichAdapter = new SandwichAdapter(mSandwiches, this);
        mSandwichRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mSandwichRecyclerView.setAdapter(mSandwichAdapter);
        mSandwichAdapter.notifyDataSetChanged();



//        ListView listView = findViewById(R.id.sandwiches_listview);
//        listView.setAdapter(adapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                launchDetailActivity(position);
//            }
//        });
    }

    private void launchDetailActivity(int position) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_POSITION, position);
        startActivity(intent);
    }

}
