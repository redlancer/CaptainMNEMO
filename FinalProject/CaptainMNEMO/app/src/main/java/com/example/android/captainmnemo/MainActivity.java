package com.example.android.captainmnemo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {

    SavedItem[] savedItemArr = new SavedItem[4];
    static final int EDIT_ITEM_REQUEST = 1; //the request code

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.app_name);

        loadAllListsInit();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == EDIT_ITEM_REQUEST && resultCode == RESULT_OK) {
            if (null != data){
                Bundle extras = data.getExtras();

                int receivedNum = extras.getInt("returnedItemNum");
                savedItemArr[receivedNum].itemname = extras.getString("returnedItemName");
                savedItemArr[receivedNum].itemmnemo = extras.getString("returnedItemMnemo");
                savedItemArr[receivedNum].itemdescription = extras.getString("returnedItemDescription");
                savedItemArr[receivedNum].wasset = extras.getBoolean("returnedItemSet");

                listsToInterface();
            }
        }
    }


    public void loadAllListsInit() {
        //ADD -> CHECK FOR ANY PREVIOUSLY SAVED DATA, IF EXISTS, LOAD FROM IT. IF NOT:
        for(int i = 0; i < savedItemArr.length; i++)
        {
            savedItemArr[i] = new SavedItem("n","m",i);
        }
        listsToInterface();
    }

    public void listsToInterface() {
        for(int i = 0; i < savedItemArr.length; i++)
        {
            String buttonID = "item" +(i+1);
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            Button currentButton = (Button) findViewById(resID);
            String generatedText = "Item " +(i+1) + "\n\n\n" +savedItemArr[i].itemname +"\n\n\n" +savedItemArr[i].itemmnemo;
            currentButton.setText(generatedText);
            if(savedItemArr[i].wasset = false)
            {
                currentButton.setBackgroundColor(Color.parseColor("#00FF00"));
            }
            else
            {
                currentButton.setBackgroundColor(Color.parseColor("#FF0000"));
            }
        }
    }

    public void resetAllLists(View view) {
        for(int i = 0; i < savedItemArr.length; i++)
        {
            savedItemArr[i].itemname = "name";
            savedItemArr[i].itemmnemo = "mnemo";
            savedItemArr[i].itemdescription = "blank";
            savedItemArr[i].viewnum = i;
            savedItemArr[i].wasset = false;

            listsToInterface();
        }
    }

    public void startAboutActivity(View view) {
        Intent aboutActivity = new Intent(this, aboutActivity.class);
        startActivity(aboutActivity);
    }

    public void startEditActivity(View view) {

        Intent editActivity = new Intent(this, editActivity.class);

        int arrayPos = 0;
        switch(view.getId()){
            case R.id.item1:
                arrayPos = 0;
                break;
            case R.id.item2:
                arrayPos = 1;
                break;
            case R.id.item3:
                arrayPos = 2;
                break;
            case R.id.item4:
                arrayPos = 3;
                break;
        }
        editActivity.putExtra("passedItemNum",(arrayPos));
        editActivity.putExtra("passedItemName",savedItemArr[arrayPos].itemname);
        editActivity.putExtra("passedItemMnemo",savedItemArr[arrayPos].itemmnemo);
        editActivity.putExtra("passedItemDescription",savedItemArr[arrayPos].itemdescription);
        editActivity.putExtra("passedItemSet",savedItemArr[arrayPos].wasset);

        startActivityForResult(editActivity, EDIT_ITEM_REQUEST);
    }
}