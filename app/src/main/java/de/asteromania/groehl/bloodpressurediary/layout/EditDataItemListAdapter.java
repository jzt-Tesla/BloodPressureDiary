package de.asteromania.groehl.bloodpressurediary.layout;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

import de.asteromania.groehl.bloodpressurediary.R;
import de.asteromania.groehl.bloodpressurediary.domain.DataItem;
import de.asteromania.groehl.bloodpressurediary.views.EditDataItemsActivity;

/**
 * Created by jgroehl on 18.09.16.
 */
public class EditDataItemListAdapter extends BaseAdapter
{
    private static final String TAG = "EditDataItemListAdapter";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault());
    private static LayoutInflater inflater=null;

    private ArrayList<DataItem> dataItems;
    private ArrayList<DataItem> otherBpItems;
    private EditDataItemsActivity context;

    public EditDataItemListAdapter(EditDataItemsActivity context, Collection<? extends DataItem> dataItems) {

        this.dataItems = new ArrayList<>();
        this.dataItems.addAll(dataItems);

        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        this.context = context;
    }

    public EditDataItemListAdapter(EditDataItemsActivity context, Collection<? extends DataItem> dataItems, Collection<?extends DataItem> otherBpItems) {

        this(context, dataItems);

        this.otherBpItems = new ArrayList<>();
        this.otherBpItems.addAll(otherBpItems);
    }

    @Override
    public int getCount() {
        return dataItems.size();
    }

    @Override
    public DataItem getItem(int position) {
        return dataItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        View rowView;
        rowView = inflater.inflate(R.layout.list_layout_edit_data_item, null);
        TextView number = (TextView) rowView.findViewById(R.id.textViewEditDataItemNumber);
        TextView value = (TextView) rowView.findViewById(R.id.textViewEditDataItemValue);
        TextView date = (TextView) rowView.findViewById(R.id.textViewEditDataItemDate);
        Button edit = (Button) rowView.findViewById(R.id.butonEditDataItemEdit);
        Button delete = (Button) rowView.findViewById(R.id.butonEditDataItemDelete);

        number.setText((position+1) + ":");

        if(otherBpItems!=null)
        {
            value.setText(String.valueOf(dataItems.get(position).getValue()+"\n"+otherBpItems.get(position).getValue()));
        }
        else
        {
            value.setText(String.valueOf(getItem(position).getValue()));
        }
        date.setText(DATE_FORMAT.format(new Date(getItem(position).getDate())));

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.setupView();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setMessage(R.string.dialog_delete_message)
                        .setTitle(R.string.dialog_delete_title);

                builder.setPositiveButton(R.string.action_delete, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if(otherBpItems!=null)
                        {
                            context.getDatabaseService().getDataItemDatabaseAccess().deleteDataItem(dataItems.get(position));
                            context.getDatabaseService().getDataItemDatabaseAccess().deleteDataItem(otherBpItems.get(position));
                        }
                        else {
                            context.getDatabaseService().getDataItemDatabaseAccess().deleteDataItem(getItem(position));
                        }
                        context.setupView();
                    }
                })
                        .setNegativeButton(R.string.action_cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Do nothing
                            }
                        });

                AlertDialog dialog = builder.create();

                dialog.show();

            }
        });
        return rowView;
    }

}
