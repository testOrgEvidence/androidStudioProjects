package com.example.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnRoll = findViewById<Button>(R.id.btnRoll)
        btnRoll.setOnClickListener {
            Toast.makeText(this,"DICE ROLLED",Toast.LENGTH_SHORT).show()
            rollDice()
           }
    }
    private fun rollDice() {
        val dice = Dice(6)
        val diceRoll = dice.roll()
        var imgDice=findViewById<ImageView>(R.id.imgDice)
        var drawableImg=when(diceRoll){
            1->R.drawable.dice1
            2->R.drawable.dice2
            3->R.drawable.dice3
            4->R.drawable.dice4
            5->R.drawable.dice5
            else->R.drawable.dice6

        }
        imgDice.setImageResource(drawableImg)
        imgDice.contentDescription=diceRoll.toString()
        var resultText=findViewById<TextView>(R.id.tvResult)
        //Toast.makeText(this,"result= $diceRoll",Toast.LENGTH_LONG).show()
        val luckyNumber=4
        when(diceRoll){
            luckyNumber->resultText.text="You Won"
            1->resultText.text="You Rolled 1 Try Again"
            2->resultText.text="You Rolled 2 Try Again"
            3->resultText.text="You Rolled 3 Try Again"
            5->resultText.text="You Rolled 5 Try Again"
            6->resultText.text="You Rolled 6 Try Again"
        }


    }
   /** private fun result(diceRoll:Int) {
        var resultText=findViewById<TextView>(R.id.tvResult)
        val luckyNumber=4
        Toast.makeText(this,"you got $resultText",Toast.LENGTH_LONG).show()
        when(diceRoll){
            luckyNumber->resultText.text="You Won"
            1->resultText.text="You Rolled 1 Try Again"
            2->resultText.text="You Rolled 2 Try Again"
            3->resultText.text="You Rolled 3 Try Again"
            5->resultText.text="You Rolled 5 Try Again"
            6->resultText.text="You Rolled 6 Try Again"
        }

    }**/
}


//class with functionality to generate number onClick with dice roll
class Dice(val numSides: Int) {
    fun roll(): Int {
        return (1..numSides).random()
    }
}
