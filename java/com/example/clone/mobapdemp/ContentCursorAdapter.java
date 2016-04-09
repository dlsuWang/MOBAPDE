package com.example.clone.mobapdemp;

/**
 * Created by Clone on 2016. 3. 16..
 */
import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Clone on 2016. 2. 24..
 */
public class ContentCursorAdapter extends CursorRecyclerViewAdapter<ContentCursorAdapter.ContentViewHolder> {

    OnItemClickListener mOnItemClickListener;

    public ContentCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor);
    }

    @Override
    public void onBindViewHolder(ContentViewHolder viewHolder, Cursor cursor) {
        String title = cursor.getString(cursor.getColumnIndex(Content.COLUMN_TITLE));
        viewHolder.tvTitle.setText(title);
        viewHolder.tvTitle.setTag(cursor.getInt(cursor.getColumnIndex(Content.COLUMN_ID)));
        viewHolder.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(Integer.parseInt(v.getTag().toString())); // how to call id by click recyclerview
            }
        });

        String combination = cursor.getString(cursor.getColumnIndex(Content.COLUMN_COMBINATION));
        viewHolder.tvCombination.setText(combination);
        viewHolder.tvCombination.setTag(cursor.getInt(cursor.getColumnIndex(Content.COLUMN_ID)));
        viewHolder.tvCombination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(Integer.parseInt(v.getTag().toString())); // how to call id by click recyclerview
            }
        });
    }

    public void setmOnItemClickListener(OnItemClickListener m){
        this.mOnItemClickListener = m;
    }

    public interface OnItemClickListener{
        public void onItemClick(int id); // the id being the note's db id
    }

    @Override
    public ContentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //R.layout.note_item
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_title, null);

        return new ContentViewHolder(v);
    }

    public class ContentViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle, tvCombination;
        View container_content;

        public ContentViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvCombination = (TextView) itemView.findViewById(R.id.tv_combination);
            container_content = itemView.findViewById(R.id.container_content);
        }
    }
}
