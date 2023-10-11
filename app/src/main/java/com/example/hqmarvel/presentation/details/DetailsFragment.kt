package com.example.hqmarvel.presentation.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.navigation.navGraphViewModels
import com.example.hqmarvel.presentation.listhq.HQViewModel
import com.example.hqmarvel.R
import com.example.hqmarvel.databinding.FragmentDetailsBinding
import com.example.hqmarvel.viewBinder.bindSrcUrl
import com.example.hqmarvel.viewBinder.imageList

class DetailsFragment : Fragment() {
    private val viewModel by navGraphViewModels<HQViewModel>(R.id.nav_graph) { defaultViewModelProviderFactory }
    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)

        observer()
        return binding.root
    }

    private fun observer(){
        viewModel.detailsLiveData.observe(viewLifecycleOwner) {

            with(binding){
                title.text = it.title
                content.text = it.getContent()
                imageView2.bindSrcUrl(it.getImageUrl() ?: "")
                carousel.imageList(it.getCarouselImages() )
                carousel.visibility = if(it.getCarouselImages()?.isEmpty() == true) GONE else VISIBLE
            }

        }
    }

}