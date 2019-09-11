package br.com.fiap.view.form

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import br.com.fiap.R
import br.com.fiap.model.Pokemon
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_form_pokemon.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class FormPokemonActivity : AppCompatActivity() {

    val formPokemonViewModel : FormPokemonViewModel by viewModel()
    val picasso: Picasso by inject()
    val baseURL: String by inject(named ("baseURL"))
    lateinit var pokemon: Pokemon

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_pokemon)

        setValues()

        formPokemonViewModel.messageResponse.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })

        btSaveForm.setOnClickListener {
            pokemon.attack = sbAttack.progress
            pokemon.defense = sbDefense.progress
            pokemon.velocity = sbVelocity.progress
            pokemon.ps = sbPS.progress
            formPokemonViewModel.updatePokemon(pokemon)
        }
    }


    private fun setValues() {
        pokemon = intent.getParcelableExtra("POKEMON")
        tvPokemonNameForm.text = pokemon.name
        picasso.load("$baseURL${pokemon.imageURL}").into(ivPokemonForm)

        setSeekBar(sbAttack, tvAttackValue, pokemon.attack)
        setSeekBar(sbDefense, tvDefenseValue, pokemon.defense)
        setSeekBar(sbPS, tvPSValue, pokemon.ps)
        setSeekBar(sbVelocity, tvVelocityValue, pokemon.velocity)
    }

    private fun setSeekBar(seekBar: SeekBar, textView: TextView, value: Int) {
        seekBar.progress = value
        textView.text = value.toString()
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                textView.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }
}


