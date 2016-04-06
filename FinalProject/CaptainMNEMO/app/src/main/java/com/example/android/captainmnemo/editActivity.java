package com.example.android.captainmnemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class editActivity extends AppCompatActivity {

    SavedItem si = new SavedItem("blank","blank",0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        loadData();
    }

    public void loadData() {
        //INSERT DATA FROM INTENT TO SI

        Bundle extras = getIntent().getExtras();
        si.itemname = extras.getString("passedItemName");
        si.itemmnemo = extras.getString("passedItemMnemo");
        si.itemdescription = extras.getString("passedItemDescription");
        si.wasset = extras.getBoolean("passedItemSet");
        si.viewnum = extras.getInt("passedItemNum");

        //INSERT DATA FROM SI TO FIELDS
        //RELOAD FIELDS
        EditText editNameText = (EditText) findViewById(R.id.edit_name_view);
        editNameText.setText(si.itemname);
        EditText editMnemoText = (EditText) findViewById(R.id.edit_mnemo_view);
        editMnemoText.setText(si.itemmnemo);
        EditText editDescriptionText = (EditText) findViewById(R.id.edit_description_view);
        editDescriptionText.setText(si.itemdescription);
    }

    public void startClose(View view) {
        finish();
    }

    public void startDone(View view) {
        EditText editNameText = (EditText) findViewById(R.id.edit_name_view);
        EditText editMnemoText = (EditText) findViewById(R.id.edit_mnemo_view);
        EditText editDescriptionText = (EditText) findViewById(R.id.edit_description_view);

        Intent data = getIntent(); // new Intent();
        data.putExtra("returnedItemNum", si.viewnum);
        data.putExtra("returnedItemName", editNameText.getText());
        data.putExtra("returnedItemMnemo", editMnemoText.getText());
        data.putExtra("returnedItemDescription", editDescriptionText.getText());
        data.putExtra("returnedItemSet", true);

        setResult(RESULT_OK,data);

        finish();


    }

}
