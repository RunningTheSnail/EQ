package me.danwi.utils;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.danwi.eq.adapter.CommonAdapter;

/**
 * Created with Android Studio.
 * User: HandSome-T
 * Date: 16/10/19
 * Time: 下午5:27
 */
public class NameAdapter extends CommonAdapter<String, NameAdapter.ViewHolder> {

    public NameAdapter(Context context, List<String> dataList) {
        super(context, R.layout.item, dataList);
    }

    @Override
    public void onBindCommonViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.tvName.setText(dataList.get(position));
    }

    @Override
    public ViewHolder getViewHolder(View view, int viewType) {
        return new ViewHolder(view);
    }

    public static class ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
