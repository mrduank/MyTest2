package com.example.dk.mytest.helper;

import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by dk on 2017/7/5.
 */

public class SimpleItemTouchHelper extends ItemTouchHelper {
    /**
     * Creates an ItemTouchHelper that will work with the given Callback.
     * <p>
     * You can attach ItemTouchHelper to a RecyclerView via
     * {@link #attachToRecyclerView(RecyclerView)}. Upon attaching, it will add an item decoration,
     * an onItemTouchListener and a Child attach / detach listener to the RecyclerView.
     *
     * @param callback The Callback which controls the behavior of this touch helper.
     */
    public SimpleItemTouchHelper(SimpleItemTouchCallback.OnItemTouchCallbackListener onItemTouchCallbackListener) {
        super(new SimpleItemTouchCallback(onItemTouchCallbackListener));
    }
}
