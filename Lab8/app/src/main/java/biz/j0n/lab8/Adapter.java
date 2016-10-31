package biz.j0n.lab8;

import android.content.Context;
import android.content.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends BaseAdapter{

    ArrayList<String> result;
    Context context;
    ArrayList<Integer> imageId;
    private static LayoutInflater inflater=null;

    public Adapter(MainActivity mainActivity, ArrayList<String> prgmNameList, ArrayList<Integer> prgmImages) {
        // TODO Auto-generated constructor stub
        result=prgmNameList;
        context=mainActivity;
        imageId=prgmImages;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView tv;
        ImageView img;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            Holder holder=new Holder();
            View rowView;
            rowView = inflater.inflate(R.layout.dinolist, null);
            holder.tv=(TextView) rowView.findViewById(R.id.textView1);
            holder.img=(ImageView) rowView.findViewById(R.id.imageView1);
            holder.tv.setText(result.get(position));
            holder.img.setImageResource(imageId.get(position));
            rowView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                    String s = result.get(position);

                    Intent newIntent = new Intent(context,DisplayActivity.class);
                    newIntent.putExtra("dinoName",s);
                    context.startActivity(newIntent);

                    }
                });
        return rowView;
    }

}