package shanmugaveltask_project.com.sampletask.view.activity;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.gmail.samehadar.iosdialog.IOSDialog;

import shanmugaveltask_project.com.sampletask.R;
import shanmugaveltask_project.com.sampletask.database.Userdatabase;
import shanmugaveltask_project.com.sampletask.utils.Const;

public class BaseActivity extends AppCompatActivity {
    private static final String TAG = BaseActivity.class.getSimpleName();

    private static IOSDialog iosDialog;


   /* init database name*/

    private static final String DATABASE_NAME = Const.DATABASENAME;

    /*create databse Object*/
    public Userdatabase  userdatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);


    }


    public void initProgressDialog(Context context) {
        iosDialog = new IOSDialog.Builder(context)
                .setTitle("Loading...")
                .setTitleColorRes(R.color.gray)
                .setCancelable(false)
                .build();
    }


    public  void initdatabase(Context context)
    {
        userdatabase = Room.databaseBuilder(context, Userdatabase.class, DATABASE_NAME)
                .build();
    }


    public static void showProgress() {
        if (iosDialog != null && !iosDialog.isShowing()) {
            iosDialog.show();
        }
    }

    public static void hideProgress() {
        if (iosDialog != null && iosDialog.isShowing()) {
            iosDialog.dismiss();
        }
    }

    public static void hideKeyboard(Activity activity) {
        try {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            View view = activity.getCurrentFocus();
            if (view == null) {
                view = new View(activity);
            }
            assert imm != null;
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





}
