package vn.edu.assigment;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class SubHolder extends RecyclerView.ViewHolder {
    public EditText edtSubname,edtSubprice;
    public ImageView imgDeleteInfor;
    public SubHolder(@NonNull View itemView) {
        super(itemView);

        edtSubname=itemView.findViewById(R.id.edtSubname);
        edtSubprice=itemView.findViewById(R.id.edtSubprice);
        imgDeleteInfor=itemView.findViewById(R.id.imgDeleteInfor);
    }
}
