package vn.edu.assigment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseHolder> {

    public Context context;
    public List<Couser> couserList;
    public AppDataBase appDataBase;

    public CourseAdapter(Context context, List<Couser> couserList, AppDataBase appDataBase) {
        this.context = context;
        this.couserList = couserList;
        this.appDataBase = appDataBase;
    }

    @NonNull
    @Override
    public CourseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.course, parent, false);
        CourseHolder courseHolder = new CourseHolder(view);
        return courseHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CourseHolder holder, final int position) {
        final Couser couser = couserList.get(position);
        holder.edtname.setText(couser.name);
        holder.edtprice.setText(couser.price + "");
        holder.imgAddInfor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    long[] result = appDataBase.courseDao().insert(couser);

                    if (result != null) {
                        Toast.makeText(context, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Lỗi đăng ký", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(context,"Chỉ đăng ký khóa học 1 lần",Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    @Override
    public int getItemCount() {
        return couserList.size();
    }
}
