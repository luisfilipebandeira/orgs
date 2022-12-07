package br.com.alura.orgs.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.orgs.R
import br.com.alura.orgs.databinding.ActivityDescricaoProdutoBinding
import br.com.alura.orgs.extensions.tentaCarregarImage
import br.com.alura.orgs.model.Produto
import coil.load
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

class DescricaoProdutoActivity: AppCompatActivity() {
    private val binding by lazy {
        ActivityDescricaoProdutoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        loadInfo()
    }

    private fun loadInfo() {
        val produto = intent.extras?.get("produto") as Produto

        val campoTitulo = binding.tituloProduto
        campoTitulo.text = produto.nome

        val descricao = binding.descricaoProduto
        descricao.text = produto.descricao

        var preco = binding.produtoPreco
        val valorEmMoeda: String =
            formataParaMoedaBrasileira(produto.valor)
        preco.text = valorEmMoeda

        if (produto.imagem != null) {
            binding.imageView.tentaCarregarImage(produto.imagem)
        } else {
            binding.imageView.load(R.drawable.imagem_padrao)
        }
    }

    private fun formataParaMoedaBrasileira(valor: BigDecimal): String {
        val formatador: NumberFormat = NumberFormat
            .getCurrencyInstance(Locale("pt", "br"))
        return formatador.format(valor)
    }
}