package com.example.clone.mobapdemp;

/**
 * Created by Clone on 2016. 3. 15..
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
public class ContactCursorAdapter extends CursorRecyclerViewAdapter<ContactCursorAdapter.ContactViewHolder> {

    OnItemClickListener mOnItemClickListener;

    public ContactCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder viewHolder, Cursor cursor) {
        String name = cursor.getString(cursor.getColumnIndex(Contact.COLUMN_NAME));
        viewHolder.tvName.setText(name);
        viewHolder.tvName.setTag(cursor.getInt(cursor.getColumnIndex(Contact.COLUMN_ID)));
        viewHolder.tvName.setOnClickListener(new View.OnClickListener() {
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
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //R.layout.note_item
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.number_name, null);

        return new ContactViewHolder(v);
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder{
        TextView tvName;
        View container_contact;

        public ContactViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            container_contact = itemView.findViewById(R.id.container_contact);
        }
    }
}
