package com.tutorials.petersonsproject.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL
import com.tutorials.petersonsproject.R
import com.tutorials.petersonsproject.databinding.ExamItemBinding
import com.tutorials.petersonsproject.databinding.OverviewItemBindingBinding
import com.tutorials.petersonsproject.domain.Exam
import com.tutorials.petersonsproject.domain.Overview
import com.tutorials.petersonsproject.ui.screenmodels.OverViewScreenModel


class OverviewAdapter() :
    ListAdapter<OverViewScreenModel, OverviewAdapter.ExamViewHolder>(ExamsListItemDiffUtilCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExamViewHolder {
        return ExamViewHolder(
            OverviewItemBindingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    class ExamViewHolder(private val binding: OverviewItemBindingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setItem(overview: OverViewScreenModel) {
           binding.overviewDescription.text = overview.description

            Glide
                .with(binding.root.context)
                .load(overview.image)
                .centerCrop()
                .into(binding.overviewIcon)
        }
    }


    override fun onBindViewHolder(holder: ExamViewHolder, position: Int) {

        holder.setItem(getItem(position))
    }

    class ExamsListItemDiffUtilCallback : DiffUtil.ItemCallback<OverViewScreenModel>() {
        override fun areItemsTheSame(oldItem: OverViewScreenModel, newItem: OverViewScreenModel): Boolean =
            oldItem === newItem

        override fun areContentsTheSame(oldItem: OverViewScreenModel, newItem: OverViewScreenModel): Boolean =
            true
    }

}