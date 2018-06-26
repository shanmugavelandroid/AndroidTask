package shanmugaveltask_project.com.sampletask.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import shanmugaveltask_project.com.sampletask.model.response.UserDetails;

@Database(entities = {UserDetails.class}, version = 1, exportSchema = false)
public  abstract class Userdatabase extends RoomDatabase {

    public abstract DaoAccess daoAccess() ;

}
