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
 * Created by lingaraj on 6/18/2015.
 */
public class deuteronomy_main extends Fragment{
    public static final String preferance_name="Bible_Preference";
    public DatabaseAssetHelper mydb;
    public Spinner mySpinner;
    public TextView mytextview;
    public ArrayAdapter<String> myadap;
    public String[] mylist = {"DEUTERONOMY 1", "DEUTERONOMY 2", "DEUTERONOMY 3", "DEUTERONOMY 4", "DEUTERONOMY 5", "DEUTERONOMY 6", "DEUTERONOMY 7", "DEUTERONOMY 8", "DEUTERONOMY 9", "DEUTERONOMY 10", "DEUTERONOMY 11", "DEUTERONOMY 12"
            , "DEUTERONOMY 13", "DEUTERONOMY 14", "DEUTERONOMY 15", "DEUTERONOMY 16", "DEUTERONOMY 17", "DEUTERONOMY 18", "DEUTERONOMY 19", "DEUTERONOMY 20", "DEUTERONOMY 21", "DEUTERONOMY 22", "DEUTERONOMY 23", "DEUTERONOMY 24", "DEUTERONOMY 25"
            , "DEUTERONOMY 26", "DEUTERONOMY 27", "DEUTERONOMY 28", "DEUTERONOMY 29", "DEUTERONOMY 30","DEUTERONOMY 31"," DEUTERONOMY 32", "DEUTERONOMY 33", "DEUTERONOMY 34"




    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootview = inflater.inflate(R.layout.genesis_main, container, false);
        mySpinner = (Spinner) rootview.findViewById(R.id.number_spin);
        mytextview=(TextView)rootview.findViewById(R.id.mytextview);
        mydb=new DatabaseAssetHelper(getActivity());
        myadap = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, mylist);
        get_shared_preferencevalue();

        mySpinner.setAdapter(myadap);
        setHasOptionsMenu(true);
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/times.ttf");
        mytextview.setTypeface(tf);



        myadap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

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

    private void get_shared_preferencevalue() {

        try {


            SharedPreferences sp = this.getActivity().getSharedPreferences(preferance_name, Context.MODE_PRIVATE);
            //SharedPreferences sp= PreferenceManager.getDefaultSharedPreferences(this.getActivity());
            int value=sp.getInt("my_int_key",18);
            if(value==0)
            {
                mytextview.setTextSize(18);
                Toast.makeText(getActivity(),"Current Textsize: 18",Toast.LENGTH_LONG).show();

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
    public void getData(int item)
    {
        int myid=item;





        String query="SELECT sayings FROM deuteronomy WHERE id=?";
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


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        return super.onOptionsItemSelected(item);
    }








}