package vn.edu.assigment;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Couser.class},version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract CourseDao courseDao();
}
