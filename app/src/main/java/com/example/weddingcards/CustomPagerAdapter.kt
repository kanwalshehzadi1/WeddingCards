import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.weddingcards.R

class CustomPagerAdapter(
    private val items: List<Int>,
    private val onItemClick: (Int) -> Unit // Click listener lambda
) : RecyclerView.Adapter<CustomPagerAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_pager_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imageView.setImageResource(items[position])
        holder.itemView.setOnClickListener {
            onItemClick(position) // Trigger the click listener
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
