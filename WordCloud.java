/**
 * Description: Parses an input file into distinct, non-common words,
 * counting the occurrences of each such word, before sending the data
 * to a CloudWindow for display as a word-cloud.
 *
 * @author M. Allen
 * @author Jackson Lee
 */
import java.util.*;
import java.util.Scanner;
import java.io.*;


public class WordCloud
{
    
    /**
     * Simple initiating main(). Sends names of input files to WordCloud object.
     *
     * @param args Not used.
     */
    public static void main( String[] args )
    {
        final String commonWordsFile = "commonest.txt";
        final String inputFile = "dream.txt";
        new WordCloud( commonWordsFile, inputFile );
    }
    
    /**
     * Reads words from an input file, counts them, and writes data
     * into a word cloud.
     *
     * @param commonWordsFile Name of a file containing common words
     *        (which are ignored when reading the input).
     * @param inputFile Name of a file containing words to count.
     */
    public WordCloud( String commonWordsFile, String inputFile )
    {
        // load common words to one list
        LinkedList<String> commonWords = new LinkedList<String>();
        loadCommon( commonWordsFile, commonWords );
        
        // open CloudWindow to display words
        LinkedList<WordCounter> counts = new LinkedList<>();
        countWords( inputFile, commonWords, counts );
        CloudWindow cw = new CloudWindow();
        cw.makeWindow( counts );
    }
    
    /**
     * Pre: the inFile should be an accessible text-file, and list should be a
     * non-null list that can hold Strings.
     *
     * Post:  each line of the input file is stored as a String into the list.
     *
     * @param inFile Name of file to read lines from.
     * @param list List of Strings to add the lines to.
     */
    private void loadCommon( String inFile, LinkedList<String> list ) {
        try {
            Scanner scan = new Scanner(new File(inFile));
            
            while(scan.hasNextLine()) {
                list.add(scan.nextLine());
            } 
        }
        catch(IOException e)  {
            System.out.println("error");
        }
    }
    
    /**
     * Pre: the inFile should be an accessible text-file, commonWords should be
     * a list of common words to exclude, and counts should be a list for Counters.
     *
     * Post:  the number of occurences for each word from the input file that is
     * not in the common list should be determined;  the counts list will consist of
     * one Counter object per non-common word, holding the count of that word.
     *
     * @param inFile Name of file to read lines from.
     * @param commonWords List of words to ignore in the input file.
     * @param counts List of Counter objects to store the data about word-counts.
     */
    private void countWords( String inFile, LinkedList<String> commonWords,
                            LinkedList<WordCounter> counts )
    {
        try {
            String[] wordList;
            Scanner scan = new Scanner(new File(inFile));
            WordCounter cnt;
            
            while(scan.hasNextLine()) {
                wordList = splitLine(scan.nextLine());
                for(int i = 0; i < wordList.length; i++) {
                    if(!commonWords.contains(wordList[i])) {
                        if(counts.contains(new Counter(wordList[i],0))) {
                            cnt = counts.get(counts.indexOf(new Counter(wordList[i],0)));
                            counts.remove(counts.indexOf(new Counter(wordList[i],0)));
                            counts.add(new Counter(wordList[i],cnt.getCount()+1));
                        }
                        else {
                            counts.add(new Counter(wordList[i],1));
                        }
                    }
                }
            }
        }
        catch(IOException e)  {
            System.out.println("error");
        }
    }
    
    // post: returns array containing individual words in s, after removing
    // punctuation and converting to lower-case
    private String[] splitLine( String s )
    {
        // remove punctuation from line and convert to lower-case
        s = s.replaceAll( "\\p{Punct}", "" );
        s = s.toLowerCase();
        // create array by splitting out all white space
        String[] wds = s.trim().split( "\\s+" );
        return wds;
    }
}