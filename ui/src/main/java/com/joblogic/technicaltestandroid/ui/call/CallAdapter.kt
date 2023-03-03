package com.joblogic.technicaltestandroid.ui.call

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.joblogic.technicaltestandroid.base.BaseAdapter
import com.joblogic.technicaltestandroid.databinding.ItemDetailBinding
import com.joblogic.technicaltestandroid.domain.models.UserModel
import javax.inject.Inject


class CallAdapter @Inject constructor(
) : BaseAdapter<UserModel>() {

    private val diffCallback = object : DiffUtil.ItemCallback<UserModel>() {
        override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    override val differ = AsyncListDiffer(this, diffCallback)

    override fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ItemDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    inner class CharacterViewHolder(private val binding: ItemDetailBinding) :
        RecyclerView.ViewHolder(binding.root), Binder<UserModel> {
        override fun bind(item: UserModel) {
            binding.apply {
                tvName.text = item.toString()
            }
        }
    }
}
