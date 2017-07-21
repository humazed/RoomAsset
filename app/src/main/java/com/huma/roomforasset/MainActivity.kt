package com.huma.roomforasset

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.huma.room_for_asset.RoomAsset
import com.jakewharton.fliptables.FlipTableConverters
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity(), AnkoLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = RoomAsset.databaseBuilder(applicationContext, AppDatabase::class.java, "chinook.db").build()

        employeesButton.setOnClickListener {
            thread {
                val employees = db.chinookDao().employees

                runOnUiThread {
                    resultTextView.text = FlipTableConverters.fromIterable(employees, Employees::class.java)
                }

                info { "employees = ${employees}" }
                println("employees Table =\n ${FlipTableConverters.fromIterable(employees, Employees::class.java)}")
            }
        }

        customersButton.setOnClickListener {
            thread {
                val customers = db.chinookDao().customers

                runOnUiThread {
                    resultTextView.text = FlipTableConverters.fromIterable(customers, Customers::class.java)
                }

                info { "employees = ${customers}" }
                println("customers Table =\n ${FlipTableConverters.fromIterable(customers, Customers::class.java)}")
            }
        }

    }
}
