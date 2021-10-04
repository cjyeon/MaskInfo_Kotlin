import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mask_info_kotlin.model.Store
import com.example.mask_info_kotlin.R
import java.util.ArrayList

class StoreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var tv_name: TextView = itemView.findViewById(R.id.tv_name)
    var tv_addr: TextView = itemView.findViewById(R.id.tv_addr)
    var tv_distance: TextView = itemView.findViewById(R.id.tv_distance)
    var tv_remain: TextView = itemView.findViewById(R.id.tv_remain)
    var tv_count: TextView = itemView.findViewById(R.id.tv_count)
}

class StoreAdapter : RecyclerView.Adapter<StoreViewHolder>() {
    private var mItems: List<Store> = ArrayList()

    //UI 갱신
    fun updateItems(items: List<Store>) {
        mItems = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_store, parent, false)
        return StoreViewHolder(view)
    }

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        val store = mItems[position]
        holder.tv_name.text = store.name
        holder.tv_addr.text = store.addr
        holder.tv_distance.text = String().format("%0.2fkm", store.distance)

        var remainStat = "충분"
        var countStat = "100개 이상"
        var color = Color.GREEN
        when (store.remain_stat) {
            //block형식으로. break 필요없음
            "plenty" -> {
                remainStat = "충분"
                countStat = "100개 이상"
                color = Color.GREEN
            }
            "some" -> {
                remainStat = "여유"
                countStat = "30개 이상"
                color = Color.BLUE
            }
            "few" -> {
                remainStat = "매진 임박"
                countStat = "2개 이상"
                color = Color.RED
            }
            "empty" -> {
                remainStat = "재고 없음"
                countStat = "1개 이하"
                color = Color.GRAY
            }
            else -> {
            }
        }
        holder.tv_remain.text = remainStat
        holder.tv_count.text = countStat
        holder.tv_remain.setTextColor(color)
        holder.tv_count.setTextColor(color)
    }

    override fun getItemCount(): Int = mItems.size
}