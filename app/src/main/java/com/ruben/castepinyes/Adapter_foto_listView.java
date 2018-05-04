package com.ruben.castepinyes;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by ruben on 16/03/2018.
 */

public class Adapter_foto_listView extends BaseAdapter {

    public ArrayList imagen;
    public Context context;

    public Adapter_foto_listView(ArrayList imagen, Context context) {
        this.imagen = imagen;
        this.context = context;
    }

    @Override
    public int getCount() {
        return imagen.size();
    }

    @Override
    public Object getItem(int position) {
        return imagen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        View rowView = convertView;
        if (rowView == null) {
            rowView = LayoutInflater.from(context).inflate(R.layout.adapter_fotos_pinya, parent, false);
            viewHolder = new ViewHolder();

            viewHolder.imagen = rowView.findViewById(R.id.Foto_pinya);

            Picasso.get().load((Uri) imagen.get(position)).into(viewHolder.imagen);
        }


        return rowView;
    }

    private static class ViewHolder {

        public ImageView imagen;
    }
}
