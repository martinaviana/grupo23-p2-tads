package uy.edu.um.prog2.tad.f1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVRecord;

@RequiredArgsConstructor
@Getter
public class TweetsWithSpecificString implements TweetRecordCallback {
    private long stringCounter = 0;
    @NonNull
    private String stringToCount;

    @Override
    public void execute(CSVRecord record) {
        String[] loweredStringWords = stringToCount.toLowerCase().split(" ");
        String[] words = record.get("text").toLowerCase().split(" ");

        if (loweredStringWords.length == 1)
            algorithmForWord(loweredStringWords[0], words);
        else
            algorithmForPhrase(loweredStringWords, words);

    }

    private void algorithmForWord(String wordToSearch, String[] words) {
        for (String word : words)
            if (wordToSearch.equals(word))
                stringCounter++;
    }

    private void algorithmForPhrase(String[] phrasetoSearch, String[] words) {
        int i = 0;
        boolean sum = false;
        try {
            for (String word : words)
                if (phrasetoSearch[i].equals(word)) {
                    i++;
                    sum = true;
                } else if (i != 0 && !phrasetoSearch[i].equals(word) && i != phrasetoSearch.length-1) {
                    sum = false;
                    break;
                }
            if (sum)
                stringCounter++;
        } catch (ArrayIndexOutOfBoundsException e) {

        }
    }
}
