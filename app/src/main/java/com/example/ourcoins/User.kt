package com.example.ourcoins

import java.util.HashMap

class User {
    var id:String?=null
    var nombre:String?=null
    var correo:String?=null

    constructor(){}

    constructor(id:String, Nombre:String, Correo:String){
        this.id=id
        this.nombre=Nombre
        this.correo=Correo
    }

    fun toMap():Map<String, Any>{
        val result= HashMap<String, Any>()
        result.put("nombre", nombre!!)
        result.put("correo", correo!!)
        return result
    }
}