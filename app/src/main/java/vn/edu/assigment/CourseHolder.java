package vn.edu.assigment;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class CourseHolder extends RecyclerView.ViewHolder {

    public EditText edtname,edtprice;
    public ImageView imgAddInfor;
    public CourseHolder(@NonNull View itemView) {
        super(itemView);

        edtname=itemView.findViewById(R.id.edtname);
        edtprice=itemView.findViewById(R.id.edtprice);
        imgAddInfor=itemView.findViewById(R.id.imgAddInfor);
    }
}
