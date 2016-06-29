package ldaures.designapp;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ldaures.designapp.API.model.Raspberry;

/**
 * Created by triba on 29/06/2016.
 */
public class RaspberryAdapter extends ArrayAdapter<Raspberry> {
    //tweets est la liste des models à afficher
    public RaspberryAdapter(Context context, ArrayList<Raspberry> raspberrys) {
        super(context, 0, raspberrys);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.raspberry_card_list,parent, false);
        }

        RaspberryViewHolder viewHolder = (RaspberryViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new RaspberryViewHolder();
            viewHolder.addr = (TextView) convertView.findViewById(R.id.RaspberryCardAddr);
            viewHolder.name = (TextView) convertView.findViewById(R.id.RaspberryCardName);
            viewHolder.card = (CardView) convertView.findViewById(R.id.card_viewRaspberry);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        Raspberry raspberry = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.addr.setText(raspberry.adr);
        viewHolder.name.setText(raspberry.name);
        viewHolder.card.setCardElevation(5.0f);
        viewHolder.card.setRadius(5.0f);

        return convertView;
    }

    private class RaspberryViewHolder{
        public TextView name;
        public TextView addr;
        public CardView card;
    }
}
