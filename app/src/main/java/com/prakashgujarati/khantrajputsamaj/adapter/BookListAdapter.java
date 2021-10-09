package com.prakashgujarati.khantrajputsamaj.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.prakashgujarati.khantrajputsamaj.R;
import com.prakashgujarati.khantrajputsamaj.flipbook.SampleActivity;
import com.prakashgujarati.khantrajputsamaj.model.Book;

import java.util.ArrayList;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Book> book;
    private View view;

    public BookListAdapter(FragmentActivity activity, ArrayList<Book> booklist) {
        this.context = activity;
        this.book = booklist;
    }

    @Override
    public BookListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_list_card, parent, false);
        return new BookListAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(BookListAdapter.ViewHolder holder, int position) {

        holder.title.setText(book.get(position).getTitle());
        holder.bookdate.setText(book.get(position).getSubtitle());

       /* holder.vanue.setText(SimpleItemFour.get(position).getVanue());
        holder.description.setText(SimpleItemFour.get(position).getDescription());
        holder.type.setText(SimpleItemFour.get(position).getType());*/

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, SampleActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return book.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView title,bookdate,address;

        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.thumbnail);
            title = itemView.findViewById(R.id.book_title);
            bookdate = itemView.findViewById(R.id.book_date);
            //vanue = itemView.findViewById(R.id.matrimoney_list_like);
            //type = itemView.findViewById(R.id.matrimoney_list_like);
            //description = itemView.findViewById(R.id.short_description);
        }
    }
}