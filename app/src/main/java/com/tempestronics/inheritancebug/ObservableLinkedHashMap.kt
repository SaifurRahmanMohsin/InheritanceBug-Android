package com.tempestronics.inheritancebug

import android.util.Log
import androidx.databinding.MapChangeRegistry
import androidx.databinding.ObservableMap
import androidx.databinding.ObservableMap.OnMapChangedCallback

class ObservableLinkedHashMap<K, V> : LinkedHashMap<K, V>(), ObservableMap<K, V> {

    companion object {
        const val TAG = "InheritanceBug"
    }

    @Transient
    private var mListeners: MapChangeRegistry? = null

    override fun addOnMapChangedCallback(
        listener: OnMapChangedCallback<out ObservableMap<K, V>, K, V>
    ) {
        if (mListeners == null) {
            mListeners = MapChangeRegistry()
        }
        mListeners!!.add(listener)
    }

    override fun removeOnMapChangedCallback(
        listener: OnMapChangedCallback<out ObservableMap<K, V>, K, V>
    ) {
        if (mListeners != null) {
            mListeners!!.remove(listener)
        }
    }

    override fun clear() {
        val wasEmpty = isEmpty()
        if (!wasEmpty) {
            super.clear()
            notifyChange(null)
        }
    }

    override fun put(k: K, v: V): V? {
        Log.i(TAG, "Adding element: $v")
        val v: V? = super.put(k, v)
        notifyChange(k)
        return v
    }

    private fun notifyChange(key: Any?) {
        if (mListeners != null) {
            mListeners!!.notifyCallbacks(this, 0, key)
        }
    }
}
