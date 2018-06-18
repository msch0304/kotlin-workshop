package de.tarent.academy.kotlin


fun main(arg:Array<String>){
    val roman = RomanEmpire()

    val list = listOf<String>("MMMCD", "XC","VII", "IV", "IX", "IMM", "MIM", "VM", "MDXL")
    list.forEach { it ->
        println(it + " = " + roman.getArabicValue(it))
    }
}

class RomanEmpire{



    fun getArabicValue(roman:String):Long{

        var sum:Long = 0

        var lastadded:Long = 0

        val list = roman.toCharArray()

        val reversed = list.reversed()

        var lastLetter  = 'I'
        var lettercount = 0

        for(letter in reversed){
            val toAdd:Long = when (letter){
                'I' -> 1L
                'V' -> 5L
                'X' -> 10L
                'L' -> 50L
                'C' -> 100L
                'D' -> 500L
                'M' -> 1000L
                else -> 0L
            }
            if (toAdd == 0L ){
                printError("invalid letter or space", roman)
                sum = 0
            }


            if (toAdd >=  lastadded){
                sum += toAdd

            } else if (letter in listOf('I', 'X', 'C') && lettercount == 1 ){
                sum -= toAdd
            } else{
                printError("invalid sequence", roman)
                sum = 0
            }
            lastadded = toAdd
            if (letter == lastLetter && letter in listOf('I', 'X', 'C', 'M')){
                lettercount ++
            }else if (letter != lastLetter){
                lettercount = 1
                lastLetter = letter
            }else{
                printError("unvalid sequence, more than 1 V,L,C ", roman)
                sum = 0
            }

            if (lettercount > 3 ){
                printError ("more than three equal letters in a sequence!", roman)
                sum = 0
            }
        }

        return sum
    }

    private fun printError(msg:String, input:String):Unit{
        println("Error with " + input +":" + msg)
    }
}

