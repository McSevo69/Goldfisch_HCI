package at.ac.univie.hci.goldfisch;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created on 07/05/2017.
 */

public class CustomSwipeAdapter extends PagerAdapter {
    private int [] getraenke_fotos = {R.drawable.wasserglas, R.drawable.juicewithoutbackround, R.drawable.kaffee1, R.drawable.newbeer};
    private String [] text = {"Wasser", "Limonade", "Kaffee", "Alkohol"};
    private Context context;
    private LayoutInflater layoutinflater;


    public CustomSwipeAdapter (Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return getraenke_fotos.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (LinearLayout) object);

    }
    //einstellen des swipes
    public Object instantiateItem(ViewGroup container,final int position){
        layoutinflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //swipelayout ist zustaendig für die bilder von getraenken.
        View item_view = layoutinflater.inflate(R.layout.swipelayout, container, false);
        ImageView imageView = (ImageView) item_view.findViewById(R.id.image_view);
        TextView textView = (TextView) item_view.findViewById(R.id.image_count);
        imageView.setImageResource(getraenke_fotos[position]);
        textView.setText( text[position] );
        container.addView(item_view);
        return item_view;
    }

    public void destroyItem(ViewGroup container, int position, Object object){
        container.removeView((LinearLayout) object);
    }

}
