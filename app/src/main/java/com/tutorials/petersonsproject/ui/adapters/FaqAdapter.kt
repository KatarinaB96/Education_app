package com.tutorials.petersonsproject.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tutorials.petersonsproject.databinding.FaqItemBinding
import com.tutorials.petersonsproject.ui.screenmodels.FaqScreenModel


class FaqAdapter() :
    ListAdapter<FaqScreenModel, FaqAdapter.FaqViewHolder>(ExamsListItemDiffUtilCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FaqViewHolder {
        return FaqViewHolder(
            FaqItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    class FaqViewHolder(private val binding: FaqItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setItem(faq: FaqScreenModel) {


            binding.questionTextView.text = faq.question

            binding.answerTextView.text = HtmlCompat.fromHtml(faq.answer,0)
        }
    }


    override fun onBindViewHolder(holder: FaqViewHolder, position: Int) {

        holder.setItem(getItem(position))
    }

    class ExamsListItemDiffUtilCallback : DiffUtil.ItemCallback<FaqScreenModel>() {
        override fun areItemsTheSame(oldItem: FaqScreenModel, newItem: FaqScreenModel): Boolean =
            oldItem === newItem

        override fun areContentsTheSame(oldItem: FaqScreenModel, newItem: FaqScreenModel): Boolean =
            true
    }

}