package com.example.lingaraj.theholybible;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by lingaraj on 6/19/2015.
 */
public class Ezekiel extends Fragment {
    public static final String preferance_name="Bible_Preference";
    public DatabaseAssetHelper mydb;
    public Spinner mySpinner;
    public TextView mytextview;
    public ArrayAdapter<String> myadap;
    public String[] mylist = {"EZEKIEL 1", "EZEKIEL 2", "EZEKIEL 3", "EZEKIEL 4", "EZEKIEL 5", "EZEKIEL 6", "EZEKIEL 7", "EZEKIEL 8", "EZEKIEL 9", "EZEKIEL 10", "EZEKIEL 11", "EZEKIEL 12"
            , "EZEKIEL 13", "EZEKIEL 14", "EZEKIEL 15", "EZEKIEL 16", "EZEKIEL 17", "EZEKIEL 18", "EZEKIEL 19", "EZEKIEL 20", "EZEKIEL 21", "EZEKIEL 22", "EZEKIEL 23", "EZEKIEL 24", "EZEKIEL 25"
            , "EZEKIEL 26", "EZEKIEL 27", "EZEKIEL 28", "EZEKIEL 29", "EZEKIEL 30","EZEKIEL 31"," EZEKIEL 32", "EZEKIEL 33", "EZEKIEL 34", "EZEKIEL 35", "EZEKIEL 36"," EZEKIEL 37", "EZEKIEL 38", "EZEKIEL 39", "EZEKIEL 40"," EZEKIEL 41", "EZEKIEL 42"," EZEKIEL 43"," EZEKIEL 44", "EZEKIEL 45", "EZEKIEL 46"," EZEKIEL 47", "EZEKIEL 48"




    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootview = inflater.inflate(R.layout.genesis_main, container, false);
        mySpinner = (Spinner) rootview.findViewById(R.id.number_spin);
        mytextview=(TextView)rootview.findViewById(R.id.mytextview);
        mydb=new DatabaseAssetHelper(getActivity());
        myadap = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, mylist);


        mySpinner.setAdapter(myadap);
        setHasOptionsMenu(true);
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/times.ttf");
        mytextview.setTypeface(tf);


        myadap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        get_shared_preferencevalue();
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                int item=position+1;
                getData(item);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                int item=1;
                getData(item);

            }
        });


        return rootview;



    }

    public void getData(int item)
    {
        int myid=item;





        String query="SELECT sayings FROM ezekiel WHERE id=?";
        try {
            SQLiteDatabase sq = mydb.getReadableDatabase();

            Cursor mycursor = sq.rawQuery(query, new String[]{String.valueOf(myid)});
            mycursor.moveToFirst();

            String sayings = mycursor.getString(mycursor.getColumnIndex("sayings")).toString();
            //String sayings= mycursor.getString(0).toString();
            mycursor.close();
            mytextview.setText(sayings);
            mytextview.setVisibility(View.VISIBLE);
            // Toast.makeText(getActivity(),item,Toast.LENGTH_LONG).show();

            mydb.close();
        }
        catch(Exception e)
        {
            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
        }

    }
    private void get_shared_preferencevalue() {

        try {


            SharedPreferences sp = this.getActivity().getSharedPreferences(preferance_name, Context.MODE_PRIVATE);
           // SharedPreferences sp= PreferenceManager.getDefaultSharedPreferences(this.getActivity());
            int value=sp.getInt("my_int_key",18);
            if(value==0)
            {
                mytextview.setTextSize(18);

            }
            else
            {
                mytextview.setTextSize(value);
            }



        }
        catch (Exception e)
        {
            Toast.makeText(getActivity(),e.toString(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        return super.onOptionsItemSelected(item);
    }













}
