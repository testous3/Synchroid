package oka.synchroid.Models;

import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;
import java.util.List;

import oka.synchroid.R;

/**
 * Created by okahoul on 13/03/2016.
 */
public class FileRecordAdapter extends ArrayAdapter<File> {
    private int resource;
    private LayoutInflater inflater;
    private Context context;


    public FileRecordAdapter(Context context, int _resource, List<File> objects) {
        super(context, _resource, objects);
        resource = _resource;
        inflater = LayoutInflater.from(context);
        context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = (RelativeLayout) inflater.inflate(resource, null);
        File file = getItem(position);
        TextView fileNameTextView = (TextView) convertView.findViewById(R.id.fileName);
        fileNameTextView.setText(file.getName());
        return convertView;
    }
}
