package shanmugaveltask_project.com.sampletask.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import shanmugaveltask_project.com.sampletask.R;
import shanmugaveltask_project.com.sampletask.model.response.UserDetails;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserHolder> {


    private List<UserDetails> usersData = new ArrayList<>();

    private Context context;

    public UserListAdapter(List<UserDetails> userData, Context context) {
        this.usersData= userData;
        this.context = context;
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v = inflater.inflate(R.layout.adapteruser_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        UserHolder vh = new UserHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {


        if(position % 2==0)
        {
            holder.rllayout.setBackgroundColor(Color.parseColor("#F1F8E9"));

        }else
        {

            holder.rllayout.setBackgroundColor(Color.parseColor("#DCEDC8"));

        }

        Glide.with(context).load(usersData.get(position).getAvatar())
                .thumbnail(0.5f)
                .into(holder.ivProfile);

        holder.tvFirstname.setText("First Name :"+usersData.get(position).getFirst_name());
        holder.tvLastname.setText("Last Name :"+usersData.get(position).getLast_name());
    }

    @Override
    public int getItemCount() {
        return usersData.size();
    }

    public class UserHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_profile)
        ImageView ivProfile;
        @BindView(R.id.tv_firstname)
        TextView tvFirstname;
        @BindView(R.id.tv_lastname)
        TextView tvLastname;

        @BindView(R.id.rl_layout)
        RelativeLayout rllayout;

        public UserHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }


    public  void updateListdata(List<UserDetails> userData1)
    {
        this.usersData.addAll(userData1);
        notifyDataSetChanged();
    }
}
