package utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.administrator.myapplication.R;

/**
 * Created by Administrator on 2016/6/27.
 */
public class ListItemDecoration extends RecyclerView.ItemDecoration {

    private Drawable drawable;
    private Context context;
    private int orientation;
    public static int DEFAULT_ORIENTATION=LinearLayoutManager.VERTICAL;


    public ListItemDecoration(Context context, int orientation) {
        this.context = context;

        if(orientation!= LinearLayoutManager.HORIZONTAL && orientation!=LinearLayoutManager.VERTICAL){
            this.orientation=DEFAULT_ORIENTATION;
        }else {
            this.orientation=orientation;
        }

        drawable=context.getResources().getDrawable(R.drawable.divider);
    }


    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if(this.orientation==LinearLayoutManager.HORIZONTAL){
            drawHorizontal(c,parent);
        }else {
            drawVertical(c,parent);
        }
    }




    public void drawHorizontal(Canvas canvas,RecyclerView parent){
        int top=parent.getPaddingTop();
        int bottom=parent.getHeight()-parent.getPaddingBottom();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            int left = child.getRight() + params.rightMargin;
            int right = left + drawable.getIntrinsicHeight();
            drawable.setBounds(left, top, right, bottom);
            drawable.draw(canvas);
        }

    }

    public void drawVertical(Canvas canvas,RecyclerView parent){
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            android.support.v7.widget.RecyclerView v = new android.support.v7.widget.RecyclerView(parent.getContext());
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + drawable.getIntrinsicHeight();
            drawable.setBounds(left, top, right, bottom);
            drawable.draw(canvas);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
    }
}
