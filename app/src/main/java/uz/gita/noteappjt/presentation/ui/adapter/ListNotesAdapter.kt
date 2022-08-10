package uz.gita.noteappjt.presentation.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.noteappjt.R
import uz.gita.noteappjt.data.sources.entity.NoteEntity
import uz.gita.noteappjt.databinding.ItemNoteBinding
import java.text.SimpleDateFormat

class ListNotesAdapter: ListAdapter<NoteEntity, ListNotesAdapter.ViewHolder>(ListNotesDiffUtils) {
    private var onItemLongClickListener: ((NoteEntity) -> Unit)?= null

    object ListNotesDiffUtils: DiffUtil.ItemCallback<NoteEntity>() {
        override fun areItemsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean =
            oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean =
            oldItem == newItem
    }

    inner class ViewHolder(private val binding: ItemNoteBinding): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SimpleDateFormat")
        private val simpleDateFormat: SimpleDateFormat = SimpleDateFormat("hh:mm a\nMMM d, yyyy")

        init {
            binding.clicker.setOnClickListener {
                onItemLongClickListener!!.invoke(getItem(absoluteAdapterPosition))
//                return@setOnLongClickListener true
            }
        }

        fun bind(): NoteEntity = with(binding) {
            getItem(absoluteAdapterPosition).apply {
                val dateTime = simpleDateFormat.format(createdTime)
                textTitle.text = title
                date.text = dateTime
                noteDesc.fromHtml(note)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemNoteBinding.bind(
            LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    fun setOnItemLongClickListener(block: (NoteEntity) -> Unit) {
        onItemLongClickListener = block
    }
}