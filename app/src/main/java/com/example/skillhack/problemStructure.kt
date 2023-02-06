package com.example.skillhack

 class problemStructure {
    private lateinit var shortDiscriptionQuestion:String
    private lateinit var fullQuestion:String
    private lateinit var skill:String
    private lateinit var rewardAmt:String
    private lateinit var lastDate:String
   constructor(){

   }

     fun getshortDiscriptionQuestion(): String? {
         return shortDiscriptionQuestion
     }
     fun setshortDiscriptionQuestion(sdQuestion: String){
         this.shortDiscriptionQuestion=sdQuestion
     }
     fun getfullQuestion(): String? {
         return fullQuestion
     }
     fun setfullQuestion(sdQuestion: String){
         this.fullQuestion=sdQuestion
     }
     fun getskill(): String? {
         return skill
     }
     fun setskill(sdQuestion: String){
         this.skill=sdQuestion
     }

fun getrewardAmt(): String? {
    return rewardAmt
}
fun setrewardAmt(sdQuestion: String){
    this.rewardAmt=sdQuestion
}

     fun getlastDate(): String? {
         return lastDate
     }
     fun setlastDate(sdQuestion: String) {
         this.lastDate = sdQuestion
     }


}