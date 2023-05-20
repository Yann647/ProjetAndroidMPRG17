package com.example.projetandroidprg17;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class ConnectionDBHelper extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "gestconnection.db";
    private static final String TABLE_NAME = "connection";
    private static final String ID = "id";
    private static final String NOM = "nom";
    private static final String PRENOM = "prenom";
    private static final String TELEPHONE = "telephone";
    private static final String ADRESSE = "adresse";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";

    public ConnectionDBHelper(Context context)
    {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String req = "CREATE TABLE " + TABLE_NAME + "(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NOM TEXT, PRENOM TEXT, TELEPHONE TEXT, ADRESSE TEXT," +
                "LOGIN TEXT, PASSWORD TEXT);";
        db.execSQL(req);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insertUser(Connection user)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues userValues = new ContentValues();
        userValues.put(NOM, user.getNom());
        userValues.put(PRENOM, user.getPrenom());
        userValues.put(TELEPHONE, user.getTelephone());
        userValues.put(ADRESSE, user.getAdresse());
        userValues.put(LOGIN, user.getLogin());
        userValues.put(PASSWORD, user.getPassword());
        db.insert(TABLE_NAME, null, userValues);
    }

    Connection getUserById(int id)
    {
        SQLiteDatabase db;
        db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[] { ID, NOM, PRENOM, TELEPHONE, ADRESSE,
        LOGIN, PASSWORD}, ID + "=?", new String[] { String.valueOf(id) },
                null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        int idUser = Integer.parseInt(cursor.getString(0));
        String nomUser = cursor.getString(1);
        String prenomUser = cursor.getString(2);
        String telephoneUser = cursor.getString(3);
        String adresseUser = cursor.getString(4);
        String loginUser = cursor.getString(5);
        String passwordUser = cursor.getString(6);
        Connection user = new Connection();
        user.setId(idUser);
        user.setNom(nomUser);
        user.setPrenom(prenomUser);
        user.setTelephone(telephoneUser);
        user.setAdresse(adresseUser);
        user.setLogin(loginUser);
        user.setPassword(passwordUser);

        return user;
    }

    public List<Connection> getAllUsers()
    {
        List<Connection> userList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                Connection user = new Connection();
                user.setId(Integer.parseInt(cursor.getString(0)));
                user.setNom(cursor.getString(1));
                user.setPrenom(cursor.getString(2));
                user.setTelephone(cursor.getString(3));
                user.setAdresse(cursor.getString(4));
                user.setLogin(cursor.getString(5));
                user.setPassword(cursor.getString(6));
                userList.add(user);
            }while (cursor.moveToNext());
        }

        return userList;
    }

    //public int updateUser(){}

}
