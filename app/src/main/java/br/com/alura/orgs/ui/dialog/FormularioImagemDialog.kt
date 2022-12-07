package br.com.alura.orgs.ui.dialog

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import br.com.alura.orgs.databinding.FormularioImagemBinding
import br.com.alura.orgs.extensions.tentaCarregarImage

class FormularioImagemDialog(private val context: Context) {
    fun mostra(
        urlPadrao: String? = null,
        quandoImageCarregada: (imagem: String) -> Unit
    ) {
        FormularioImagemBinding.inflate(LayoutInflater.from(context)).apply {
            urlPadrao?.let {
                formularioImagemImageview.tentaCarregarImage(it)
                formularioImagemUrl.setText(it)
            }

            formularioImagemBotaoCarregar.setOnClickListener {
                val url = formularioImagemUrl.text.toString()
                formularioImagemImageview.tentaCarregarImage(url)
            }

            AlertDialog.Builder(context)
                .setView(root)
                .setPositiveButton("Confirmar") { _, _ ->
                    val url = formularioImagemUrl.text.toString()
                    quandoImageCarregada(url)
                }
                .setNegativeButton("Cancelar") { _, _ ->

                }
                .show()
        }
    }
}