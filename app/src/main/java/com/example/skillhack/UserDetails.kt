package com.example.skillhack

import kotlin.properties.Delegates

class UserDetails {
    private lateinit var mobNum:String
    private lateinit var dob:String
    private lateinit var name:String
    private lateinit var skills:ArrayList<String>
    private var TotalReward:Int=0
    constructor(){

    }
    fun setmobNum(data:String){
        this.mobNum=data
    }
    fun getmobNum():String?{
        return mobNum
    }
    fun setskills(data:ArrayList<String>){
        this.skills=data
    }
    fun getskills():ArrayList<String>{
        return skills
    }
    fun setname(data:String){
        this.name=data
    }
    fun getname():String?{
        return name
    }
    fun setdob(data:String){
        this.dob=data
    }
    fun getdob():String?{
        return dob
    }
    fun getTotalReward():Int{
        return TotalReward
    }
    fun setTotalReward(data:Int){
        this.TotalReward+=data
    }

}