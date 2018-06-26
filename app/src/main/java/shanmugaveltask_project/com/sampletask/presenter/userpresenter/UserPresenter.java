package shanmugaveltask_project.com.sampletask.presenter.userpresenter;

import io.reactivex.disposables.CompositeDisposable;
import shanmugaveltask_project.com.sampletask.data.ApiService;

public interface UserPresenter {

    void getUserdata(ApiService apiService, int Pageno,boolean isnetworkavialable,CompositeDisposable disposable);

    void onDestroy();
}
