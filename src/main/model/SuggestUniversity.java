package model;

import exception.NoSuggestionsException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Code partly taken from JsonSerializationDemo

//Class to suggest the university to user, if the interests or major or location of the UserChoices object in userlist
// are the same as DataChoices object in the dataList

public class SuggestUniversity implements Writable {
    private ChoicesList<UserChoices> userList;
    private ArrayList<DataChoices> dataList;
    private ArrayList<String> suggestionList;

    //MODIFIES: this
    //EFFECTS: creates a SuggestUniversity object and assigns userList and dataList
    public SuggestUniversity(ChoicesList<UserChoices> userList, ArrayList<DataChoices> dataList) {
        this.userList = userList;
        this.dataList = dataList;
        suggestionList = new ArrayList<>();
    }

    //EFFECTS: created a SuggestUniversity object
    //         used just for JSON testing purposes
    public SuggestUniversity() {
        suggestionList = new ArrayList<>();

    }

    //MODIFIES: this
    //EFFECTS: adds 2 values to suggestionList
    //         used just for JSON testing purposes
    public void addTestingValues() {
        this.suggestionList.add("TestVal 1");
        this.suggestionList.add("TestVal 2");
    }

    //MODIFIES: this
    //EFFECTS: compares interests, major and location of userList object with every object in dataList; and prints
    // out the related university if the match is found
    // throws new NoSuggestionsException if the suggestion list is empty
    public ArrayList<String> suggestion() throws NoSuggestionsException {
        for (int i = 0; i < userList.size(); i++) {
            UserChoices userTemp = userList.get(i);

            for (DataChoices data : dataList) {
                DataChoices dataTemp = data;

                if (userTemp.getInterests().equals(dataTemp.getInterests())
                        || userTemp.getMajor().equals(dataTemp.getMajor())
                        || userTemp.getLocation().equals(dataTemp.getLocation())) {

                    if (!(suggestionList.contains(dataTemp.getUniversity()))) {
                        suggestionList.add((data).getUniversity());
                    }
                }
            }
        }
        if (suggestionList.size() == 0) {
            throw new NoSuggestionsException();
        }
        return suggestionList;
    }

    //MODIFIES: this
    //EFFECTS: adds the suggestions to suggestionList
    //         used for reading JSON files
    public void addToSuggestionList(String suggestion) {
        suggestionList.add(suggestion + "\n");
    }

    //EFFECTS: returns suggestionList
    public ArrayList<String> getSuggestionList() {
        return this.suggestionList;
    }

    //EFFECTS: returns the size of suggestionList
    public int listSize() {
        return suggestionList.size();
    }

    // Code partly taken from JsonSerializationDemo
    //EFFECTS: returns json object of suggestionList
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Suggested", suggestedToJson());
        return json;
    }

    // Code partly taken from JsonSerializationDemo
    // EFFECTS: returns suggestionList as a json array
    private JSONArray suggestedToJson() {
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < suggestionList.size(); i++) {
            jsonArray.put(suggestionList.get(i));
        }
        return jsonArray;
    }
}

