package vn.edu.assigment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class Data_Adapter extends BaseAdapter {
    private Context context;
    private List<TinTuc> tinTucList;

    public Data_Adapter(Context context, List<TinTuc> tinTucList) {
        this.context = context;
        this.tinTucList = tinTucList;
    }

    @Override
    public int getCount() {
        return tinTucList.size();
    }

    @Override
    public Object getItem(int position) {
        return tinTucList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.tintuc, parent, false);
        TextView tvNew = view.findViewById(R.id.tvNew);
        final TinTuc tinTuc = tinTucList.get(position);
        tvNew.setText(tinTuc.title);

        tvNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,Web_View_Activity.class);
                intent.putExtra("link",tinTuc.linkk);
                context.startActivity(intent);

            }
        });

        return view;
    }
}
