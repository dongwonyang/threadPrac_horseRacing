package com.example.thread_coroutine_prac

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread
import kotlin.random.Random


var isFinish = false

fun main(){
    println("경마 게임 시작")

    var selectedHorse : Int
    while(true) {
        try {
            println("말을 선택해 주세요")
            println("1, 2, 3, 4, 5")
            selectedHorse = readln()!!.split(' ')[0].toInt()
            break
        } catch (e: java.lang.Exception) {
            continue
        }
    }

    for (i in 1..5) {
        thread(true) {
            horseRun(i, 100)
        }
    }


}

fun horseRun(horseNum: Int, finalDistance: Int){
    val random = Random.Default
    var horseDistance = 0
    while (true){
        horseDistance += random.nextInt(5) + 1
        if(isFinish) break

        if(horseDistance < finalDistance){
            println("${horseNum}번 말: ${horseDistance}")
        } else if(horseDistance >= finalDistance){
            println("${horseNum}번 말 도착!!!")
            isFinish = true
            break
        }
        runBlocking {
            launch {
                delay(1000)
            }
        }
    }
}