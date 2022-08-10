package uz.gita.noteappjt.presentation.ui.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.noteappjt.R
import uz.gita.noteappjt.data.sources.entity.TaskEntity
import uz.gita.noteappjt.databinding.TaskItemBinding
import java.text.SimpleDateFormat

class TaskAdapter : ListAdapter<TaskEntity, TaskAdapter.ViewHolder>(TaskDataDiffUtils) {

    private var onItemClickListener: ((TaskEntity) -> Unit)? = null
    private var onItemLongClickListener: ((TaskEntity) -> Unit)?= null

    object TaskDataDiffUtils : DiffUtil.ItemCallback<TaskEntity>() {
        override fun areItemsTheSame(oldItem: TaskEntity, newItem: TaskEntity): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: TaskEntity, newItem: TaskEntity): Boolean =
            oldItem == newItem

    }

    inner class ViewHolder(private val binding: TaskItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SimpleDateFormat")
        private val simpleDateFormat: SimpleDateFormat = SimpleDateFormat("MMM d, yyyy\thh:mm")

        init {
            binding.taskCheck.setOnClickListener {
                onItemClickListener!!.invoke(getItem(absoluteAdapterPosition))
            }
            binding.root.setOnClickListener {
                onItemClickListener!!.invoke(getItem(absoluteAdapterPosition))
            }
            binding.root.setOnLongClickListener {
                onItemLongClickListener!!.invoke(getItem(absoluteAdapterPosition))
                return@setOnLongClickListener true
            }
        }

        @SuppressLint("ResourceType")
        fun bind(): TaskEntity = with(binding) {
            getItem(absoluteAdapterPosition).apply {
                val dateTime = simpleDateFormat.format(createdTime)
                if (selected == 0) {
                    taskText.text = title
                    taskCheck.isChecked = false
                    taskTime.text = dateTime
                    taskText.setBackgroundResource(0)
                } else {
                    taskText.text = title
                    taskText.setTypeface(null, Typeface.ITALIC)
                   taskText.setTextColor(Color.parseColor("#FF979797"))
                    taskText.paintFlags = taskText.paintFlags
                    taskTime.text = dateTime
                    taskCheck.isChecked = true
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        TaskItemBinding.bind(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.task_item, parent, false)
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    fun setOnItemClickListener(block: (TaskEntity) -> Unit) {
        onItemClickListener = block
    }

    fun setOnItemLongClickListener(block: (TaskEntity) -> Unit) {
        onItemLongClickListener = block
    }
}