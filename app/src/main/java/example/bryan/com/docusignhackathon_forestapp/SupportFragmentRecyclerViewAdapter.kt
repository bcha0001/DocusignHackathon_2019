package example.bryan.com.docusignhackathon_forestapp

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import example.bryan.com.docusignhackathon_forestapp.Model.Petition

fun ViewGroup.inflate(@LayoutRes id: Int): View = LayoutInflater.from(context).inflate(id, this, false)

class SupportFragmentRecyclerViewAdapter(private val click: (String, Int) -> Unit): RecyclerView.Adapter<SupportFragmentRecyclerViewAdapter.PetitionViewHolder>() {
    private val petitionList: ArrayList<Petition> = ArrayList()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PetitionViewHolder = PetitionViewHolder(p0.inflate(p1))

    override fun getItemCount(): Int = petitionList.size

    override fun onBindViewHolder(p0: PetitionViewHolder, p1: Int) {
        p0.bindData(petitionList[p1], click)
    }

    override fun getItemViewType(position: Int): Int = R.layout.fragment_support_vh

    fun addAll(list: ArrayList <Petition>) {
        petitionList.addAll(list)
    }

    class PetitionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val descriptionField = itemView.findViewById<TextView>(R.id.petitionDescriptionVH)

        fun bindData(petition: Petition, click: (String, Int) -> Unit) {
            descriptionField.text = petition.description
            itemView.setOnClickListener {
                if (!petition.supported) {
                    itemView.setBackgroundResource(R.color.colorWhite)
                    click(petition.description, petition.petitionID)
                }
                else {
                    itemView.setBackgroundResource(R.color.colorDarkGreen)
                }
            }
        }
    }
}