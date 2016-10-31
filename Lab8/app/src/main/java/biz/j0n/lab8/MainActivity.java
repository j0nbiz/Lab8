package biz.j0n.lab8;


import java.util.ArrayList;

import android.database.Cursor;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;

import android.widget.ListView;

public class MainActivity extends Activity {

    ListView lv;
    Context context;

    ArrayList prgmName;


    //public String[] dinoNameList = {"dino1","dino2"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;


        int[] dinoImagesDB = {
                R.drawable.edmontonia,
                R.drawable.gastonia,
                R.drawable.giganotosaurus,
                R.drawable.gorgosaurus,
                R.drawable.minmi,
                R.drawable.saltasaurus,
                R.drawable.saurolophus,
                R.drawable.suchomimus,
                R.drawable.utahraptor,
                R.drawable.yangchuanosaurus};

        String[] dinoNameListDB = {
                getString(R.string.edmontonia),
                getString(R.string.gastonia),
                getString(R.string.giganotosaurus),
                getString(R.string.gorgosaurus),
                getString(R.string.minmi),
                getString(R.string.saltasaurus),
                getString(R.string.saurolophus),
                getString(R.string.suchomimus),
                getString(R.string.utahraptor),
                getString(R.string.yangchuanosaurus)};

        String[] dinoNameAboutDB = {
                getString(R.string.edmontoniaAbout),
                getString(R.string.gastoniaAbout),
                getString(R.string.giganotosaurusAbout),
                getString(R.string.gorgosaurusAbout),
                getString(R.string.minmiAbout),
                getString(R.string.saltasaurusAbout),
                getString(R.string.saurolophusAbout),
                getString(R.string.suchomimusAbout),
                getString(R.string.utahraptorAbout),
                getString(R.string.yangchuanosaurusAbout)};


        DBHelper dbh = DBHelper.getDBHelper(this);

        // Create dinos
        for(int i = 0; i < 10; i++){
            dbh.insertNewDino(dinoNameListDB[i], dinoNameAboutDB[i], dinoImagesDB[i], dinoImagesDB[i]);
        }

        Cursor cursor = dbh.getDinos();

        ArrayList<Integer> dinoImages = new ArrayList<Integer>();
        ArrayList<String> dinoNameList = new ArrayList<String>();;

        // Get dinos
        while (cursor.moveToNext()) {
            dinoImages.add(cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_IMG_ID)));
            dinoNameList.add(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_NAME)));
        }

        lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(new Adapter(this, dinoNameList, dinoImages));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}