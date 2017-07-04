package com.noegenesys.recyclerview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView ;
    RecyclerViewAdapter mAdapter;
    RecyclerViewAdapter.OnItemClickListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listener = new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick() {
//                Toast.makeText(getApplicationContext(),"Click",Toast.LENGTH_SHORT).show();
            }
        };
        recyclerView  = (RecyclerView)findViewById(R.id.recyclerview);
        mAdapter = new RecyclerViewAdapter(this, listener);

        RecyclerView.LayoutManager mLayout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayout);
        recyclerView.setAdapter(mAdapter);
    }
    static class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
        Context mContext;
        private final OnItemClickListener listener;

        public interface OnItemClickListener {
            void onItemClick();
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
        public RecyclerViewAdapter(Context mCotext, OnItemClickListener listener){
            this.mContext = mCotext;
            this.listener = listener;
        }

        @Override
        public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, final int position) {
            holder.bind(position, listener);
        }
        @Override
        public int getItemCount() {
            return 10;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView txt;
            LinearLayout layoutFull;
            public ViewHolder(View itemView) {
                super(itemView);
                txt = (TextView)itemView.findViewById(R.id.txtLabel);
                layoutFull = (LinearLayout)itemView.findViewById(R.id.layoutfull);
            }

            public void bind(final int position, final OnItemClickListener listener) {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override public void onClick(View v) {
                        listener.onItemClick();
                        Toast.makeText(mContext,"OnClickList"+position,Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }
}

