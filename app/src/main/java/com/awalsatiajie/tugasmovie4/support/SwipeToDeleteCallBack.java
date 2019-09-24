package com.awalsatiajie.tugasmovie4.support;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.awalsatiajie.tugasmovie4.R;

public class SwipeToDeleteCallBack extends ItemTouchHelper.Callback {
    Context context;
    private Paint paint;
    private ColorDrawable background;
    private int backgroundColor;
    private Drawable deleteDrawable;
    private int intrinsicWidth;
    private int intrinsicHeight;

    public SwipeToDeleteCallBack(Context context){
        this.context = context;
        background = new ColorDrawable();
        backgroundColor = ContextCompat.getColor(context, R.color.colorPrimaryDark);
        paint = new Paint();
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.ADD));
        deleteDrawable = ContextCompat.getDrawable(context, R.drawable.ic_delete_sweep_black_24dp);
        intrinsicHeight = deleteDrawable.getIntrinsicHeight();
        intrinsicWidth = deleteDrawable.getIntrinsicWidth();
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(0, ItemTouchHelper.LEFT);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public void onChildDraw(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder,
                            float dX, float dY, int actionState, boolean isCurrentActive){
        super.onChildDraw(canvas, recyclerView, viewHolder, dX, dY, actionState, isCurrentActive);

        View itemView = viewHolder.itemView;
        int itemHeight = itemView.getHeight();

        boolean isCanceled = dX == 0 && !isCurrentActive;

        if(isCanceled){
            clearCanvas(canvas, itemView.getRight() + dX, (float)itemView.getTop(), (float)itemView.getRight(), (float) itemView.getBottom());
            super.onChildDraw(canvas, recyclerView, viewHolder, dX, dY, actionState, isCurrentActive);
            return;
        }

        background.setColor(backgroundColor);
        background.setBounds(itemView.getRight() + (int) dX, itemView.getTop(), itemView.getRight(), itemView.getBottom());
        background.draw(canvas);

        int deleteIconTop = itemView.getTop()  + (itemHeight - intrinsicHeight) / 2;
        int deleteIconMargin = (itemHeight - intrinsicHeight) / 2;
        int deleleteIconLeft = itemView.getRight() - intrinsicWidth - deleteIconMargin;
        int deleteIconRight = itemView.getRight() - deleteIconMargin;
        int deleteIconBottom = deleteIconTop + intrinsicHeight;

        deleteDrawable.setBounds(deleleteIconLeft, deleteIconTop, deleteIconRight, deleteIconBottom);
        deleteDrawable.draw(canvas);

        super.onChildDraw(canvas, recyclerView, viewHolder, dX, dY, actionState, isCurrentActive);

    }

    private void clearCanvas(Canvas c, Float left, Float top, Float right, Float bottom){
        c.drawRect(left,top,right,bottom,paint);
    }

    @Override
    public  float getSwipeThreshold(@NonNull RecyclerView.ViewHolder viewHolder){
        return 0.7f;
    }
}
