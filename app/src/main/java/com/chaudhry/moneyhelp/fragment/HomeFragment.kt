package com.chaudhry.moneyhelp.fragment

import android.content.Context
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.chaudhry.moneyhelp.R
import com.chaudhry.moneyhelp.adapter.HomeAdapter
import com.chaudhry.moneyhelp.model.HomeCategory.Data
import com.chaudhry.moneyhelp.model.HomeData.Service
import com.chaudhry.moneyhelp.util.RetrofitFactory.api
import com.chaudhry.moneyhelp.util.SessonManager
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList


class HomeFragment : Fragment() {

    lateinit var root : View
    lateinit var rv : RecyclerView
    var list = ArrayList<Service>()
    var listCat = ArrayList<String>()
    lateinit var listCategory : ArrayList<Data>
    private lateinit var sessonManager: SessonManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_home, container, false)
        rv = root.findViewById(R.id.rvItem)
        sessonManager = SessonManager.getInstance(activity)!!
        val spinner = root.findViewById<Spinner>(R.id.mySpinner)
        listCategory = ArrayList()
        list = ArrayList()


        GlobalScope.launch(Dispatchers.Main) {
            val response = api.getHomeCategory()
            try {
                if(response.isSuccessful){
                    val data  = Data(0,0,"Select")
                    listCategory.add(data)

                    listCategory.addAll(response.body()?.data as ArrayList<Data>)

                    for(data in listCategory){
                        listCat.add(data.name)
                    }

                    val adapter = ArrayAdapter(
                        requireContext(),
                        android.R.layout.simple_spinner_item, listCat)
                    spinner.adapter = adapter
                }
            }catch (e : Exception){
                Toast.makeText(activity, ""+e.message, Toast.LENGTH_SHORT).show()
            }
        }
        if (spinner != null) {

            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    val id = listCategory[position].id
                    getData(id)
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }

        }
        return root
    }

    private fun getData(id: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            val response= api.getHomeData("Bearer "+sessonManager.token,id)
            try {
                if(response.isSuccessful){


                        if(response.body()?.status.toString().equals("success")){
                         val data = response.body()?.data
                            list = data?.services as ArrayList<Service>

                            rv.setAdapter(HomeAdapter(list))
                        }else{
                            Toast.makeText(activity, ""+ (response.body()?.message), Toast.LENGTH_SHORT).show()
                        }

                }else{
                    Toast.makeText(activity, ""+response.message(), Toast.LENGTH_SHORT).show()
                }
            }catch (e : Exception){
                Toast.makeText(activity, ""+e.message, Toast.LENGTH_SHORT).show()
            }
        }

    }

}