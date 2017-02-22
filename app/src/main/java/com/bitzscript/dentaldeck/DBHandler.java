package com.bitzscript.dentaldeck;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by naimesh on 2/7/2017.
 */
public class DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DB_PATH = "/data/data/com.bitzscript.dentaldeck/databases/";//"/app/src/main/assets/databases/";
    private static final String DATABASE_NAME = "dentaldeck.db"; //database name
    private static final String TABLE_NAME = "Questions"; //table name
    //COLUMN NAMES
    private final static String TAG = "DBHandler";
    private String pathToSaveDBFile;
    private static final String KEY_ID = "ID";
    private static final String KEY_QUES = "question";
    private static final String KEY_ANS = "trueans"; //correct option
    private static final String KEY_OPTA = "ans1"; //option a
    private static final String KEY_OPTB = "ans2"; //option b
    private static final String KEY_OPTC = "ans3"; //option c
    private static final String KEY_OPTD = "ans4"; //option d
    private static final String KEY_OPTE = "ans5"; //option d
    private static final String KEY_OPTF = "ans6"; //option d
    private static final String KEY_EXPLAIN = "explain"; //explain
    private static final String KEY_SUBCODE = "subcode"; //subcode
    private SQLiteDatabase dbase;
    private Context myContext;

    public DBHandler(Context context, String filePath) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.myContext = context;
        pathToSaveDBFile = new StringBuffer(filePath).append("/").append(DATABASE_NAME).toString();
    }

    public void prepareDatabase() throws IOException {
        boolean dbExist = checkDataBase();
        if (dbExist) {
            Log.d(TAG, "Database exists.");
            int currentDBVersion = getVersionId();
            if (DATABASE_VERSION > currentDBVersion) {
                Log.d(TAG, "Database version is higher than old.");
                deleteDb();
                try {
                    copyDataBase();
                } catch (IOException e) {
                    Log.e(TAG, e.getMessage());
                }
            }
        } else {
            try {
                copyDataBase();
            } catch (IOException e) {
                Log.e(TAG, e.getMessage());
            }
        }
    }

    private boolean checkDataBase() {
        boolean checkDB = false;
        try {
            File file = new File(pathToSaveDBFile);
            checkDB = file.exists();
        } catch (SQLiteException e) {
            Log.d(TAG, e.getMessage());
        }
        return checkDB;
    }

    private void copyDataBase() throws IOException {
        OutputStream os = new FileOutputStream(pathToSaveDBFile);
        InputStream is = myContext.getAssets().open(DATABASE_NAME);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = is.read(buffer)) > 0) {
            os.write(buffer, 0, length);
        }
        is.close();
        os.flush();
        os.close();
    }

    public void deleteDb() {
        File file = new File(pathToSaveDBFile);
        if (file.exists()) {
            file.delete();
            Log.d(TAG, "Database deleted.");
        }
    }

    private int getVersionId() {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READONLY);
        String query = "SELECT version_id FROM dbVersion";
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        int v = cursor.getInt(0);
        db.close();
        return v;
    }

    @Override
    public synchronized void close() {

        if (dbase != null)
            dbase.close();

        super.close();


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
                + " TEXT, " + KEY_ANS+ " TEXT, "+KEY_OPTA +" TEXT, "
                +KEY_OPTB +" TEXT, "+KEY_OPTC+" TEXT, "+KEY_OPTD+" TEXT, "+KEY_OPTE+" TEXT, "+KEY_OPTF+" TEXT, "
                +KEY_EXPLAIN+" TEXT, "+ KEY_SUBCODE+" TEXT)";
        Log.e("DB",sql);
        db.execSQL(sql);
        try {
            copyDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /*// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        // Create tables again
        onCreate(db);*/

        if (newVersion > oldVersion) {
            Log.v("Database Upgrade", "Database version higher than old.");
        }

    }

    public List<Question_DB> getAllQuesions() {
        Log.d("method", "method called succesful");

        SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READONLY);
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);

        List<Question_DB> queslist = new ArrayList<Question_DB>();
        if (cursor.moveToFirst()) {
            do {
                Question_DB ques = new Question_DB();
                ques.setId(cursor.getInt(0));
                ques.setQuestion(cursor.getString(1));
                ques.setAnswer1(cursor.getString(2));
                ques.setAnswer2(cursor.getString(3));
                ques.setAnswer3(cursor.getString(4));
                ques.setAnswer4(cursor.getString(5));
                ques.setAnswer5(cursor.getString(6));
                ques.setAnswer6(cursor.getString(7));
                ques.setTrue_ans(cursor.getString(8));
                ques.setExplain(cursor.getString(9));
                ques.setSubcode(cursor.getString(10));
                queslist.add(ques);
            } while (cursor.moveToNext());
        }
        return queslist;
    }
}
