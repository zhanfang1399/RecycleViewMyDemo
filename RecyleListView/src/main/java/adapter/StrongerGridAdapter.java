package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/28.
 */
public class StrongerGridAdapter extends RecyclerView.Adapter<StrongerGridAdapter.ViewHolder> {


    private ArrayList<String> list;
    private LayoutInflater inflater;
    private List<Integer> heights;
    private int mOrientation;
    private OnItemClickListener onItemClickListener;
    private StaggeredGridLayoutManager sglm;

    public StrongerGridAdapter(ArrayList<String> list, Context context,StaggeredGridLayoutManager sglm) {
        this.list = list;
        this.sglm=sglm;
        mOrientation = StaggeredGridLayoutManager.VERTICAL;
        inflater = LayoutInflater.from(context);
        heights = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            int value=(int) (100 + Math.random() * 300);
            Log.i("随机数",""+value);
            heights.add(value);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder(inflater.inflate(R.layout.stagger_item_layout, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.textView.setText(list.get(position));

        StaggeredGridLayoutManager.LayoutParams lp =(StaggeredGridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
        lp.setFullSpan(false);
        if (mOrientation == StaggeredGridLayoutManager.VERTICAL) {
            lp.height = heights.get(position);
        } else {
            lp.width = heights.get(position);
        }
        holder.itemView.setLayoutParams(lp);


        if (onItemClickListener != null) {
            if (!holder.itemView.hasOnClickListeners()) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = holder.getPosition();
                        onItemClickListener.OnShortClick(v, pos);
                    }
                });

                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        onItemClickListener.OnLongClick(v, holder.getPosition());
                        return true;
                    }
                });
            }

        }

    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public void add(int pos, String value) {
        if (pos > list.size()) {
            pos = list.size();
        }

        if (pos < 0) {
            pos = 0;
        }
        list.add(pos, value);
        heights.add(pos, (int) (100 + Math.random() * 300));
        notifyItemInserted(pos);
        sglm.scrollToPosition(pos);

    }


    public String remove(int position) {
        if (position >= list.size()  || position<0) {
            return null;
        }
        String value=list.remove(position);
        heights.remove(position);
        notifyItemRemoved(position);
        sglm.scrollToPosition(position);
        return value;
    }


    public void setOrientation(int oOrientation) {
        this.mOrientation = oOrientation;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;

    }

    public interface OnItemClickListener {
        void OnShortClick(View view, int pos);

        void OnLongClick(View view, int pos);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textview);
        }
    }
}
