package com.example.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class project_database extends SQLiteOpenHelper {
    private static String databasename="Userdatabase";
    String usertable="create table user(U_id integer primary key autoincrement,Uname text not null,Upassword text not null," +
            "Uemail text not null,phone text,address text);";
    String tasktable="create table tasks(T_id integer primary key autoincrement,title text not null," +
            "status text not null ,description text not null" +
            ",Tdate text not null,user_id integer not null,FOREIGN KEY (user_id) REFERENCES user (U_id));";
    private SQLiteDatabase db;

    public project_database(Context context){
        super(context,databasename,null,2);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(usertable);
        db.execSQL(tasktable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
        db.execSQL("drop table if exists tasks");
        onCreate(db);
    }
    public void adduser(String name,String password,String email,String phone,String address){
        ContentValues row=new ContentValues();
        row.put("Uname",name);
        row.put("Upassword",password);
        row.put("phone",phone);
        row.put("address",address);
        row.put("Uemail",email);
        db=getWritableDatabase();
        db.insert("user",null,row);
        db.close();
    }
    /////////////////////////////////////////////////////
    // checks if user is exist
    public Cursor checkuser() {
        db = getReadableDatabase();
        String[] shape = {"Uname", "Upassword"}; // shakl el cursor rag3ly azay
        Cursor cursor = db.query("user", shape, null, null,
                null, null, null);// el cursor di hatrg3li data ( kol el users )
        if (cursor != null)    // lw el cursor != null y3ni el cursor 3ndi msh fady .. hatw2fly el curson 3nd awl row
            cursor.moveToFirst(); // 3shan aw2f el cursor 3nd awl row
        db.close();
        return cursor;
    }
// gets user_id
    // bdeha el user name w el password w btdwr 3leeh f table el user w trg3ly el user id
    public int getuserID(String name,String pass){
        db = getReadableDatabase();
        Cursor cursor=db.rawQuery("select U_id from user where Uname like ? and Upassword like ?",new String[]{name,pass});
        cursor.moveToFirst();
        db.close();
        int x=Integer.parseInt(cursor.getString(0));
        return x;
    }
    // add a task to tasks table
        public void addtask(String title,String date,String disc,int id){
        ContentValues row=new ContentValues();
        row.put("title",title);
        row.put("description",disc);
        row.put("Tdate",date);
        row.put("user_id",id);
        row.put("status","not completed");
        db=getWritableDatabase();// to write in the database
        db.insert("tasks",null,row);
        db.close();
    }


    //// in display activity
public Cursor findusertasks(int id){
        db=getReadableDatabase();
Cursor cursor=db.query("tasks",new String[]{"title","description","Tdate","status","T_id"},"user_id="+id,null,null,null,null);
cursor.moveToFirst();
db.close();
        return  cursor;
}
/////

    public void update(Cursor c,String n){
        String s,des,d,i;
        s=d=des=i="";
        while (!c.isAfterLast()) {
            if(c.getString(0).equals(n)) {
                s = c.getString(0);//name
                des = c.getString(1);//description
                d = c.getString(2);//date
                i = c.getString(4);//id
                break;
            }
            c.moveToNext();
        }
    c.moveToFirst();
    db=getWritableDatabase();
    ContentValues row=new ContentValues();
    row.put("title",s);
    row.put("description",des);
    row.put("Tdate",d);
    row.put("status","completed");
    row.put("T_id",i);
 db.update("tasks",row,"title like ? and description like ?",new String[]{n,des});
    db.close();
}
public void del(Cursor c,String n){
    String s="";
    db=getWritableDatabase();
    while (!c.isAfterLast()) {
        if(c.getString(0).equals(n)) {
           s=c.getString(1);
            break;
        }
        c.moveToNext();
    }
    c.moveToFirst();
    //
    db.delete("tasks","title like ? and description like ?",new String[]{n,s});
db.close();
}
}
