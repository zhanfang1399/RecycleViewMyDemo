package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.myapplication.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/27.
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHodler> {


    private Context context;
    private ArrayList<String> list;
    private LayoutInflater inflater;
    private OnItemClickLisenter itemClickListener;

    public ListAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
        inflater=LayoutInflater.from(context);
    }


    /**
     * 创建viewHolder
     * @param parent  par     *
     * @param viewType  viewType
     * @return view
     */
    @Override
    public ViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHodler viewHodler=new ViewHodler(inflater.inflate(R.layout.item_layout,parent,false));
        return viewHodler;
    }


    /**
     * 绑定viewHolder
     * @param holder holder
     * @param position pos
     */
    @Override
    public void onBindViewHolder(final ViewHodler holder, final int position) {
        holder.textView.setText(list.get(position));
        if(itemClickListener!=null){

            /**
             * 增加判断，如果已经有了点击事件则不在重新设置点击事件
             */
            if(!holder.itemView.hasOnClickListeners()){
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos=holder.getPosition();
                        itemClickListener.OnShortClick(v,pos);
                    }
                });

                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        int pos=holder.getPosition();
                        itemClickListener.OnLongClick(v,pos);
                        return true;
                    }
                });

            }
        }



    }


    /**
     * 按指定位置进行添加
     * @param position pos
     * @param value value
     */
    public void add(int position,String value){
        if(position>list.size()){
            position=list.size();
        }

        if(position<0){
            position=0;
        }

        list.add(position,value);
        /**
         * 使用notifyItemInserted/notifyItemRemoved会有动画效果
         * 而使用notifyDataSetChanged()则没有
         */
        notifyItemInserted(position);
    }


    /**
     * 移除指定位置的数据
     * @param position pos
     * @return 内容
     */
    public String remove(int position){
        if(position>list.size()-1){
            return null;
        }
        String value=list.remove(position);
        notifyItemRemoved(position);
        return  value;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public void setOnItemClickLisenter(OnItemClickLisenter lisenter){
        this.itemClickListener=lisenter;
    }


    public interface OnItemClickLisenter{
        void OnShortClick(View view,int position);
        void OnLongClick(View view,int position);
    }

    class ViewHodler extends RecyclerView.ViewHolder {

        private TextView textView;
        public ViewHodler(View itemView) {
            super(itemView);
            textView=(TextView)itemView.findViewById(R.id.textview);
        }
    }
}
