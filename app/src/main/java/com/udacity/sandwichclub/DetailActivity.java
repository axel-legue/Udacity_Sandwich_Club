package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    public static final String SANDWICH_SELECTED = "sandwich_selected";
    private Sandwich mSandwichSelected;
    @BindView(R.id.iv_sandwich)
    ImageView mSandwichImageView;
    @BindView(R.id.tv_main_name)
    TextView mMainNameTextView;
    @BindView(R.id.tv_also_known_as)
    TextView mAlsoKnownAsTextView;
    @BindView(R.id.tv_description)
    TextView mDescriptionTextView;
    @BindView(R.id.tv_ingredients)
    TextView mIngredientTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sandwich_detail_layout);

        initData();
        initView();
        populateUI();

    }

    private void initData() {
        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mSandwichSelected = bundle.getParcelable((SANDWICH_SELECTED));
        }

    }

    private void initView() {
        ButterKnife.bind(this);
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {
        if (mSandwichSelected != null) {
            mMainNameTextView.setText(mSandwichSelected.getMainName());
            Picasso.with(this).load(mSandwichSelected.getImage()).placeholder(R.drawable.placeholder).error(R.drawable.android_error).into(mSandwichImageView);

            if (mSandwichSelected.getAlsoKnownAs().size() > 0) {
                String joinedKnownAs = TextUtils.join(" / ", mSandwichSelected.getAlsoKnownAs());
                String alsoKnownAsString = String.format(getResources().getString(R.string.also_known_as), joinedKnownAs);
                Spanned resultAlsoKnownAs = Html.fromHtml(alsoKnownAsString);
                mAlsoKnownAsTextView.setVisibility(View.VISIBLE);
                mAlsoKnownAsTextView.setText(resultAlsoKnownAs);
            } else {
                mAlsoKnownAsTextView.setVisibility(View.GONE);
            }

            String joinedIngredients = TextUtils.join(" - ", mSandwichSelected.getIngredients());
            String ingredientString = String.format(getResources().getString(R.string.ingredients), joinedIngredients);
            Spanned resultIngredients = Html.fromHtml(ingredientString);
            mIngredientTextView.setText(resultIngredients);

            String descriptionString = String.format(getResources().getString(R.string.description), mSandwichSelected.getDescription());
            Spanned resultDescription = Html.fromHtml(descriptionString);
            mDescriptionTextView.setText(resultDescription);
        }
    }


}
