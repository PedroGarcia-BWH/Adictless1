package com.example.adictless1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TableLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_awards.*
import kotlinx.android.synthetic.main.activity_awards.view.*

class Awards : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_awards)

        //Lista con todos los imageView de los trofeos
        val imageList: MutableList<ImageView> = arrayListOf()
        imageList.add(findViewById<ImageView>(R.id.imageView12))
        imageList.add(findViewById<ImageView>(R.id.imageView8))
        imageList.add(findViewById<ImageView>(R.id.imageView10))
        for(id in 13 until 28){
            imageList.add(findViewById<ImageView>(resources.getIdentifier("imageView" + id, "id", packageName)))
        }

        //Creacion de lista trofeos
        var AwardList: MutableList<Award> = arrayListOf()
        AwardList.add(Award("Chat", 1, imageList[0]))
        AwardList.add(Award("Estadistica", 1, imageList[1]))
        AwardList.add(Award("XP", 1, imageList[2]))

        val categoria: Array<String> = arrayOf("Chat", "Estadistica", "XP")
        val nivel: Array<Int> = arrayOf(1,2,3,4,5,6)
        var contador = 0
        var nivelIt = 0

        for (it in 0..17){
            if(contador == 3){
                contador = 0
                nivelIt++
            }
            AwardList.add(Award(categoria[contador], nivel[nivelIt], imageList[it]))
            contador++
        }

        //Descripcion de AwardList: Lista que contiene todos los logros disponibles
        // Primero = Chat, Segundo = Estadistica, Tercero = XP

        // 0, 1, 2 -> Bronces
        // 3, 4, 5 -> Plata
        // 6, 7, 8 -> Oro
        // 9, 10, 11 -> Diamante
        // 12, 13, 14 -> Platino
        // 15, 16, 17 -> Extra

        //Para desbloquear un trofeo -> AwardList[numTrofeo segun lo descrito arriba].desbloquear()
    }
}



class Award{
    val Categoria : String
    val Nivel : Int
    val ImageId: ImageView
    var Conseguido : Boolean

    constructor(Categoria: String, Nivel: Int, ImageId: ImageView, Conseguido : Boolean = false){
        this.Categoria = Categoria
        this.Nivel = Nivel
        this.ImageId = ImageId
        this.Conseguido = Conseguido
        if(Conseguido == false){
            ImageId.alpha = 0.5F
        }
    }

    fun desbloquear(){
        this.Conseguido = true
        this.ImageId.alpha = 1F
    }

    override fun toString(): String {
        return "Award(Categoria='$Categoria', Nivel=$Nivel, ImageId=$ImageId, Conseguido=$Conseguido)"
    }

}