package naim.test.beta;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static int DB_VERSION=1;
    private static String DATABASE_NAME="Database_Recette";
    private static String TABLE_NAME="Recettes";
    private static String ITEM_TITLE="TITRE";
    private static String ITEM_IMAGE="IMAGE";
    private static String ITEM_INGREDIENT="INGREDIENT";
    private static String ITEM_PREPARATION="PREPARATION";
    private static String FAVORITE_STATUS="FAVORITE";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
        SQLiteDatabase db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE "+TABLE_NAME + " (TITRE TEXT, IMAGE BINARY, FAVORITE INTEGER, INGREDIENT TEXT, PREPARATION TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public void insertData(String titre, byte[] imageByte, int favorite,String ingredient, String preparation)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(ITEM_TITLE,titre);
        contentValues.put(ITEM_IMAGE,imageByte);
        contentValues.put(FAVORITE_STATUS,favorite);
        contentValues.put(ITEM_INGREDIENT,ingredient);
        contentValues.put(ITEM_PREPARATION,preparation);
       db.insert(TABLE_NAME,null,contentValues);

    }
    public int deleteTitle(String name)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABLE_NAME, ITEM_TITLE + "=" + name, null);
    }

    public Cursor getAllData()
    {

        SQLiteDatabase db=getWritableDatabase();
        Cursor res=db.rawQuery("SELECT * FROM "+TABLE_NAME, null);
        return  res;
    }
    public Cursor getImage(String name)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("SELECT * FROM "+TABLE_NAME+ " WHERE TITRE='"+name+"'", null);
        return  res;
    }
    public Cursor getFavData()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("SELECT * FROM "+TABLE_NAME+ " WHERE FAVORITE=1", null);
        return  res;
    }
    public boolean updateData(String titre, byte[] imageByte, int favorite,String ingredient, String preparation)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(ITEM_TITLE,titre);
        contentValues.put(ITEM_IMAGE,imageByte);
        contentValues.put(FAVORITE_STATUS,favorite);
        contentValues.put(ITEM_INGREDIENT,ingredient);
        contentValues.put(ITEM_PREPARATION,preparation);
        db.update(TABLE_NAME,contentValues,"TITRE = ?", new String[]{ titre });
        return true;
    }
    public boolean updateFav(String name,int fav)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("UPDATE Recettes SET FAVORITE="+fav+" WHERE TITRE='"+name+"'");
        return true;
    }

    public boolean checkExist(String name)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        Cursor res=db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE TITRE = '"+ name+"'", null);
        if (res.getCount()==0)
        {
            res.close();
            return true;
        }else {
            res.close();
            return false;
        }
    }



}