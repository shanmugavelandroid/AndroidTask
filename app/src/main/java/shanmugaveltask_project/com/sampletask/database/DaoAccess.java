package shanmugaveltask_project.com.sampletask.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import shanmugaveltask_project.com.sampletask.model.response.UserDetails;

@Dao
public interface DaoAccess {

    @Insert
    void insertUserdetails(UserDetails userDetails);

    @Query("SELECT * FROM  UserDetails")
    List<UserDetails> fetchalluserdetails();


    @Query("SELECT * FROM  UserDetails WHERE id = :id ")
    UserDetails checkinsertdata(int id);


}
