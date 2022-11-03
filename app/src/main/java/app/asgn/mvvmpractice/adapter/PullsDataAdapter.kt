package app.asgn.mvvmpractice.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import app.asgn.mvvmpractice.R
import app.asgn.mvvmpractice.databinding.PullsItemRvLayoutBinding
import app.asgn.mvvmpractice.models.PullsDataClassItem
import app.asgn.mvvmpractice.utils.inputFormat
import app.asgn.mvvmpractice.utils.outputFormat
import java.util.*

class PullsDataAdapter(val onItemClicked: (PullsDataClassItem, Int) -> Unit) : RecyclerView.Adapter<PullsDataAdapter.PullsViewHolder>() {

    private var pullDataa: ArrayList<PullsDataClassItem>? = null

    inner class PullsViewHolder(pullsItemRvLayoutBinding: PullsItemRvLayoutBinding) : RecyclerView.ViewHolder(pullsItemRvLayoutBinding.root){
        val pullsListItemBinding: PullsItemRvLayoutBinding
        init {
            pullsListItemBinding = pullsItemRvLayoutBinding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullsViewHolder {
        val pullsRvLayoutBinding : PullsItemRvLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
        R.layout.pulls_item_rv_layout, parent, false)
        return PullsViewHolder(pullsRvLayoutBinding)
    }

    override fun onBindViewHolder(holder: PullsViewHolder, position: Int) {
        val currentItem: PullsDataClassItem = pullDataa!![position]
        holder.pullsListItemBinding.pullsItem = currentItem
        val parsedDate: Date = inputFormat.parse(currentItem.closed_at) as Date
        holder.pullsListItemBinding.tv3.text = outputFormat.format(parsedDate)

        holder.itemView.setOnClickListener {
            onItemClicked(currentItem, position)
        }
    }

    fun setPullItemList(itemList : ArrayList<PullsDataClassItem>?) {
        pullDataa = itemList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return if(!pullDataa.isNullOrEmpty()){
            pullDataa?.size?:0
        } else {
            0
        }
    }

}