package com.tutorials.petersonsproject.ui.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.*
import com.tutorials.petersonsproject.databinding.TopicContentItemBinding
import com.tutorials.petersonsproject.domain.hierarchy.TopicContent
import com.tutorials.petersonsproject.ui.screenmodels.ModuleContentScreenModel
import com.tutorials.petersonsproject.ui.screenmodels.TopicScreenModel

class TopicContentAdapter(val clickListener: (topic: TopicScreenModel) -> Unit) :
    ListAdapter<TopicScreenModel, TopicContentAdapter.TopicViewHolder>(
        CourseListItemDiffUtilCallBack()
    ) {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TopicViewHolder {
        return TopicViewHolder(
            TopicContentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TopicViewHolder, position: Int) {
        holder.setItem(getItem(position), clickListener)
    }


    class TopicViewHolder(private val binding: TopicContentItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setItem(
            topicContent: TopicScreenModel,
            clickListener: (topic: TopicScreenModel) -> Unit
        ) {
            binding.topicTitle.text = topicContent.title

            binding.topic.setOnClickListener {
                clickListener(topicContent)
            }
        }
    }

    class CourseListItemDiffUtilCallBack : DiffUtil.ItemCallback<TopicScreenModel>() {
        override fun areItemsTheSame(
            oldItem: TopicScreenModel,
            newItem: TopicScreenModel
        ): Boolean =
            oldItem === newItem

        override fun areContentsTheSame(
            oldItem: TopicScreenModel,
            newItem: TopicScreenModel
        ): Boolean =
            true
    }
}


