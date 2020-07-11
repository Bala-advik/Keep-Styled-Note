package com.example.android.todonote

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var parentLinearLayout: LinearLayout? = null
    private var parentLinearLayoutDel: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        parentLinearLayout =
            findViewById<View>(R.id.parent_linear_layout) as LinearLayout
        parentLinearLayoutDel =
            findViewById<View>(R.id.parent_linear_layout_deleted) as LinearLayout
    }

    fun onAddField(v: View) {
        val inflater =
            getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rowView: View = inflater.inflate(R.layout.fields, null)
        val edt = rowView.findViewById<View>(R.id.edtText) as EditText
        parentLinearLayout!!.addView(
            rowView,
            parentLinearLayout!!.childCount - 1
        ) // Add the new row before the add field button.
        val scrollview =
            findViewById<View>(R.id.scrollMain) as ScrollView
        scrollview.post {
            scrollview.fullScroll(ScrollView.FOCUS_DOWN)
            edt.requestFocus()
        }
    }

    fun onChkField(v: View, `val`: String) {
        val inflater =
            getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rowView: View = inflater.inflate(R.layout.fields_del, null)
        // Add the new row before the add field button.
        val edt = rowView.findViewById<View>(R.id.edtText_Del) as EditText
        edt.setText(`val`)
        parentLinearLayoutDel!!.addView(rowView, parentLinearLayoutDel!!.childCount - 1)
    }

    fun onChkFieldUndo(v: View, `val`: String) {
        val inflater =
            getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rowView: View = inflater.inflate(R.layout.fields, null)
        // Add the new row before the add field button.
        val edt = rowView.findViewById<View>(R.id.edtText) as EditText
        edt.setText(`val`)
        parentLinearLayout!!.addView(rowView, parentLinearLayout!!.childCount - 1)
    }

    fun onDelete(v: View) {
        parentLinearLayout!!.removeView(v.parent as View)
    }

    fun onDeleteDel(v: View) {
        parentLinearLayoutDel!!.removeView(v.parent as View)
    }

    fun onChecked(v: View) {
        var str = ""
        val parentLinearLayout1 = v.parent as LinearLayout
        val edt = parentLinearLayout1.findViewById<EditText>(R.id.edtText)
        str = str + edt.text.toString()
        Toast.makeText(this, "" + str, Toast.LENGTH_SHORT).show()
        parentLinearLayout!!.removeView(v.parent as View)
        onChkField(v, str)
    }

    fun onCheckedDel(v: View) {
        var str = ""
        val parentLinearLayout = v.parent as LinearLayout
        val edt = parentLinearLayout.findViewById<EditText>(R.id.edtText_Del)
        str = str + edt.text.toString()
        Toast.makeText(this, "" + str, Toast.LENGTH_SHORT).show()
        parentLinearLayoutDel!!.removeView(v.parent as View)
        onChkFieldUndo(v, str)
    }

    fun getCount(v: View) {
        var str = ""
        for (i in 1 until parentLinearLayout!!.childCount - 1) {
            val viewGroup = parentLinearLayout!!.getChildAt(i)
            val edt = viewGroup.findViewById(R.id.edtText) as EditText
            str = str + edt.text.toString()
        }
        Toast.makeText(this, "" + str, Toast.LENGTH_LONG).show()
    }
}