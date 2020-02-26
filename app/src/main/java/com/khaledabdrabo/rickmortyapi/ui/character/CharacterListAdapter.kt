package com.khaledabdrabo.rickmortyapi.ui.character

import android.os.Build
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.layout_custom_dialog.view.*
import com.khaledabdrabo.rickmortyapi.R
import com.khaledabdrabo.rickmortyapi.databinding.CharacterItemBinding
import com.khaledabdrabo.rickmortyapi.model.Character


class CharacterListAdapter: RecyclerView.Adapter<CharacterListAdapter.ViewHolder>() {
    private lateinit var characterList:List<Character>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: CharacterItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.character_item, parent, false)
        return ViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(characterList[position])
    }

    override fun getItemCount(): Int {
        return if(::characterList.isInitialized) characterList.size else 0
    }

    fun updateCharacterList(characterList:List<Character>){
        this.characterList = characterList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: CharacterItemBinding):RecyclerView.ViewHolder(binding.root){
        private val viewModel = CharacterViewModel()

        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(character: Character) {
            viewModel.bind(character)
            binding.viewModel = viewModel

            binding.root.setOnClickListener {

                val mDialogView = LayoutInflater.from(binding.root.context).inflate(R.layout.layout_custom_dialog, null)
                val mBuilder = MaterialAlertDialogBuilder(binding.root.context)
                    .setView(mDialogView)
                mDialogView.txt_character_name_dialog.text = character.name

                val characterTitle = "STATUS\nSPECIES\nGENDER\nORIGIN\nLOCATION"

                val characterDetails = "${character.status}\n" +
                        "${character.species}\n" +
                        "${character.gender}\n" +
                        "${character.origin.name}\n" +
                        "${character.location.name}"


                val date = TextUtils.substring(character.created,0, 4)
                mDialogView.txt_character_brief_dialog.text = "id: ${character.id} - created in $date"
                mDialogView.txt_character_titles_dialog.text = characterTitle
                mDialogView.txt_character_titles_details_dialog.text = characterDetails
                Glide.with(mDialogView.img_character_dialog.context).load(character.image).into(mDialogView.img_character_dialog)
                val mAlertDialog = mBuilder.show()
                mDialogView.btn_dismiss_dialog.setOnClickListener {
                    mAlertDialog.dismiss()
                }
            }
        }

    }
}