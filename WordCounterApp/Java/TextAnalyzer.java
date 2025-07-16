package Java;
import java.util.Scanner;

public class TextAnalyzer{
    public static int countLetters(String paragraph){
        String letters = paragraph.replace("\n", "");
        return letters.length();

    }
    public static int countWords(String paragraph){
        /**
         * this was the first logic i thought of based on basic knowledge I get
        String words = paragraph.replace("\n", " ");
        int count = 0;
        for (int i = 0; i < words.length(); i++){
            if(words.charAt(i) == ' '){
                count++;
            }
        }
        return count;*/
        // then I thought it counts words based on only spaces what if user enter 2 spaces by mistake then i found this easy way
        String[] splitWords = paragraph.trim().split("\\s+");
        return splitWords.length;

    }
    public static int countParagraphs(String paragraph){
        String[] paragraphs = paragraph.split("\\n+");
        return paragraphs.length;
    }
    /**public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        String paragraph = "";
        String line;
        while(!(line = input.nextLine()).isEmpty()) {
            paragraph += line + "\n";
        }
        System.out.println(countLetters(paragraph) + " Letters");
        System.out.println(countWords(paragraph) + " words");
        System.out.println(countParagraphs(paragraph) + " paragraphs");
    }*/
}
