package com.tutorials.petersonsproject.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tutorials.petersonsproject.databinding.ExamItemBinding
import com.tutorials.petersonsproject.domain.Exam


class ExamsAdapter(val clickListener: ( exam: Exam) -> (Unit)) :
    ListAdapter<Exam, ExamsAdapter.ExamViewHolder>(ExamsListItemDiffUtilCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExamViewHolder {
        return ExamViewHolder(
            ExamItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    class ExamViewHolder(private val binding: ExamItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setItem(exam: Exam, clickListener: (exam: Exam) -> Unit) {
            binding.title.text = exam.title

            Glide
                .with(binding.root.context)
                .load(exam.image)
                .centerCrop()
                .into(binding.courseImage)

            binding.buttonStart.setOnClickListener {
                clickListener(exam)
            }
            binding.examItem.setOnClickListener {
                clickListener(exam)
            }

        }
    }


    override fun onBindViewHolder(holder: ExamViewHolder, position: Int) {

        holder.setItem(getItem(position), clickListener)
    }

    class ExamsListItemDiffUtilCallback : DiffUtil.ItemCallback<Exam>() {
        override fun areItemsTheSame(oldItem: Exam, newItem: Exam): Boolean =
            oldItem === newItem

        override fun areContentsTheSame(oldItem: Exam, newItem: Exam): Boolean =
            true
    }

}