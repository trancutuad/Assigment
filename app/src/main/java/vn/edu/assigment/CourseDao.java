package vn.edu.assigment;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CourseDao {

    @Query("SELECT*FROM couser")
    List<Couser> getAll();

    @Insert
    long[] insert(Couser...cousers);

    @Delete
    int delete(Couser couser);

    @Update
    int update(Couser couser);
}
