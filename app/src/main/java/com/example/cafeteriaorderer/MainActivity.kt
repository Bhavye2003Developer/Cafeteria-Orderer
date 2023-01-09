package com.example.cafeteriaorderer

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val order : Button = findViewById(R.id.order)
        var reset : Button = findViewById(R.id.reset)
        val name : EditText = findViewById(R.id.name)
        val whipped_cream : CheckBox = findViewById(R.id.whipped_cream)
        val chocolate : CheckBox = findViewById(R.id.chocolate)
        val add : Button = findViewById(R.id.add)
        val sub : Button = findViewById(R.id.sub)
        val quantity : TextView = findViewById(R.id.quantity)
        val output : TextView = findViewById(R.id.output)
        val emain_order : Button = findViewById(R.id.emain_order)

        var result : String? = null

        var number : Int = 0;

        add.setOnClickListener {
            number++;
            quantity.setText("$number")
        }

        sub.setOnClickListener {
            if (number==0) number=0
            else {
                number--;
                quantity.setText("$number")
            }
        }

        order.setOnClickListener {
            result = "Name : ${name.editableText.toString()}\n"
            result+= "Add Whipped Cream? ${whipped_cream.isChecked()}\n"
            result+= "Add Chocolate? ${chocolate.isChecked()}\n"
            result+= "Quantity: ${number}\n"
            result+= "Total: $${number*3}"
            Toast.makeText(this,"Thank you for ordering from us...",Toast.LENGTH_SHORT).show()
            output.setText(result)
        }

        reset.setOnClickListener {
            result = null
            quantity.setText("0")
            whipped_cream.isChecked = false
            chocolate.isChecked = false
            name.setText("")
            output.setText("")
            Toast.makeText(this,"Resetting the form...",Toast.LENGTH_SHORT).show()
        }

        emain_order.setOnClickListener {
            Toast.makeText(this,"Sending order...",Toast.LENGTH_SHORT).show()
            val send_email : Intent = Intent(Intent.ACTION_SEND)
            send_email.setType("test/plain")
            send_email.putExtra(Intent.EXTRA_SUBJECT,"Order for Cafeteria")
            send_email.putExtra(Intent.EXTRA_TEXT,"ORDER\n${result}")
            startActivity(send_email)
        }
    }
}