package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements SandwichAdapter.SandwichListener {

    @BindView(R.id.rv_sandwich)
    RecyclerView mSandwichRecyclerView;
    private SandwichAdapter mSandwichAdapter;
    private List<Sandwich> mSandwiches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
    }

    private void initData() {

        mSandwiches = new ArrayList<>();

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        for (String sandwichJSON : sandwiches) {
            Sandwich sandwich = JsonUtils.parseSandwichJson(sandwichJSON);
            mSandwiches.add(sandwich);
        }
    }

    private void initView() {

        ButterKnife.bind(this);

        mSandwichAdapter = new SandwichAdapter(mSandwiches, this, this);
        mSandwichRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mSandwichRecyclerView.setAdapter(mSandwichAdapter);
        mSandwichAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSandwichClicked(Sandwich sandwich) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.SANDWICH_SELECTED, sandwich);
        startActivity(intent);
    }
}
