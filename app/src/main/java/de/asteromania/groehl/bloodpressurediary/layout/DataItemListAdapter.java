package de.asteromania.groehl.bloodpressurediary.layout;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;

import  de.asteromania.groehl.bloodpressurediary.R;
import de.asteromania.groehl.bloodpressurediary.domain.DataItem;
import de.asteromania.groehl.bloodpressurediary.domain.ListViewItem;

/**
 * Created by jgroehl on 18.09.16.
 */
public class DataItemListAdapter extends BaseAdapter
{
    private static final String TAG = "DataItemListAdapter";
    private static LayoutInflater inflater=null;

    private ArrayList<ListViewItem> dataItems;

    public DataItemListAdapter(Activity context, Collection<? extends ListViewItem> dataItems) {

        this.dataItems = new ArrayList<>();
        this.dataItems.addAll(dataItems);

        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        Log.i(TAG, "Instantiated DataItemListAdapter");
    }
    @Override
    public int getCount() {
        return dataItems.size();
    }

    @Override
    public Object getItem(int position) {
        return dataItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Log.i(TAG, "Called getView");
        View rowView;
        rowView = inflater.inflate(R.layout.layout_data_item, null);
        TextView text =(TextView) rowView.findViewById(R.id.textViewDataItemText);
        TextView value =(TextView) rowView.findViewById(R.id.textViewDataItemValue);
        ImageView img=(ImageView) rowView.findViewById(R.id.imageViewTrend);
        Button btn = (Button) rowView.findViewById(R.id.buttonInfo);

        text.setText(dataItems.get(position).getDataItemType().getText());
        value.setText(String.valueOf(dataItems.get(position).getMean()));
        img.setImageResource(dataItems.get(position).getDataItemTrend().getImage());

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        return rowView;
    }

}
