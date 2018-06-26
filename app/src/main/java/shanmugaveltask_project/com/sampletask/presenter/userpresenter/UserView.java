package shanmugaveltask_project.com.sampletask.presenter.userpresenter;

import java.util.List;

import shanmugaveltask_project.com.sampletask.model.response.UserDetails;

public interface UserView {

    void OnSuccess(List<UserDetails> userData, int totaldata);

    void onFailure(Throwable throwable);

    void showprogressDialog();

    void hideprogressDialog();
}
