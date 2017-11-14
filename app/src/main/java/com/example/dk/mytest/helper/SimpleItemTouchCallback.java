package com.example.dk.mytest.helper;

import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.tsy.sdk.myokhttp.util.LogUtils;

/**
 * Created by dk on 2017/7/5.
 */

public class SimpleItemTouchCallback extends ItemTouchHelper.Callback {
    private OnItemTouchCallbackListener onItemTouchCallbackListener;
    private boolean isCanDrag=false;
    private boolean isCanSwipe=false;
    public SimpleItemTouchCallback(OnItemTouchCallbackListener onItemTouchCallbackListener) {
        this.onItemTouchCallbackListener = onItemTouchCallbackListener;
    }


    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlag=0;
        int swipeFlag=0;
        RecyclerView.LayoutManager layoutManager=recyclerView.getLayoutManager();
        LogUtils.e("getMovementFlags==========", String.valueOf(layoutManager instanceof LinearLayoutManager));
        if (layoutManager instanceof GridLayoutManager){
            // 如果是Grid布局，则不能滑动，只能上下左右拖动
            dragFlag=ItemTouchHelper.DOWN|ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT|ItemTouchHelper.UP;
            swipeFlag=0;
        }else if (layoutManager instanceof LinearLayoutManager){
            // 如果是纵向Linear布局，则能上下拖动，左右滑动
            if (((LinearLayoutManager) layoutManager).getOrientation()==LinearLayoutManager.VERTICAL){
                dragFlag=ItemTouchHelper.DOWN|ItemTouchHelper.UP;
                swipeFlag=ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT;
            }else {
                // 如果是横向Linear布局，则能左右拖动，上下滑动
                dragFlag=ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT;
                swipeFlag=ItemTouchHelper.DOWN|ItemTouchHelper.UP;
            }
        }
        return makeMovementFlags(dragFlag,swipeFlag);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        int fromPosition = viewHolder.getAdapterPosition();//得到拖动ViewHolder的position
        int targetPosition = target.getAdapterPosition();//得到目标ViewHolder的position
        LogUtils.e("fromPosition============", String.valueOf(fromPosition));
        onItemTouchCallbackListener.onMove(fromPosition,targetPosition);
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();
        onItemTouchCallbackListener.onSwiped(position);
    }
    //当长按选中item的时候（拖拽开始的时候）调用,使item变色
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {//不为空闲状态时
            viewHolder.itemView.setBackgroundColor(Color.LTGRAY);
        }
        super.onSelectedChanged(viewHolder, actionState);
    }
    //当手指松开的时候（拖拽完成的时候）调用，使item还原
    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        viewHolder.itemView.setBackgroundColor(0);
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return isCanDrag;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return isCanSwipe;
    }

    public void setCanDrag(boolean canDrag) {
        isCanDrag = canDrag;
    }

    public void setCanSwipe(boolean canSwipe) {
        isCanSwipe = canSwipe;
    }

    public interface OnItemTouchCallbackListener {
        void onSwiped(int adapterPosition);
        void onMove(int fromPosition, int targetPosition);
    }

}
