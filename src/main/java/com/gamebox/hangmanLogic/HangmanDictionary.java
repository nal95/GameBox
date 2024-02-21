package com.gamebox.hangmanLogic;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class HangmanDictionary {

    private final List<String> words;

    private final WordCategory category;


    public HangmanDictionary(WordCategory category) {
        this.words = new ArrayList<>();
        this.category = category;
        // Make an API call to fetch words and populate the wordList
        fetchWordsFromAPI();
    }

    public List<String> getWords() {
        return words;
    }

    private void fetchWordsFromAPI() {
        try {
            // Replace "API_ENDPOINT" with the actual endpoint of your word-fetching API
            String url = "https://www.wordgamedb.com/api/v1/words/?category=" + category.name();
            JSONArray jsonArray = getJsonArray(url);

            // Extract words from the JSON array and populate the wordList
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String word = jsonObject.getString("word");
                words.add(word);
            }
        } catch (IOException e) {
            //TODO: work on error handling & logging
            e.printStackTrace();
        }
    }

    private static JSONArray getJsonArray(String stringUrl) throws IOException {
        URL url = new URL(stringUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // Read the response from the API
        Scanner scanner = new Scanner(connection.getInputStream());
        StringBuilder response = new StringBuilder();

        while (scanner.hasNextLine()) {
            response.append(scanner.nextLine());
        }

        scanner.close();

        // Parse the JSON array in the API response
        return new JSONArray(response.toString());
    }

}
