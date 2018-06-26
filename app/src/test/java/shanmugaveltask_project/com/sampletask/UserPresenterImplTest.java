package shanmugaveltask_project.com.sampletask;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import shanmugaveltask_project.com.sampletask.data.ApiService;
import shanmugaveltask_project.com.sampletask.database.Userdatabase;
import shanmugaveltask_project.com.sampletask.model.response.UserDetails;
import shanmugaveltask_project.com.sampletask.presenter.userpresenter.UserPresenter;
import shanmugaveltask_project.com.sampletask.presenter.userpresenter.UserPresenterImpl;
import shanmugaveltask_project.com.sampletask.presenter.userpresenter.UserView;
import shanmugaveltask_project.com.sampletask.view.activity.MainActivity;

import static org.mockito.Mockito.verify;

public class UserPresenterImplTest {
    @Mock
    private UserView userView;

    @Mock
    public Userdatabase userdatabase;

    @Mock
    public ApiService apiService;


    @Mock
    public UserPresenter userPresenter;

    @Mock
    public MainActivity mainActivity;

    private CompositeDisposable disposable = new CompositeDisposable();

    public int Pageno=1 ,totalcount=0;
    boolean isnetworkavialable=true;

    public List<UserDetails> userData = new ArrayList<>();

    Throwable throwable;


    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

      /*  RxAndroidPlugins.setInitMainThreadSchedulerHandler(
                __ -> Schedulers.trampoline());*/
      /*  RxAndroidPlugins.setInitMainThreadSchedulerHandler(__ -> Schedulers.io());*/

        userPresenter= new UserPresenterImpl(userView,userdatabase, mainActivity);
    }


    @Test
    public void  onSuccessTest() {

     //   when(apiService.syncGenres()).thenReturn(Observable.just(Collections.emptyList());

        userPresenter.getUserdata(apiService,Pageno,isnetworkavialable,disposable);
       verify(userView).OnSuccess(userData,totalcount);
    }

    @Test
    public void  onFailureTest() {

        userPresenter.getUserdata(apiService,5,isnetworkavialable,disposable);
        verify(userView).onFailure(throwable);
    }
}


