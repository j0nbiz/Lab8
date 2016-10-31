package biz.j0n.lab8;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        String name = getIntent().getExtras().getString("dinoName");

        TextView tv = (TextView) findViewById(R.id.dinoName);
        tv.setText(name);

        ImageView iv = (ImageView) findViewById(R.id.dinoImage);

        int resID = getResources().getIdentifier(name.toLowerCase(), "drawable", this.getPackageName());

        iv.setImageResource(resID);
    }
}
