package esiea.org.app.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import esiea.org.app.Database.User;
import esiea.org.app.R;

/**
 * Created by Ayoub Bouthoukine on 08/11/2016.
 */
public class ListAdapter extends BaseAdapter{


    private Activity activity;
    private LayoutInflater inflater;
    private List<User> userItems;

    public ListAdapter(Activity activity, List<User> userItems) {
        this.activity = activity;
        this.userItems = userItems;
    }

    @Override
    public int getCount() {
        return userItems.size();
    }

    @Override
    public Object getItem(int i) {
        return userItems.get(i);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);

        TextView name = (TextView) convertView.findViewById(R.id.name);
        User u = userItems.get(position);
        name.setText(u.getName());

        return convertView;
    }
}
