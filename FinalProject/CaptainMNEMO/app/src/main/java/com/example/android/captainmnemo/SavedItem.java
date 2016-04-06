package com.example.android.captainmnemo;

/**
 * class for items
 */
public class SavedItem {
        public String itemname = "n";
        public String itemmnemo = "m";
        public String itemdescription = "blank";
        public boolean wasset = false;
        public int viewnum = 0;
        //constructor
        public SavedItem(String a, String b, int c) {
            itemname = a;
            itemmnemo = b;
            viewnum = c;
        }

}
