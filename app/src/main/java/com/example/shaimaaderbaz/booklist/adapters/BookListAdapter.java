package com.example.shaimaaderbaz.booklist.adapters;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shaimaaderbaz.booklist.R;
import com.example.shaimaaderbaz.booklist.models.Book;
import com.example.shaimaaderbaz.booklist.utils.Utils;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Shaimaa Derbaz on 2/3/2018.
 */

public class BookListAdapter extends ArrayAdapter<Book> {

    public BookListAdapter(Activity context, ArrayList<Book> items) {

        super(context, 0, items);

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(
                    getContext()).inflate(R.layout.list_item, null, false);

        }
        Book currentItem = getItem(position);
        TextView titleItemTextView=(TextView)listItemView.findViewById(R.id.item_title_text_view);
        titleItemTextView.setText(currentItem.getTitle());
        TextView autherItemTextView = (TextView) listItemView.findViewById(R.id.item_auther_text_view);
        autherItemTextView.setText(currentItem.getAuthor());
        TextView itemPublisherTextView = (TextView) listItemView.findViewById(R.id.item_publisher_text_view);
        itemPublisherTextView.setText(currentItem.getPublisher());
        TextView itemDescriptionTextView = (TextView) listItemView.findViewById(R.id.item_description_text_view);
        itemDescriptionTextView.setText(currentItem.getDescription());
        ImageView itemImageView = (ImageView) listItemView.findViewById(R.id.item_image_image_view);
        Picasso.with(getContext())
                .load(currentItem.getThumbnail())
                .resize(600,900).into(itemImageView);




        return listItemView;
    }

}
