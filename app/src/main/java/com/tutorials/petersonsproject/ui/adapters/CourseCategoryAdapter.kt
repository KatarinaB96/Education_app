package com.tutorials.petersonsproject.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.*
import com.tutorials.petersonsproject.databinding.TopicItemBinding
import com.tutorials.petersonsproject.domain.CourseCategory
import com.tutorials.petersonsproject.domain.Exam
import com.tutorials.petersonsproject.ui.screenmodels.CourseScreenModel


class CourseCategoryAdapter(val clickListener: (exam: Exam) -> (Unit)) :

    ListAdapter<CourseScreenModel, CourseCategoryAdapter.TopicViewHolder>(
        TopicsListItemDiffUtilCallback()
    ) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicViewHolder {
        return TopicViewHolder(
            TopicItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    class TopicViewHolder(private val binding: TopicItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setItem(topic: CourseScreenModel, clickListener: ( exam: Exam) -> Unit) {
            val childMembersAdapter = ExamsAdapter(clickListener)

            binding.titleOfCategory.text = topic.title

            binding.courseRecyclerView.apply {

                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

                adapter = childMembersAdapter
            }

            childMembersAdapter.submitList(topic.exam.sortedBy { it.title })
        }
    }


    override fun onBindViewHolder(holder: TopicViewHolder, position: Int) {
        holder.setItem(getItem(position), clickListener)
    }

    class TopicsListItemDiffUtilCallback : DiffUtil.ItemCallback<CourseScreenModel>() {
        override fun areItemsTheSame(
            oldItem: CourseScreenModel,
            newItem: CourseScreenModel
        ): Boolean =
            oldItem === newItem

        override fun areContentsTheSame(
            oldItem: CourseScreenModel,
            newItem: CourseScreenModel
        ): Boolean =
            true
    }

}