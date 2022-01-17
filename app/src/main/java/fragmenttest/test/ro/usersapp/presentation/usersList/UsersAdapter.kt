package fragmenttest.test.ro.usersapp.presentation.usersList

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import fragmenttest.test.ro.usersapp.R
import fragmenttest.test.ro.usersapp.presentation.helpers.inflate
import fragmenttest.test.ro.usersapp.presentation.models.UserModel
import java.text.DateFormat

class UsersAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val list: MutableList<UserModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(parent.inflate(R.layout.list_item_user, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = list[position]
        (holder as ViewHolder).apply {

            if (item.imageUrl.isNullOrBlank().not()) {
                thumbnail.visibility = View.VISIBLE
                userInitialsView.visibility = View.GONE
                userInitialsText.visibility = View.GONE

                Glide.with(thumbnail.context)
                    .load(item.imageUrl)
                    .placeholder(ColorDrawable(Color.RED))
                    .transform(CircleCrop())
                    .into(thumbnail)

            } else {
                Glide.with(thumbnail.context).clear(thumbnail)
                thumbnail.visibility = View.INVISIBLE
                userInitialsView.visibility = View.VISIBLE
                userInitialsText.visibility = View.VISIBLE

                userInitialsText.text = item.name.firstOrNull().toString()
            }

            name.text = item.name
            details.apply {
                text = String.format(
                    context.getString(R.string.user_details),
                    item.age,
                    item.country
                )
            }
            if (item.time != null) {
                time.text = DateFormat
                    .getTimeInstance(DateFormat.SHORT)
                    .format(item.time)
            } else {
                time.text = "##:##"
            }

            attachment.visibility = if (item.shouldShowAttachment) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val thumbnail: ImageView = view.findViewById(R.id.thumbnail)
        val userInitialsView: View = view.findViewById(R.id.userInitialsView)
        val userInitialsText: TextView = view.findViewById(R.id.userInitialsText)
        val name: TextView = view.findViewById(R.id.name)
        val details: TextView = view.findViewById(R.id.details)
        val time: TextView = view.findViewById(R.id.time)
        val attachment: ImageView = view.findViewById(R.id.attachment)
    }

    override fun getItemCount(): Int {
        return list.size
    }


    fun addItems(list: List<UserModel>) {
        if (list.isNotEmpty()) {
            val currentMaxPosition = this.list.size
            this.list.addAll(list)
            notifyItemRangeInserted(currentMaxPosition, this.list.size)
        }
    }

    fun clearData() {
        val currentMaxPosition = this.list.size
        this.list.clear()
        notifyItemRangeRemoved(0, currentMaxPosition)
    }
}