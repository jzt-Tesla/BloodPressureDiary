package de.asteromania.groehl.bloodpressurediary.layout;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.asteromania.groehl.bloodpressurediary.R;
import de.asteromania.groehl.bloodpressurediary.domain.DataItem;
import de.asteromania.groehl.bloodpressurediary.domain.DataItemType;

/**
 * Created by jgroehl on 18.09.16.
 */
public class ChoseDataTypeListAdapter extends BaseAdapter
{
    private static final String TAG = "DataTypeListAdapter";
    private static LayoutInflater inflater=null;

    private ArrayList<DataItemType> selectedDataItems;
    private final int size = DataItemType.values().length - 1;
    private Context context;

    public ChoseDataTypeListAdapter(Activity context)
    {

        this.selectedDataItems = new ArrayList<>();

        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        this.context = context;
    }

    @Override
    public int getCount()
    {
        return size;
    }

    @Override
    public DataItemType getItem(int position)
    {
        return DataItemType.values()[position+1];
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        Log.i(TAG, "Called getView");
        View rowView;
        rowView = inflater.inflate(R.layout.list_layout_chose_data_types, null);
        TextView typeName =(TextView) rowView.findViewById(R.id.textViewDataItemTypeName);
        ImageView typeIcon = (ImageView) rowView.findViewById(R.id.imageViewTypeIconSelect);

        int textId = getItem(position).getText();
        if(position==0)
            textId = R.string.dataTypeBloodPressure;

        typeName.setText(textId);
        typeIcon.setImageResource(getItem(position).getTypeIcon());

        final CheckBox cb = (CheckBox) rowView.findViewById(R.id.checkboxTrackDataItemType);
        cb.setChecked(selectedDataItems.contains(getItem(position)));
        cb.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                updateSelectedDataItems(position, getItem(position), cb);
            }
        });

        rowView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                cb.setChecked(!cb.isChecked());
                updateSelectedDataItems(position, getItem(position), cb);
            }
        });
        return rowView;
    }

    private void updateSelectedDataItems(int position, DataItemType type, CheckBox cb) {

        if(cb.isChecked() && !selectedDataItems.contains(type)) {
            if(position==0) {
                selectedDataItems.add(DataItemType.SYSTOLE);
                selectedDataItems.add(DataItemType.DIASTOLE);
            }
            else
                selectedDataItems.add(type);
        }
        else if(!cb.isChecked() && selectedDataItems.contains(type)) {
            if(position==0) {
                selectedDataItems.remove(DataItemType.SYSTOLE);
                selectedDataItems.remove(DataItemType.DIASTOLE);
            }
            else
                selectedDataItems.remove(type);
        }
    }

    public List<DataItemType> getSelectedDataItems()
    {
        return  selectedDataItems;
    }

    public void setTrackedDataItemTypes(List<DataItemType> trackedDataItemTypes) {
        if(trackedDataItemTypes!=null) {
            selectedDataItems.addAll(trackedDataItemTypes);
        }
    }
}
