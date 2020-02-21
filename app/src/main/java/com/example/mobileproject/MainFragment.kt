package com.example.mobileproject

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_main.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [MainFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // View Model instance
        search.setOnClickListener {  }
        connect.setOnClickListener {
            view!!.findNavController().navigate(R.id.action_mainFragment_to_authoFragment,null) }
        create.setOnClickListener {
            view!!.findNavController().navigate(R.id.action_mainFragment_to_new_accountFragment2,null) }
      /***  val cityModel = ViewModelProviders.of(activity!!).get(CityModel::class.java)
        // If the list of cities is null, load the list from DB
        if (cityModel.cities==null) {
            cityModel.loadData(activity!!)
        }
        else {
            // After the rotation of the screen, use cities of the ViewModel instance
            listcities.adapter = CityAdapter(activity!!, cityModel.cities!!)
        }
        listcities.setOnItemClickListener { adapterView, view, i, l ->
            val city = (adapterView.getItemAtPosition(i) as City)
            var bundle = bundleOf("id" to city.idCity)
            view.findNavController().navigate(R.id.action_mainFragment_to_detailFragment,bundle)***/
        }
}