package com.wildsmith.recyclerview.dynamic;

import android.support.annotation.Nullable;
import android.view.View;

import com.wildsmith.recyclerview.RecyclerViewHolder;

import java.lang.ref.WeakReference;

public class DynamicRecyclerViewHolder<M extends DynamicRecyclerModel, V extends DynamicRecyclerModelView> extends
    RecyclerViewHolder<M, V> {

    private WeakReference<DynamicRecyclerModelListener> listenerWeakReference;

    public DynamicRecyclerViewHolder(View view) {
        super(view);
    }

    public DynamicRecyclerViewHolder(View view, DynamicRecyclerModelListener listener) {
        super(view);
        this.listenerWeakReference = new WeakReference<>(listener);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void populateView(M recyclerObject, int position, int size) {
        if (container == null) {
            return;
        }

        container.setListener(getListener());
        container.populateView(recyclerObject, position, size);
    }

    @Nullable
    public DynamicRecyclerModelListener getListener() {
        if (listenerWeakReference == null) {
            return null;
        }

        return listenerWeakReference.get();
    }
}