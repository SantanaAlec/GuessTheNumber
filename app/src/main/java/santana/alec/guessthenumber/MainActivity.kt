package santana.alec.guessthenumber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlin.random.Random
import java.util.*

class MainActivity : AppCompatActivity() {

    var minValue = 0
    var maxValue = 100
    var num: Int = 0
    var won = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Instancia al objeto del layout guessing
        val guessing: TextView = findViewById(R.id.guessing)
        val down: Button = findViewById(R.id.down)
        val up: Button = findViewById(R.id.up)
        val generate: Button = findViewById(R.id.generate)
        val guessed: Button = findViewById(R.id.guessed)

        //guessed.visibility = View.INVISIBLE

        //Metodo del clic del botón generate (Start Game)
        generate.setOnClickListener {
            //num = Random().nextInt(maxValue+1) Usando java utils: rango= 0 , 100
            num = Random.nextInt(minValue, maxValue)
            guessing.setText(num.toString())
            generate.visibility = View.INVISIBLE
            guessed.visibility = View.VISIBLE
        }

        up.setOnClickListener {
            minValue = num
            if (checkingLimits()) {
                num = Random.nextInt(minValue, maxValue)
                guessing.setText(num.toString())
            } else {
                guessing.setText("No puede ser, me venciste")
            }
        }

        down.setOnClickListener {
            maxValue = num
            if (checkingLimits()) {
                num = Random.nextInt(minValue, maxValue)
                guessing.setText(num.toString())
            } else {
                guessing.setText("No puede ser, me venciste")
            }
        }

        guessed.setOnClickListener {
            if (!won) {
                guessing.setText("Ajá!, tu numero es: " + num)
                guessed.setText("Volver a jugar")
                won = true
            } else {
                generate.visibility = View.VISIBLE
                guessing.setText("Tap on generate to start")
                guessed.setText("Guessed")
                guessed.visibility = View.GONE
                resetValues()
            }
        }
    }

    //Funciones
    fun resetValues() {
        minValue = 0
        maxValue = 100
        num = 0
        won = false
    }

    fun checkingLimits(): Boolean {
        return minValue != maxValue
    }
}