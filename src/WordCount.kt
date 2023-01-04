import java.io.File
import java.io.InputStream



fun main(args: Array<String>) {

    if(args.size == 0){
        println("Must provide file name")
    } else {

        // Read from a file and turn into a string. test.txt > allWords
        val inputStream: InputStream = File(args[0]).inputStream()

        val words = inputStream.bufferedReader().use { it.readText() }

        // Create a list of all the words separated out

        val wordList = words.replace(")", "").replace("(", " ").replace("]", "").replace("[", " ").replace("-", "").replace("\n", " ").replace(",", "").replace(".", "").replace("!", "").replace("?", "").split(" ")


        // Get a counted list of all the words

        val mapOfWords = mutableMapOf<String, Int>()

        for (word in wordList) {

            if (word != "") {


                if (mapOfWords.containsKey(word.toLowerCase())) {
                    var tempValue = mapOfWords.get(word.toLowerCase())!! + 1

                    mapOfWords.put(word.toLowerCase(), tempValue)
                } else {
                    mapOfWords.put(word.toLowerCase(), 1)
                }

            }
        }

        //Convert map to list
        val wordList2 = mapOfWords.toList()

        // Sort the list
        val sortedList = wordList2.sortedWith(compareByDescending({ it.second }))


        // Print a  sorted list of the  most popular words
        for (word in sortedList) {
            println("${word.first} - ${word.second}")
        }
    }
}