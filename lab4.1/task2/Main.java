import java.io.*; 
import java.nio.file.*;

class FileReader{
    //read a file content into a string
    public static String readFileIntoString(String path) {
        try {
            return new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage()); 
            return ""; 
        }
    }
}

class TextData{
    String FileName;
    String text;
    int numberOfVowels;
    int numberOfConsonants;
    int numberOfLetters;
    int numberOfSentences;
    String longestWord;
  
    public TextData(String text) {
        this.text = text;
        this.numberOfVowels = countVowels(text);
        this.numberOfConsonants = countConsonants(text);
        this.numberOfLetters = numberOfVowels + numberOfConsonants;
        this.numberOfSentences = countSentences(text);
        this.longestWord = findLongestWord(text);

    }

    private int countVowels(String text) {
        int count = 0;
        for(char c : text.toLowerCase().toCharArray()){
            if("aeiou".indexOf(c) != -1){
            count++;
            }
        }
        return count;
    }

    private int countConsonants(String text){
        int count = 0;
        for(char c : text.toLowerCase().toCharArray()){
            if("bcdfghjklmnpqrtvwyz".indexOf(c) != -1){
            count++;
            }
        }
        return count;
    }

    private int countSentences(String text){
        int count = 0;
        for(char c : text.toCharArray()){
            if(c == '.' || c == '?' || c == '!'){
                count++;
            }
        }
        return count;
    }

    private String findLongestWord(String text){
        String longest = "";
        for(String word : text.split("\\s+")){
            if(word.length() > longest.length()){
                longest = word;
            }
        }
        return longest;

    }

    public String getText(){
        return text;
    }

    public int getNumberOfVowels(){
        return numberOfVowels;
    }

    public int getNumberOfConsonants(){
        return numberOfConsonants;
    }

    public int getNumberOfLetters(){
        return numberOfLetters;
    }

    public int getNumberOfSentences(){
        return numberOfSentences;
    }

    public String getLongestWord(){
        return longestWord;
    }

}

//main class - execute the program
public class Main {
    public static void main(String[] args){
        if(args.length == 0) {
            System.out.println("Please provide the file path as a command-line argument.");
            return;
        }

        String filePath = args[0];
        String fileContent = FileReader.readFileIntoString(filePath);

        if(fileContent.isEmpty()){
            System.out.println("File content is empty");
            return;
        }

        TextData textData = new TextData(fileContent);

        
        System.out.println("Loaded Text Data:");
        System.out.println("Number of Vowels: " + textData.getNumberOfVowels());
        System.out.println("Number of Consonants: " + textData.getNumberOfConsonants());
        System.out.println("Number of Letters: " + textData.getNumberOfLetters());
        System.out.println("Number of Sentences: " + textData.getNumberOfSentences());
        System.out.println("Longest Word: " + textData.getLongestWord());

    }
    
}
