package com.lab43.sligamer.shadesenhanced

import android.app.Activity
import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView

/**
 * Created by Justin Freres on 3/10/2018.
 * Class for List Fragment
 * Lab 4-6 Shades II Application
 * Plugin Support with kotlin_version = '1.2.21'
 */
class ListFragment : Fragment() {


    private lateinit var listener: OnItemSelectedListener
    private lateinit var information: String
    private lateinit var viewReturn: View

    private lateinit var btnPlum: Button
    private lateinit var btnBlue: Button
    private lateinit var btnGold: Button

    // ENHANCED ADDITIONS
    private lateinit var dummyData: DummyData
    private lateinit var shadelisting: List<String> // MASTER LIST
    private lateinit var shadeNameDetail: List<String> // DETAIL LIST
    private lateinit var mShadeAdapter: ArrayAdapter<String>
    private lateinit var listView: ListView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {


        // ENHANCED ADDITIONS
        dummyData = DummyData()

        shadelisting = dummyData.shade_names.toList()
        shadeNameDetail = dummyData.shade_detail.toList()

        mShadeAdapter = ArrayAdapter(activity, R.layout.list_item_shade,
                R.id.list_item_shade_textview, shadelisting)


        viewReturn = inflater.inflate(
                R.layout.list_fragment,
                container,
                false
        )

        listView = viewReturn.findViewById(R.id.listview_shades)
        listView.adapter = mShadeAdapter
        listView.setOnItemClickListener { parent: AdapterView<*>?, view: View?, position: Int, id: Long ->
            val shadeIndexString = mShadeAdapter.getItem(position)
            information = shadeIndexString + "\n\n\n" + shadeNameDetail[position]
            updateDetail()
        }

/*        btnPlum = viewReturn.findViewById(R.id.btnPlum)
        btnPlum.setOnClickListener { v -> setShadeDescription(v) }

        btnBlue = viewReturn.findViewById(R.id.btnBlue)
        btnBlue.setOnClickListener { v -> setShadeDescription(v) }

        btnGold = viewReturn.findViewById(R.id.btnGold)
        btnGold.setOnClickListener { v -> setShadeDescription(v) }*/



        return viewReturn
    }


    fun setShadeDescription(v: View)
    {
        var description = v!!.contentDescription
        information = description as String
        updateDetail()
    }


    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        if(activity is OnItemSelectedListener )
        {
            listener = activity
        }
        else{
            throw ClassCastException(activity.toString() + " must implement ListFragment.OnItemSelectedListener")
        }
    }

    private fun updateDetail() {
       listener.onShadeItemSelected(information)
    }

    interface OnItemSelectedListener {
        fun onShadeItemSelected(link: String)
    }

}