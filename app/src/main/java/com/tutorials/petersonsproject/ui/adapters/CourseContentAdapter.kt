package com.tutorials.petersonsproject.ui.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tutorials.petersonsproject.databinding.ContentItemBinding
import com.tutorials.petersonsproject.ui.screenmodels.ModuleContentScreenModel

class CourseContentAdapter(val clickListener: (moduleScreen:ModuleContentScreenModel) -> Unit) :
    ListAdapter<ModuleContentScreenModel, CourseContentAdapter.CourseViewHolder>(CourseListItemDiffUtilCallBack()) {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CourseViewHolder {
        return CourseViewHolder(
            ContentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.setItem(getItem(position), clickListener)
    }


    class CourseViewHolder(private val binding: ContentItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setItem(moduleContentScreenModel: ModuleContentScreenModel, clickListener: (moduleScreen:ModuleContentScreenModel) -> Unit) {
            binding.title.text = moduleContentScreenModel.title

            binding.course.setOnClickListener {
                clickListener(moduleContentScreenModel)
            }
        }
    }

    class CourseListItemDiffUtilCallBack : DiffUtil.ItemCallback<ModuleContentScreenModel>() {
        override fun areItemsTheSame(oldItem: ModuleContentScreenModel, newItem: ModuleContentScreenModel): Boolean =
            oldItem === newItem

        override fun areContentsTheSame(oldItem: ModuleContentScreenModel, newItem: ModuleContentScreenModel): Boolean = true
    }
}


