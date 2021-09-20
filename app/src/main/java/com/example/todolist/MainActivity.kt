package com.example.todolist

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import android.widget.EditText
import android.widget.AdapterView
import androidx.appcompat.app.AlertDialog
import java.io.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edittext: EditText = findViewById(R.id.writeitem)

        val button: Button = findViewById(R.id.additem)
        var listView: ListView = findViewById(R.id.list_item)

        var itemlist = arrayListOf<String>()

        button.setOnClickListener {
            itemlist.add(edittext.text.toString())
            listView.adapter =
                ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itemlist)
        }

        listView.setOnItemClickListener { adapterView, view, i, l ->

            Toast.makeText(this, "u click on " + itemlist.get(i), Toast.LENGTH_SHORT).show()

            val boton1 = findViewById<Button>(R.id.button)
            val et1 = findViewById<EditText>(R.id.writeitem)
            if (fileList().contains("notas.txt")) {
                try {
                    val archivo = InputStreamReader(openFileInput("notas.txt"))
                    val br = BufferedReader(archivo)
                    var linea = br.readLine()
                    val todo = StringBuilder()
                    while (linea != null) {
                        todo.append(linea + "\n")
                        linea = br.readLine()
                    }
                    br.close()
                    archivo.close()
                    et1.setText(todo)
                } catch (e: IOException) {
                }
            }

            boton1.setOnClickListener {
                try {
                    val archivo = OutputStreamWriter(openFileOutput("notas.txt", MODE_PRIVATE))
                    archivo.write(et1.text.toString())
                    archivo.flush()
                    archivo.close()
                } catch (e: IOException) {
                }
                Toast.makeText(this, "Los datos fueron grabados", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
