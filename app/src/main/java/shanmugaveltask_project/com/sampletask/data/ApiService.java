package shanmugaveltask_project.com.sampletask.data;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;
import shanmugaveltask_project.com.sampletask.model.response.UserMaster;

public interface ApiService {

    @GET("users")
    Single<UserMaster> getUserDetails(@Query("page") int page);
}
