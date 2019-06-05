package com.example.ourcoins

class Gastos {
    var fecha:String?=null
    var categoria:String?=null
    var cantidad:String?=null
    var usuario:String?= null
    var nota:String?=null
    var cuenta:String?=null

    constructor(){}

    constructor(f:String, categoria:String, c:String, u:String, n:String, cu:String){
        this.fecha=f
        this.categoria=categoria
        this.cantidad=c
        this.usuario=u
        this.nota=n
        this.cuenta=cu
    }
}