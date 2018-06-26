package shanmugaveltask_project.com.sampletask.presenter.userpresenter;

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import shanmugaveltask_project.com.sampletask.data.ApiService;
import shanmugaveltask_project.com.sampletask.database.Userdatabase;
import shanmugaveltask_project.com.sampletask.model.response.UserDetails;
import shanmugaveltask_project.com.sampletask.model.response.UserMaster;
import shanmugaveltask_project.com.sampletask.utils.SharedPrefsUtils;
import shanmugaveltask_project.com.sampletask.view.activity.MainActivity;

public class UserPresenterImpl implements UserPresenter {

    private UserView userView;

    public Userdatabase  userdatabase;

    public List<UserDetails> userData = new ArrayList<>();

    public Context context;

    int limit=3,offset=0;
    public UserPresenterImpl(UserView mainActivity, Userdatabase userdatabase, MainActivity activity) {
        this.userView = mainActivity;
        this.userdatabase=userdatabase;
        this.context=activity;
    }


    @Override
    public void getUserdata(ApiService apiService, int Pageno,boolean isnetworkavialable,CompositeDisposable disposable) {

        if (userView != null) {


            userView.showprogressDialog();
         if(isnetworkavialable) {

             disposable.add(
                     apiService.getUserDetails(Pageno)
                             .subscribeOn(Schedulers.io())
                             .observeOn(AndroidSchedulers.mainThread())
                             .subscribeWith(new DisposableSingleObserver<UserMaster>() {
                                 @Override
                                 public void onSuccess(final UserMaster user) {

                                     userView.hideprogressDialog();

                                     if (user != null) {
                                         userView.OnSuccess(user.getData(),user.getTotal());

                                         // UserDetails.id is primary key  so we use user object

                                         insertdata(user, userdatabase);

                                     }

                                 }

                                 @Override
                                 public void onError(Throwable e) {

                                     userView.hideprogressDialog();

                                     userView.onFailure(e);
                                 }
                             }));

         } else
         {

              new GetAllUsers().execute();

         }
        }

    }

    private void insertdata(final UserMaster user, final Userdatabase userdatabase) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                for( int i=0;i<user.getData().size();i++)
                {
                    // UserDetails userDetails1= userdatabase.daoAccess ().checkinsertdata(user.getData().get(i).getId());
                    if(userdatabase.daoAccess ().checkinsertdata(user.getData().get(i).getId())== null )
                        userdatabase.daoAccess ().insertUserdetails(user.getData().get(i));
                }


            }
        }) .start();

    }

//offine get data

    private class GetAllUsers extends AsyncTask<String, Integer, List<UserDetails>>
    {

        @Override
        protected List<UserDetails> doInBackground(String... strings) {


            return userdatabase.daoAccess().fetchalluserdetails(limit,offset);
        }

        @Override
        protected void onPostExecute(List<UserDetails> user) {
            super.onPostExecute(user);
            userView.hideprogressDialog();
            userView.OnSuccess(user, SharedPrefsUtils.getInt("totalvalue",0,context));
            offset=offset+limit;
        }
    }




    @Override
    public void onDestroy() {
        userView = null;
    }
}
