/*
 * Programmer:Jackson Lee
 * Program: Counter
 * Date:4/27/16
 */
public class Counter implements WordCounter
{
    private String word;
    private int count;
    //constructor
    public Counter(String w, int c)
    {
        word = w;
        count = c;
    }
    //return word
    public String getWord() {
        return word;
    }
    //returns count
    public int getCount() {
        return count;
    }
    //allows the user to set word
    public void setWord( String word ) {
        this.word = word;
    }
    //allows the user to set count
    public void setCount( int count ) {
        this.count = count;
    }
    //compares String variables and returns an int to represent that comparison
    public int compareTo(WordCounter w) {
        return this.word.compareTo(((Counter) w).word);
    }
    //returns true if and only if the words are the same
    public boolean equals(Object obj) {
       Counter wordCounter;
        
       if(obj instanceof WordCounter) {
            wordCounter = (Counter) obj;
            if(this.word.equals(wordCounter.word)) {
                return true;
            } else {
                return false;
            }
       } else {
            return false;
       }
    }
}
