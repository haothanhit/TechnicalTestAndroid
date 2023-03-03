package com.joblogic.technicaltestandroid.base

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.VideoView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    protected abstract val differ: AsyncListDiffer<T>

    var list: List<T>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        getViewHolder(parent, viewType)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position in list.indices) {
            (holder as Binder<T>).bind(list[position])
        }
    }

    abstract fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder

    override fun getItemCount(): Int {
        return list.size
    }

    protected var onItemClickListener: ((T) -> Unit)? = null

    protected var onActionItemClickListener: ((T) -> Unit)? = null

    protected var onPlayAudioClickListener: ((T,Int) -> Unit)? = null

    protected var onPlayVideoClickListener: ((T, VideoView, ImageView) -> Unit)? = null

    protected var onRejectItemClickListener: ((T) -> Unit)? = null

    protected var onImageClickListener: ((T) -> Unit)? = null
    fun setImageListener(listener: (T) -> Unit) {
        onImageClickListener = listener
    }

    fun setClickPlayAudioListener(listener: (T,Int) -> Unit) {
        onPlayAudioClickListener = listener
    }

    fun setClickPlayVideoListener(listener: (T, VideoView, ImageView) -> Unit) {
        onPlayVideoClickListener = listener
    }

    protected var onCallClickListener: ((T) -> Unit)? = null

    protected var onVideoCallClickListener: ((T) -> Unit)? = null

    protected var onChatClickListener: ((T) -> Unit)? = null

    protected var onVoiceClickListener: ((T) -> Unit)? = null

    protected var onRemoveClickListener: ((T) -> Unit)? = null


    fun setItemClickListener(listener: (T) -> Unit) {
        onItemClickListener = listener
    }

    fun setActionClickListener(listener: (T) -> Unit) {
        onActionItemClickListener = listener
    }

    fun setActionRejectClickListener(listener: (T) -> Unit) {
        onRejectItemClickListener = listener
    }

    fun setActionCallClickListener(listener: (T) -> Unit) {
        onCallClickListener = listener
    }

    fun setActionVideoCallClickListener(listener: (T) -> Unit) {
        onVideoCallClickListener = listener
    }

    fun setActionVoiceClickListener(listener: (T) -> Unit) {
        onVoiceClickListener = listener
    }

    fun setActionChatClickListener(listener: (T) -> Unit) {
        onChatClickListener = listener
    }

    fun setActionRemoveClickListener(listener: (T) -> Unit) {
        onRemoveClickListener = listener
    }


    interface Binder<in T> {
        fun bind(item: T)
    }
}
