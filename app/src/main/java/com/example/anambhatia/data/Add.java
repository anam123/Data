package com.example.anambhatia.data;

import android.app.Activity;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by AnamBhatia on 01/10/16.
 */
public class Add extends ListActivity {

    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sp;
    Button viewdb;
    Button adddb;
    EditText movie;
    EditText review;
    EditText rating;
    TextView student_Id;
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);
        adddb=(Button) findViewById(R.id.button2);
        movie=(EditText)findViewById(R.id.editText3);
        review=(EditText)findViewById(R.id.editText2);
        rating=(EditText)findViewById(R.id.editText);
        makedb();


    }


    public void makedb(){
        db=openOrCreateDatabase("ReviewDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS reviews(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, movie_name text,movie_review text,movie_rating integer);");
    }

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }
    public void addit(View v)
    {
        String mname = movie.getText().toString().trim();
        String mreview = review.getText().toString().trim();
        int mrating;

        if(rating.getText().toString().equals("")) {
            mrating=0;
        }
        else
        {
           mrating  = Integer.parseInt(rating.getText().toString());
        }

        if(mname.equals("") || mreview.equals("") || mrating==0){
            Toast.makeText(getApplicationContext(),"Please fill all fields", Toast.LENGTH_LONG).show();
            return;
        }

        String filename = "reviewinternal.txt";
        String m="Movie Name:";
        String re="Review:";
        String rat="Rating:";
        String nline="\n";
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(filename, Context.MODE_APPEND);
            outputStream.write(nline.getBytes());
            outputStream.write(nline.getBytes());
            outputStream.write(m.getBytes());
            outputStream.write(nline.getBytes());
            outputStream.write(mname.getBytes());
            outputStream.write(nline.getBytes());
            outputStream.write(re.getBytes());
            outputStream.write(nline.getBytes());
            outputStream.write(mreview.getBytes());
            outputStream.write(nline.getBytes());
            outputStream.write(rat.getBytes());
            outputStream.write(nline.getBytes());
            outputStream.write(Integer.toString(mrating).getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String filename1 = "reviewsextpublic.txt";
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),filename1);

        FileOutputStream os;
        try {
        os = new FileOutputStream(file,true);
        os.write(nline.getBytes());
        os.write(nline.getBytes());
        os.write(m.getBytes());
        os.write(nline.getBytes());
        os.write(mname.getBytes());
        os.write(nline.getBytes());
        os.write(re.getBytes());
        os.write(nline.getBytes());
        os.write(mreview.getBytes());
        os.write(nline.getBytes());
        os.write(rat.getBytes());
        os.write(nline.getBytes());
        os.write(Integer.toString(mrating).getBytes());
        os.close();
    } catch (Exception e) {
        e.printStackTrace();
    }

        String filename2 = "reviewsextprivate.txt";
        File file1 = new File(getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), filename2);

        FileOutputStream os1;
        try {
            os1 = new FileOutputStream(file1,true);
            os1.write(nline.getBytes());
            os1.write(nline.getBytes());
            os1.write(m.getBytes());
            os1.write(nline.getBytes());
            os1.write(mname.getBytes());
            os1.write(nline.getBytes());
            os1.write(re.getBytes());
            os1.write(nline.getBytes());
            os1.write(mreview.getBytes());
            os1.write(nline.getBytes());
            os1.write(rat.getBytes());
            os1.write(nline.getBytes());
            os1.write(Integer.toString(mrating).getBytes());
            os1.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String filename3 = "reviewsext.txt";
        File file2 = new File(Environment.getExternalStorageDirectory(), filename3);

        FileOutputStream os2;
        try {
            os2 = new FileOutputStream(file2,true);
            os2.write(nline.getBytes());
            os2.write(nline.getBytes());
            os2.write(m.getBytes());
            os2.write(nline.getBytes());
            os2.write(mname.getBytes());
            os2.write(nline.getBytes());
            os2.write(re.getBytes());
            os2.write(nline.getBytes());
            os2.write(mreview.getBytes());
            os2.write(nline.getBytes());
            os2.write(rat.getBytes());
            os2.write(nline.getBytes());
            os2.write(Integer.toString(mrating).getBytes());
            os2.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        String query = "INSERT INTO reviews (movie_name,movie_review,movie_rating) VALUES('"+mname+"', '"+mreview+"','"+mrating+"');";
        db.execSQL(query);
        Toast.makeText(getApplicationContext(),"Saved Successfully to the SQL DataBase, The Internal Storage and The External Storage. ", Toast.LENGTH_LONG).show();
        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sp.edit();
        editor.putLong("total", getProfilesCount());
        editor.commit();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public ArrayList<HashMap<String, String>>  getList() {

        String selectQuery =  "SELECT  " +
                "movie_name" + "," +
                "movie_review" + "," +
                "movie_rating" +
                " FROM " + "reviews";


        ArrayList<HashMap<String, String>> movielist = new ArrayList<HashMap<String, String>>();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> movie = new HashMap<String, String>();
                movie.put("name", cursor.getString( cursor.getColumnIndex("movie_name")));
                movie.put("rating", cursor.getString(cursor.getColumnIndex("movie_rating"))+" Stars");
                movie.put("review", cursor.getString(cursor.getColumnIndex("movie_review")));
                movielist.add(movie);

            } while (cursor.moveToNext());
        }

        cursor.close();

        return movielist;

    }



    public void view(View v)
    {


        ArrayList<HashMap<String, String>> studentList =  getList();
        if(studentList.size()!=0) {
            ListView lv = getListView();
            ListAdapter adapter = new SimpleAdapter( Add.this,studentList, R.layout.listing, new String[] { "name","rating","review"}, new int[] {R.id.movie, R.id.rating, R.id.review});
            setListAdapter(adapter);
        }else{
            Toast.makeText(this,"No movie!",Toast.LENGTH_SHORT).show();
        }

    }

    public void delete(View v)
    {
        String mname = movie.getText().toString().trim();
        String mreview = review.getText().toString().trim();
        int mrating;
        if(rating.getText().toString().equals("")) {
            mrating=0;
        }
        else
        {
            mrating  = Integer.parseInt(rating.getText().toString());
        }
        if(mname.equals("")){
            Toast.makeText(getApplicationContext(),"Please fill movie field to delete the particular movie", Toast.LENGTH_LONG).show();
            return;
        }

        Long n=getProfilesCount();

            db.delete("reviews", "movie_name" + "=" + '"'+mname+'"', null);
        if(n==getProfilesCount())
        {
            Toast.makeText(getApplicationContext(),"no such movie found!", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "movie review deleted!", Toast.LENGTH_LONG).show();
            sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor = sp.edit();
            editor.putLong("total", getProfilesCount());
            editor.commit();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }



    }

    public long getProfilesCount() {
        long cnt  = DatabaseUtils.queryNumEntries(db, "reviews");
        return cnt;
    }

    public void update(View v)
    {
        ContentValues values = new ContentValues();
        String mname = movie.getText().toString().trim();
        String mreview = review.getText().toString().trim();

        int mrating;
        if(rating.getText().toString().equals("")) {
            mrating=0;
        }
        else
        {
            mrating  = Integer.parseInt(rating.getText().toString());
        }
        if(mname.equals("") || mreview.equals("") || mrating==0){
            Toast.makeText(getApplicationContext(),"Please fill all fields", Toast.LENGTH_LONG).show();
            return;
        }
        values.put("movie_name", mname);
        values.put("movie_review",mreview);
        values.put("movie_rating", mrating);

        db.update("reviews", values, "movie_name" + "=" + '"'+mname+'"', null);
        Toast.makeText(getApplicationContext(),"movie review updated!", Toast.LENGTH_LONG).show();

    }

}
