package com.example.stepper01

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.lang.Exception
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {

    lateinit var BotaoCalc: Button
    lateinit var BotaoLimpar: Button
    lateinit var Passo: EditText
    lateinit var STEP: EditText
    lateinit var DIVPASSO: EditText
    lateinit var RELACAO: EditText
    lateinit var Vel: EditText
    lateinit var Acel: EditText

    lateinit var PRECISAO: TextView
    lateinit var CLK: TextView
    lateinit var Tmax: TextView
    lateinit var Vmm_min: TextView
    lateinit var NMotor: TextView
    lateinit var STATUS: TextView


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        BotaoCalc = findViewById(R.id.btnCalc)
        BotaoLimpar = findViewById(R.id.btnLimpar)
        Passo = findViewById(R.id.Entrypasso)
        STEP = findViewById(R.id.Entrystep)
        DIVPASSO = findViewById(R.id.EntrydivPasso)
        RELACAO = findViewById(R.id.Entryrelacao)
        Vel = findViewById(R.id.EntryVel)
        Acel = findViewById(R.id.EntryA)

        PRECISAO = findViewById(R.id.OutPrecisao)
        CLK = findViewById(R.id.OutCLK)
        Tmax = findViewById(R.id.OutTime)
        Vmm_min = findViewById(R.id.OutVel)
        NMotor = findViewById(R.id.OutNmotor)
        STATUS = findViewById(R.id.OutStatus)

        BotaoCalc.setOnClickListener(){


            try {
                val P = Passo.text.toString().toFloat()
                val step = STEP.text.toString().toFloat()
                val Rmotor = "360".toInt() / step
                val Div = DIVPASSO.text.toString().toFloat()
                val Rel = RELACAO.text.toString().toFloat()
                val i = (1/Rel)
                val V = Vel.text.toString().toFloat()
                val a = Acel.text.toString().toFloat()

                var ERRO = false
                ERRO = ((P == 0f) || (step == 0f) || (Div == 0f) || (Rel == 0f))

                var valor = (i*P) / (Rmotor*Div)
                PRECISAO.text = valor.toString()

                valor = (V * 60 * Rmotor * Div) / (i*P)
                CLK.text = valor.toString()

                valor = (V / a)
                Tmax.text = valor.toString()

                valor = (V * 60)
                Vmm_min.text = valor.toString()

                valor = (V*60)/(P*i)
                NMotor.text = valor.toString()

                if (ERRO == true) {
                    STATUS.text = "Entradas Incorretas!!"
                    STATUS.setBackgroundColor(Color.RED)
                }else{
                    STATUS.text = "Operação Realizada com SUCESSO!!"
                    STATUS.setBackgroundColor(Color.GREEN)

                }
            }
            catch (ex:Exception){
                STATUS.text = "Entradas Incorretas!!"
                STATUS.setBackgroundColor(Color.RED)
            }
            
        }

        BotaoLimpar.setOnClickListener(){

            Passo.setText("")
            STEP.setText("")
            DIVPASSO.setText("")
            RELACAO.setText("")
            Vel.setText("")
            Acel.setText("")
            STATUS.text = "Aguardando Entradas"
            STATUS.setBackgroundColor(Color.YELLOW)

        }
    }
}