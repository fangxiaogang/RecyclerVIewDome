package com.example.xiaogang.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by xiaogang on 16/7/21.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<String> mlist;


    public RecyclerViewAdapter(Context context, List<String> mlist) {
        this.context = context;
        this.mlist = mlist;
    }
    //在apapter设置点击事件回调借口
    private OnItemClickListener mListener;
    public interface OnItemClickListener {

        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {

        this.mListener = listener;
    }
    //


    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler, parent, false);
        return new RecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewAdapter.ViewHolder holder, int position) {
        System.out.println(position);
        holder.textView.setText(mlist.get(position));
        //点击事件
        if (mListener != null){
            holder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //获取实际的位置
                    int pos = holder.getLayoutPosition();
                    mListener.onItemClick(holder.textView,pos);

                }
            });
            holder.textView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int pos = holder.getLayoutPosition();
                    mListener.onItemLongClick(holder.textView,pos);
                    //屏蔽单击  false点击事件仍会响应
                    return true;
                }
            });

        }
    }
    /**
     * 在当前Item之前添加Item
     */
    public void addPreItem(String data,int position) {

        mlist.add(position,data);
        notifyItemInserted(position);
    }

    /**
     * 在当前Item之后添加Item
     */
    public void addNextItem(String data,int position) {

        mlist.add(position+1,data);
        notifyItemInserted(position+1);
    }

    /**
     * 删除Item
     *
     */
    public void removeItem(int position) {
        mlist.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textview);
        }
    }
}
