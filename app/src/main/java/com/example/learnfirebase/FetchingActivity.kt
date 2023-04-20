package com.example.learnfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learnfirebase.adapter.ProductAdapter
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_fetching.*

class FetchingActivity : AppCompatActivity() {

    private lateinit var ds:ArrayList<ProductModel>
    private lateinit var dbRef :DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetching)

        rvSP.layoutManager = LinearLayoutManager(this)
        rvSP.setHasFixedSize(true)
        ds = arrayListOf<ProductModel>()
        showProduct()
    }

    private fun showProduct() {
        dbRef = FirebaseDatabase.getInstance().getReference("Product")
        //Để đọc dữ liệu tại một đường dẫn và lắng nghe các thay đổi,
        //hãy sử dụng addValueEventListener()

        dbRef.addValueEventListener(object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for(empSnap in snapshot.children){
                        val empData = empSnap.getValue(ProductModel::class.java)
                        ds.add(empData!!)
                    }
                    val mAdapter = ProductAdapter(ds, this@FetchingActivity)
                    rvSP.adapter = mAdapter
                    //code lắng nghe sự kiện click lên item rv
                    mAdapter.setOnItemClickListener(object : ProductAdapter.onItemClickListener{
                        //ctrl +i
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@FetchingActivity,MainActivity::class.java)
                            //put extras
                            intent.putExtra("empId",ds[position].idSP)
                            intent.putExtra("empName",ds[position].tenSP)
                            intent.putExtra("empAge",ds[position].loaiSP)
                            intent.putExtra("empSalary",ds[position].giaSP)
                            startActivity(intent)
                        }
                    })


                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}