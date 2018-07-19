package drinsta.chaitanya.applocker.ui.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import drinsta.chaitanya.applocker.R;
import drinsta.chaitanya.applocker.data.db.model.AppItem;

/**
 * Created by Chaitanya Aggarwal  on 14/7/18.
 */
public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private LayoutInflater inflater;
    private List<AppItem> installedAppsList;
    private Context ctx;



    public Adapter(Context ctx, List<AppItem> installedAppsList){
        this.ctx=ctx;
        inflater = LayoutInflater.from(ctx);
        this.installedAppsList = installedAppsList;
    }

    public void removeItem(int position) {
        installedAppsList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, installedAppsList.size());
    }
    public void restoreItem(AppItem appItem, int position) {
        installedAppsList.add(position, appItem);
        // notify item added by position
        notifyItemInserted(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.rv_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
holder.app_icon.setImageBitmap(installedAppsList.get(position).getBitmap());
holder.list_app_name.setText(installedAppsList.get(position).getLabel());

    }

    @Override
    public int getItemCount() {
        return installedAppsList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView list_app_name;
        private ImageView app_icon;


        public MyViewHolder(View itemView) {
            super(itemView);

            list_app_name = (TextView) itemView.findViewById(R.id.list_app_name);
            app_icon= (ImageView) itemView.findViewById(R.id.app_icon);
        }

    }
}

