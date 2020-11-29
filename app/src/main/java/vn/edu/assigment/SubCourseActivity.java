package vn.edu.assigment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;

import java.util.List;

public class SubCourseActivity extends AppCompatActivity {
    private RecyclerView rvSubCourse;
    private SubCourseAdapter subCourseAdapter;
    private List<Couser> couserList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_course);
        rvSubCourse = findViewById(R.id.rvSubCourse);
        AppDataBase appDataBase = Room.databaseBuilder(SubCourseActivity.this, AppDataBase.class, "course.db").allowMainThreadQueries().build();
        couserList = appDataBase.courseDao().getAll();
        rvSubCourse.setHasFixedSize(true);
        subCourseAdapter = new SubCourseAdapter(SubCourseActivity.this, couserList, appDataBase);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SubCourseActivity.this, RecyclerView.VERTICAL, false);
        rvSubCourse.setLayoutManager(linearLayoutManager);
        rvSubCourse.setAdapter(subCourseAdapter);


    }
}
