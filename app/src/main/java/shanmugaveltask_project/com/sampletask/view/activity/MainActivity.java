package shanmugaveltask_project.com.sampletask.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.TextView;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;
import shanmugaveltask_project.com.sampletask.R;
import shanmugaveltask_project.com.sampletask.data.ApiClient;
import shanmugaveltask_project.com.sampletask.data.ApiService;
import shanmugaveltask_project.com.sampletask.model.response.UserDetails;
import shanmugaveltask_project.com.sampletask.presenter.userpresenter.UserPresenter;
import shanmugaveltask_project.com.sampletask.presenter.userpresenter.UserPresenterImpl;
import shanmugaveltask_project.com.sampletask.presenter.userpresenter.UserView;
import shanmugaveltask_project.com.sampletask.utils.Networkavailable;
import shanmugaveltask_project.com.sampletask.utils.SharedPrefsUtils;
import shanmugaveltask_project.com.sampletask.utils.ToastUtil;
import shanmugaveltask_project.com.sampletask.view.adapter.UserListAdapter;


public class MainActivity extends BaseActivity implements UserView {

    private static final String TAG = MainActivity.class.getSimpleName();

    //inti reccyclerview
    @BindView(R.id.rv_userdata)
    RecyclerView rvUserdata;

    @BindView(R.id.tv_no_data)
    TextView tvNoData;

    //api service object
    private ApiService apiService;

    //presenter object
    private UserPresenter presenter;

    //adpter class object
    private UserListAdapter userListAdapter;


    // recycler view set data using array list
    public List<UserDetails> userData = new ArrayList<>();

    LinearLayoutManager mLayoutManager;


    // first time page call 1
    int currentPage = 1, total_pages, per_page_data;

    // api loading or not
    private boolean isloading = true;

    //rx java object
    private CompositeDisposable disposable = new CompositeDisposable();


   public SharedPrefsUtils sharedPrefsUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initProgressDialog(this);
        initdatabase(this);
        sharedPrefsUtils.getSharedPrefs(this);

        apiService = ApiClient.getClient(getApplicationContext()).create(ApiService.class);
        presenter = new UserPresenterImpl(this, userdatabase,this);

        setDataAdatper(userData);


        /*first page api calling*/

        apiCalling(currentPage);


        //pagenation recyclerview on scroll

        rvUserdata.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int lastvisibleitemposition = mLayoutManager.findLastVisibleItemPosition();

                if (lastvisibleitemposition == userListAdapter.getItemCount() - 1) {

                    if (!isloading && userListAdapter.getItemCount() != sharedPrefsUtils.getInt("totalvalue",0,MainActivity.this)) {
                        currentPage++;
                        apiCalling(currentPage);
                        isloading = true;
                    }


                }
                }


        });


    }

    private void apiCalling(int page) {

        showProgress();
        presenter.getUserdata(apiService, page, Networkavailable.isNetworkAvailable(this), disposable);


    }

    private void setDataAdatper(List<UserDetails> userData) {

        // use a linear layout manager
        mLayoutManager =
                new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);

        rvUserdata.setLayoutManager(mLayoutManager);
        // adding inbuilt divider line
        //  rvUserdata.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        rvUserdata.setHasFixedSize(true);
        userListAdapter = new UserListAdapter(userData, this);
        rvUserdata.setAdapter(userListAdapter);


    }


    /* api false respone*/
    private void showError(Throwable e) {
        String message = "";
        try {
            if (e instanceof IOException) {
                message = "No internet connection!";
            } else if (e instanceof HttpException) {
                HttpException error = (HttpException) e;
                String errorBody = error.response().errorBody().string();
                JSONObject jObj = new JSONObject(errorBody);

                message = jObj.getString("error");
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (JSONException e1) {
            e1.printStackTrace();
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        if (TextUtils.isEmpty(message)) {
            message = "Unknown error occurred! Check LogCat.";
        }

        ToastUtil.showToast(this, message.toString());
    }

    @Override
    public void OnSuccess(List<UserDetails> userData, int totaldata1) {

        if (userData.size() > 0 && userData != null) {


            // updateListdata method used add date old and new
            userListAdapter.updateListdata(userData);

            //totaldata = totaldata1;

            sharedPrefsUtils.putInt("totalvalue",totaldata1,this);

            //Log.d("totaldata", "" + totaldata);
            isloading = false;


        }

    }


    @Override
    public void onFailure(Throwable throwable) {
        isloading = false;
        showError(throwable);
    }

    @Override
    public void showprogressDialog() {
        showProgress();
    }

    @Override
    public void hideprogressDialog() {
        hideProgress();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
