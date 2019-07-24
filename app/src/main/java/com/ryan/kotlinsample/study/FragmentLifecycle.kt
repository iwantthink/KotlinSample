package com.ryan.kotlinsample.study

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.ryan.kotlinsample.R
import com.ryan.kotlinsample.pt
import kotlinx.android.synthetic.main.fragment_lifecycle.*


class FragmentLifecycle : Fragment() {

    val TAG = "TEST-" + FragmentLifecycle::class.java.simpleName

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        pt("onAttach", TAG)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pt("onCreate", TAG)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        pt("onCreateView", TAG)
        val view = inflater.inflate(R.layout.fragment_lifecycle, container)
        val btnFragment = view.findViewById<Button>(R.id.btn_fragment)
        btnFragment.text = "测试Fragment生命周期"
        return view
    }

    override fun onStart() {
        super.onStart()
        pt("onStart", TAG)
    }

    override fun onResume() {
        super.onResume()
        pt("onResume", TAG)
    }

    override fun onPause() {
        super.onPause()
        pt("onPause", TAG)
    }

    override fun onStop() {
        super.onStop()
        pt("onStop", TAG)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        pt("onDestroyView", TAG)
    }

    override fun onDestroy() {
        super.onDestroy()
        pt("onDestroy", TAG)
    }

}