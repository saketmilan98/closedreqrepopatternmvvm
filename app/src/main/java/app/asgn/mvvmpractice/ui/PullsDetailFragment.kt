package app.asgn.mvvmpractice.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import app.asgn.mvvmpractice.R
import app.asgn.mvvmpractice.databinding.FragmentPullsDetailBinding
import app.asgn.mvvmpractice.models.PullsDataClassItem
import app.asgn.mvvmpractice.viewmodels.MainViewModel
import app.asgn.mvvmpractice.utils.inputFormat
import app.asgn.mvvmpractice.utils.loadImage
import app.asgn.mvvmpractice.utils.outputFormat
import java.util.*


class PullsDetailFragment : Fragment() {

    val viewModel: MainViewModel by activityViewModels()
    lateinit var binding: FragmentPullsDetailBinding
    lateinit var currentItem : PullsDataClassItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPullsDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbarLayout.toolbarTitleTv.text = getString(R.string.pull_request_info)
        binding.toolbarLayout.toolbarBackIv.setOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.pullsItem = viewModel.getCurrentPullItem()
        currentItem = viewModel.getCurrentPullItem()
        binding.iv3.loadImage(currentItem.user.avatar_url)
        binding.tv9.text = "Created: ${outputFormat.format(inputFormat.parse(currentItem.created_at) as Date)}"
        binding.tv10.text = "Closed: ${outputFormat.format(inputFormat.parse(currentItem.closed_at) as Date)}"
        binding.btn1.setOnClickListener {
            val uri: Uri = Uri.parse(currentItem.head.repo.html_url)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
        binding.btn2.setOnClickListener {
            val uri: Uri = Uri.parse(currentItem.html_url)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
    }

}