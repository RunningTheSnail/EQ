package me.danwi.eq.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created with Android Studio.
 * User: HandSome-T
 * Date: 16/10/19
 * Time: 上午11:02
 */
public abstract class CommonAdapter<T, V> extends BaseAdapter {

    protected Context context;

    public List<T> dataList;

    private int resId;

    public CommonAdapter(Context context, List<T> dataList) {

    }

    public CommonAdapter(Context context, int resId, List<T> dataList) {
        this.context = context;
        this.resId = resId;
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public T getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        V viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resId, parent, false);
            viewHolder = getViewHolder(convertView, getItemViewType(position));
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (V) convertView.getTag();
        }
        onBindCommonViewHolder(viewHolder, position);
        return convertView;
    }

    public void refreshData(List<T> tList) {
        dataList.clear();
        dataList.addAll(tList);
        notifyDataSetChanged();
    }

    public abstract void onBindCommonViewHolder(V viewHolder, int position);

    public abstract V getViewHolder(View view, int viewType);
}
