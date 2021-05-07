package pt.ipg.adivinhanumero

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity() {
    private val random = Random()
    private var numeroAdivinhar : Int = 0
    private var jogo : Int = 0
    private var tentativas : Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        novoJogo()
    }

    private fun novoJogo() {
        numeroAdivinhar = random.nextInt(10) + 1
        tentativas = 0
        jogo++
        atualizaEcra()
    }

    private fun atualizaEcra() {
        findViewById<TextView>(R.id.textViewNTentativas).text = "Tentativas: " + tentativas
        findViewById<TextView>(R.id.textViewNJogos).text = "Jogo: " + jogo
    }


    fun adivinha(view: View) {
        tentativas++
    }
}