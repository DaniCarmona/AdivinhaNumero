package pt.ipg.adivinhanumero

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
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
        atualizaJogo()
        atualizaTentativas()
        findViewById<TextView>(R.id.textViewMaiorMenor).text = ""
    }

    private fun atualizaJogo() {
        findViewById<TextView>(R.id.textViewNJogos).text = getString(R.string.jogo) + " " + jogo
    }

    private fun atualizaTentativas() {
        findViewById<TextView>(R.id.textViewNTentativas).text = getString(R.string.tentativas) + " " + tentativas
    }

    fun adivinha(view: View) {
        val editTextNumero = findViewById<EditText>(R.id.editTextNumero)
        val numero = editTextNumero.text.toString().toIntOrNull()
        when(numero) {
            in 1..10 -> verificaAcertou(numero)
            null -> editTextNumero.error = getString(R.string.numero_invalido)
            else -> editTextNumero.error = getString(R.string.numero_entre_1_e_10)
        }
    }

    private fun verificaAcertou(numero: Int?) {
        val textViewMaiorMenor = findViewById<TextView>(R.id.textViewMaiorMenor)
        if(numero == numeroAdivinhar){
           textViewMaiorMenor.text=getString(R.string.acertou)
           perguntaSeQuerJogarNovamente()
        }else if(numeroAdivinhar>numero!!){
            textViewMaiorMenor.text=getString(R.string.maior)
        }else{
            textViewMaiorMenor.text=getString(R.string.menor)
        }
        tentativas++;
        atualizaTentativas()
    }

    private fun perguntaSeQuerJogarNovamente() {
        val alertDialogBuilder = AlertDialog.Builder(this)

        alertDialogBuilder.setTitle(R.string.acertou)
        alertDialogBuilder.setMessage(R.string.novo_jogo)
        alertDialogBuilder.setPositiveButton(
                android.R.string.ok,
                DialogInterface.OnClickListener { dialog, which -> novoJogo() }
        )

        alertDialogBuilder.setNegativeButton(
                android.R.string.cancel,
                DialogInterface.OnClickListener { dialog, which -> finish() }
        )
        alertDialogBuilder.show()
    }


}