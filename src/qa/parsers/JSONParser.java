package qa.parsers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.json.*;

import fileManagement.FileManager;
import qa.dataStructures.Question;

public class JSONParser {

    
    public static ArrayList<Question> parseSimpleDB(String fileDirectory, String sourceString, String endpoint) throws JSONException {
        ArrayList<Question> questionsList = new ArrayList<Question>();
        String fileContents = FileManager.readWholeFile(fileDirectory);
        JSONObject obj = new JSONObject(fileContents);
        JSONArray arr = obj.getJSONArray("Questions");
        for (int i = 0; i < arr.length(); i++) {

            Question question = new Question();
            question.setDatabase(endpoint);
            question.setFilepath(fileDirectory);
            // The source is predefined
            question.setQuestionSource(sourceString);

            JSONObject currentQuestionObject = arr.getJSONObject(i);
            question.setQuestionString(currentQuestionObject.getString("Query"));

            questionsList.add(question);
        }
        return questionsList;
    }

    
    public static ArrayList<Question> parseFree917(String fileDirectory, String sourceString, String endpoint) throws JSONException {
        ArrayList<Question> questionsList = new ArrayList<Question>();
        String fileContents = FileManager.readWholeFile(fileDirectory);
        JSONArray arr = new JSONArray(fileContents);
        for (int i = 0; i < arr.length(); i++) {

            Question question = new Question();
            question.setDatabase(endpoint);
            question.setFilepath(fileDirectory);
            // The source is predefined
            question.setQuestionSource(sourceString);

            JSONObject currentQuestionObject = arr.getJSONObject(i);
            question.setQuestionString(currentQuestionObject.getString("utterance").replace("\n", ""));

            questionsList.add(question);
        }
        return questionsList;
    }

    
    
    public static ArrayList<Question> parseComQA(String fileDirectory, String sourceString, String endpoint) throws JSONException {
        ArrayList<Question> questionsList = new ArrayList<Question>();
        String fileContents = FileManager.readWholeFile(fileDirectory);
        JSONObject obj = new JSONObject(fileContents);
        JSONArray arr = obj.getJSONArray("questions");
        for (int i = 0; i < arr.length(); i++) {

            JSONObject currentQuestionObject = arr.getJSONObject(i);
            
            
            //Question
            JSONArray questionVersions = currentQuestionObject.getJSONArray("questions");
            for (int k = 0; k < questionVersions.length(); k++) {
                Question question = new Question();
            question.setDatabase(endpoint);
            question.setFilepath(fileDirectory);
            // The source is predefined
            question.setQuestionSource(sourceString);

            //Answers
            try {
                    JSONArray answers = currentQuestionObject.getJSONArray("answers");
                    for (int j = 0; j < answers.length(); j++) {
                        // The question's answers
                        String answer = null;
                        try {
                            answer = (String) answers.get(j);
                        } catch (Exception e) {
                            answer = null;
                        }

                        question.addAnswer(answer);
                    }
                } catch (Exception e) {
                }
            
                question.setQuestionString(questionVersions.get(k).toString().replace("\n", ""));

                

                questionsList.add(question);
            }
        }
        return questionsList;
    }

    public static ArrayList<Question> parseTemp(String fileDirectory, String sourceString, String endpoint) throws JSONException {
        ArrayList<Question> questionsList = new ArrayList<Question>();
        String fileContents = FileManager.readWholeFile(fileDirectory);
        JSONObject obj = new JSONObject(fileContents);
        JSONArray arr = obj.getJSONArray("questions");
        for (int i = 0; i < arr.length(); i++) {

            Question question = new Question();
            question.setDatabase(endpoint);
            question.setFilepath(fileDirectory);
            // The source is predefined
            question.setQuestionSource(sourceString);

            JSONObject currentQuestionObject = arr.getJSONObject(i);
            question.setQuestionString(currentQuestionObject.getString("Question"));

            try {
                JSONArray answers = currentQuestionObject.getJSONArray("Gold answer");
                for (int j = 0; j < answers.length(); j++) {
                    // The question's answers
                    String answer = null;
                    try {
                        answer = (String) answers.get(j);
                    } catch (Exception e) {
                        answer = null;
                    }

                    question.addAnswer(answer);
                }
            } catch (Exception e) {
            }

            questionsList.add(question);
        }
        return questionsList;
    }

    public static ArrayList<Question> parseQuADFile(String fileDirectory, String sourceString, String endpoint) throws JSONException {
        ArrayList<Question> questionsList = new ArrayList<Question>();
        String fileContents = FileManager.readWholeFile(fileDirectory);
        JSONObject obj = new JSONObject(fileContents);
        JSONArray arr = obj.getJSONArray("questions");
        for (int i = 0; i < arr.length(); i++) {

            Question question = new Question();
            question.setDatabase(endpoint);
            question.setFilepath(fileDirectory);
            // The source is predefined
            question.setQuestionSource(sourceString);

            JSONObject currentQuestionObject = arr.getJSONObject(i);
            question.setQuestionString(currentQuestionObject.getString("corrected_question").replace("\n", ""));

            // The question's SPARQL Query
            question.setQuestionQuery(currentQuestionObject.getString("sparql_query"));
            if (question.getQuestionQuery() != null) {
                questionsList.add(question);
            }
        }
        return questionsList;
    }

    public static ArrayList<Question> parseNo(String fileDirectory, String sourceString, String endpoint) throws JSONException {
        ArrayList<Question> questionsList = new ArrayList<Question>();
        String fileContents = FileManager.readWholeFile(fileDirectory);
        JSONObject obj = new JSONObject(fileContents);
        JSONArray arr = obj.getJSONArray("questions");
        for (int i = 0; i < arr.length(); i++) {

            Question question = new Question();
            question.setDatabase(endpoint);
            question.setFilepath(fileDirectory);
            // The source is predefined
            question.setQuestionSource(sourceString);

            JSONObject currentQuestionObject = arr.getJSONObject(i);
            question.setQuestionString(currentQuestionObject.getString("Query").replace("\n", ""));

            questionsList.add(question);
        }
        return questionsList;
    }

    public static ArrayList<Question> parseFreeNo(String fileDirectory, String sourceString, String endpoint) throws JSONException {
        ArrayList<Question> questionsList = new ArrayList<Question>();
        String fileContents = FileManager.readWholeFile(fileDirectory);
        JSONObject obj = new JSONObject(fileContents);
        JSONArray arr = obj.getJSONArray("questions");
        for (int i = 0; i < arr.length(); i++) {

            Question question = new Question();
            question.setDatabase(endpoint);
            question.setFilepath(fileDirectory);
            // The source is predefined
            question.setQuestionSource(sourceString);

            JSONObject currentQuestionObject = arr.getJSONObject(i);
            question.setQuestionString(currentQuestionObject.getString("utterance").replace("\n", ""));

            try {
                JSONArray answers = currentQuestionObject.getJSONArray("targets");
                for (int j = 0; j < answers.length(); j++) {
                    // The question's answers
                    String answer = null;
                    try {
                        answer = (String) answers.get(j);
                    } catch (Exception e) {
                        answer = null;
                    }

                    question.addAnswer(answer);
                }
            } catch (Exception e) {
            }

            questionsList.add(question);
        }
        return questionsList;
    }

    public static ArrayList<Question> parseFreeNo2(String fileDirectory, String sourceString, String endpoint) throws JSONException {
        ArrayList<Question> questionsList = new ArrayList<Question>();
        String fileContents = FileManager.readWholeFile(fileDirectory);
        JSONObject obj = new JSONObject(fileContents);
        JSONArray arr = obj.getJSONArray("questions");
        for (int i = 0; i < arr.length(); i++) {

            Question question = new Question();
            question.setDatabase(endpoint);
            question.setFilepath(fileDirectory);
            // The source is predefined
            question.setQuestionSource(sourceString);

            JSONObject currentQuestionObject = arr.getJSONObject(i);
            question.setQuestionString(currentQuestionObject.getString("question").replace("\n", ""));

            try {
                JSONArray answers = currentQuestionObject.getJSONArray("answers");
                for (int j = 0; j < answers.length(); j++) {
                    // The question's answers
                    String answer = null;
                    try {
                        answer = (String) answers.get(j);
                    } catch (Exception e) {
                        answer = null;
                    }

                    question.addAnswer(answer);
                }
            } catch (Exception e) {
            }

            questionsList.add(question);
        }
        return questionsList;
    }

    public static ArrayList<Question> parseFreeNo3(String fileDirectory, String sourceString, String endpoint) throws JSONException {
        ArrayList<Question> questionsList = new ArrayList<Question>();
        String fileContents = FileManager.readWholeFile(fileDirectory);
        JSONObject obj = new JSONObject(fileContents);
        JSONArray arr = obj.getJSONArray("questions");
        for (int i = 0; i < arr.length(); i++) {

            Question question = new Question();
            question.setDatabase(endpoint);
            question.setFilepath(fileDirectory);
            // The source is predefined
            question.setQuestionSource(sourceString);

            JSONObject currentQuestionObject = arr.getJSONObject(i);
            question.setQuestionString(currentQuestionObject.getJSONArray("questions").get(0).toString().replace("\n", ""));

            try {
                JSONArray answers = currentQuestionObject.getJSONArray("answers");
                for (int j = 0; j < answers.length(); j++) {
                    // The question's answers
                    String answer = null;
                    try {
                        answer = (String) answers.get(j);
                    } catch (Exception e) {
                        answer = null;
                    }

                    question.addAnswer(answer);
                }
            } catch (Exception e) {
            }

            questionsList.add(question);
        }
        return questionsList;
    }

    public static ArrayList<Question> parseGra(String fileDirectory, String sourceString, String endpoint) throws JSONException {
        ArrayList<Question> questionsList = new ArrayList<Question>();
        String fileContents = FileManager.readWholeFile(fileDirectory);
        JSONObject obj = new JSONObject(fileContents);
        JSONArray arr = obj.getJSONArray("questions");
        for (int i = 0; i < arr.length(); i++) {

            Question question = new Question();
            question.setDatabase(endpoint);
            question.setFilepath(fileDirectory);
            // The source is predefined
            question.setQuestionSource(sourceString);

            JSONObject currentQuestionObject = arr.getJSONObject(i);
            question.setQuestionString(currentQuestionObject.getString("question").replace("\n", ""));
            // The question's SPARQL Query
            question.setQuestionQuery(currentQuestionObject.getString("sparql_query"));

            try {
                JSONArray answers = currentQuestionObject.getJSONArray("answer");
                for (int j = 0; j < answers.length(); j++) {
                    // The question's answers
                    String answer = null;
                    try {
                        answer = (String) answers.get(j);
                    } catch (Exception e) {
                        answer = null;
                    }

                    question.addAnswer(answer);
                }
            } catch (Exception e) {
            }

            questionsList.add(question);
        }
        return questionsList;
    }

    public static ArrayList<Question> parseWeb(String fileDirectory, String sourceString, String endpoint) throws JSONException {
        ArrayList<Question> questionsList = new ArrayList<Question>();
        String fileContents = FileManager.readWholeFile(fileDirectory);
        JSONObject obj = new JSONObject(fileContents);
        JSONArray arr = obj.getJSONArray("questions");
        for (int i = 0; i < arr.length(); i++) {

            Question question = new Question();
            question.setDatabase(endpoint);
            question.setFilepath(fileDirectory);
            // The source is predefined
            question.setQuestionSource(sourceString);

            JSONObject currentQuestionObject = arr.getJSONObject(i);
            question.setQuestionString(currentQuestionObject.getString("RawQuestion").replace("\n", ""));
            // The question's SPARQL Query
            try {
                question.setQuestionQuery(((JSONObject) currentQuestionObject.getJSONArray("Parses").get(0)).getString("Sparql"));
            } catch (Exception e) {
                question.setQuestionQuery(null);
            }

            try {
                JSONArray answers = ((JSONObject) currentQuestionObject.getJSONArray("Parses").get(0)).getJSONArray("Answers");
                for (int j = 0; j < answers.length(); j++) {
                    // The question's answers
                    String answer = null;
                    try {
                        answer = ((JSONObject) (answers.get(j))).getString("EntityName");
                    } catch (Exception e) {
                        answer = null;
                    }

                    question.addAnswer(answer);
                }
            } catch (Exception e) {
            }

            questionsList.add(question);

        }
        return questionsList;
    }

    public static ArrayList<Question> parseQald9File(String fileDirectory, String sourceString, String endpoint) {
        ArrayList<Question> questionsList = new ArrayList<Question>();
        String fileContents = FileManager.readWholeFile(fileDirectory);
        JSONObject obj = new JSONObject(fileContents);
        JSONArray arr = obj.getJSONArray("questions");
        for (int i = 0; i < arr.length(); i++) {
            Question question = new Question();
            question.setDatabase(endpoint);
            question.setFilepath(fileDirectory);
            // The source is predefined
            question.setQuestionSource(sourceString);

            JSONObject currentQuestionObject = arr.getJSONObject(i);
            JSONArray objectArr = currentQuestionObject.getJSONArray("question");
            // Finding question in English
            for (int j = 0; j < objectArr.length(); ++j) {
                JSONObject currentQuestionLanguageObject = objectArr.getJSONObject(j);
                if (currentQuestionLanguageObject.getString("language").compareTo("en") == 0) {
                    question.setQuestionString(currentQuestionLanguageObject.getString("string").replace("\n", ""));
                    if (currentQuestionLanguageObject.getString("language").compareTo("en") == 0) {
                        question.setKeywords(currentQuestionLanguageObject.getString("keywords").replace("\n", ""));

                    }
                    break;
                }
            }
            // The question's SPARQL Query
            final String XSD_PREFIX = "PREFIX xsd:   <http://www.w3.org/2001/XMLSchema#> ";
            final String DBO_PREFIX = "PREFIX dbo: <http://dbpedia.org/ontology/> ";
            final String DBR_PREFIX = "PREFIX dbr: <http://dbpedia.org/resource/> ";
            final String DCT_PREFIX = "PREFIX dct: <http://purl.org/dc/terms/> ";
            final String DBP_PREFIX = "PREFIX dbp: <http://dbpedia.org/property/> ";
            final String DBC_PREFIX = "PREFIX dbc: <http://dbpedia.org/resource/Category:> ";
            final String YAGO_PREFIX = "PREFIX yago: <http://dbpedia.org/class/yago/> ";
            final String PREFIX = XSD_PREFIX+DBO_PREFIX+DBR_PREFIX+DCT_PREFIX+DBP_PREFIX+DBC_PREFIX+YAGO_PREFIX;
            String query = currentQuestionObject.getJSONObject("query").getString("sparql");
            if(null != query && query.length() > 0) {
                query = PREFIX+query;
            }
            question.setQuestionQuery(query);
            // The question's answers
            JSONArray answersArray = currentQuestionObject.getJSONArray("answers");
            for (int j = 0; j < answersArray.length(); ++j) {
                if (answersArray.getJSONObject(j).getJSONObject("results").isEmpty()) {
                    if (answersArray.getJSONObject(j).getBoolean("boolean") == true) {
                        question.addAnswer("Yes");
                    } else {
                        question.addAnswer("No");
                    }

                } else {
                    JSONArray resultsArray = answersArray.getJSONObject(j).getJSONObject("results").getJSONArray("bindings");
                    for (int k = 0; k < resultsArray.length(); ++k) {
                        Set<String> keysSet = resultsArray.getJSONObject(k).keySet();
                        Iterator<String> it = keysSet.iterator();
                        question.addAnswer(resultsArray.getJSONObject(k).getJSONObject(it.next()).getString("value"));
                    }
                }

            }
            if (question.getQuestionQuery() != null) {
                questionsList.add(question);
            }
        }
        return questionsList;
    }
    
    public static ArrayList<Question> parseQald8File(String fileDirectory, String sourceString, String endpoint) {
        return parseQald9File(fileDirectory, sourceString, endpoint);
    }

    public static ArrayList<Question> parseQald7File1(String fileDirectory, String sourceString, String endpoint) {
        ArrayList<Question> questionsList = new ArrayList<Question>();

        String fileContents = FileManager.readWholeFile(fileDirectory);

        JSONObject obj = new JSONObject(fileContents);
        JSONArray arr = obj.getJSONArray("questions");
        for (int i = 0; i < arr.length(); i++) {
            Question question = new Question();
            // The source is predefined
            question.setQuestionSource(sourceString);
            question.setDatabase(endpoint);
            question.setFilepath(fileDirectory);
            JSONObject currentQuestionObject = arr.getJSONObject(i);
            JSONObject questionObject = currentQuestionObject.getJSONObject("question");
            JSONArray languageArr = questionObject.getJSONArray("language");
            for (int j = 0; j < languageArr.length(); ++j) {
                if (languageArr.getJSONObject(j).getString("language").compareTo("en") == 0) {
                    question.setQuestionQuery(languageArr.getJSONObject(j).getString("SPARQL"));
                    question.setQuestionString(languageArr.getJSONObject(j).getString("question").replace("\n", ""));
                    if (languageArr.getJSONObject(j).getString("language").compareTo("en") == 0) {
                        question.setKeywords(languageArr.getJSONObject(j).getString("keywords").replace("\n", ""));
                    }
                    break;
                }
            }
            JSONObject answers = questionObject.getJSONObject("answers");

            JSONObject head = answers.getJSONObject("head");

            if (head.isEmpty()) {
                if (answers.getBoolean("boolean") == true) {
                    question.addAnswer("Yes");
                } else {
                    question.addAnswer("No");
                }
            } else {
                JSONArray varsArray = head.getJSONArray("vars");
                String type = (String) varsArray.get(0);
                JSONObject results = answers.getJSONObject("results");
                JSONArray bindingsArray = results.getJSONArray("bindings");
                for (int j = 0; j < bindingsArray.length(); ++j) {
                    question.addAnswer(bindingsArray.getJSONObject(j).getJSONObject(type).getString("value"));
                }
            }
            if (question.getQuestionQuery() != null) {
                questionsList.add(question);
            }
        }
        return questionsList;
    }

    public static ArrayList<Question> parseQald7File2(String fileDirectory, String sourceString, String endpoint) {
        ArrayList<Question> questionsList = new ArrayList<Question>();
        String fileContents = FileManager.readWholeFile(fileDirectory);
        JSONObject obj = new JSONObject(fileContents);
        JSONArray arr = obj.getJSONArray("questions");
        for (int i = 0; i < arr.length(); i++) {
            Question question = new Question();
            // The source is predefined
            question.setQuestionSource(sourceString);
            question.setDatabase(endpoint);
            question.setFilepath(fileDirectory);
            JSONObject currentQuestionObject = arr.getJSONObject(i);
            String type = currentQuestionObject.getString("answertype");
            JSONArray questionArray = currentQuestionObject.getJSONArray("question");
            for (int j = 0; j < questionArray.length(); ++j) {
                if (questionArray.getJSONObject(j).getString("language").compareTo("en") == 0) {
                    question.setQuestionString(questionArray.getJSONObject(j).getString("string").replace("\n", ""));
                    if (questionArray.getJSONObject(j).getString("language").compareTo("en") == 0) {
                        question.setKeywords(questionArray.getJSONObject(j).getString("keywords").replace("\n", ""));
                        break;
                    }
                    break;
                }
            }
            // This case checks if there's no query in the data set
            // This is encountered in the qald-6-train-multilingual.json file
            if (currentQuestionObject.getJSONObject("query").isEmpty()) {
                continue;
            }
            question.setQuestionQuery(currentQuestionObject.getJSONObject("query").getString("sparql"));
            JSONArray answersArray = currentQuestionObject.getJSONArray("answers");
            if (type.compareTo("boolean") == 0) {
                if (answersArray.getJSONObject(0).getBoolean(type) == true) {
                    question.addAnswer("Yes");
                } else {
                    question.addAnswer("No");
                }
            } else {
                for (int j = 0; j < answersArray.length(); ++j) {
                    JSONObject answer = answersArray.getJSONObject(j);
                    JSONObject head = answer.getJSONObject("head");
                    JSONArray varsArray = head.getJSONArray("vars");
                    ArrayList<String> variables = new ArrayList<String>();
                    for (int k = 0; k < varsArray.length(); ++k) {
                        variables.add(varsArray.getString(k));
                    }
                    JSONObject results = answer.getJSONObject("results");

                    JSONArray bindingsArray = results.getJSONArray("bindings");
                    for (int k = 0; k < bindingsArray.length(); ++k) {
                        String answerString = "";
                        for (int l = 0; l < variables.size(); ++l) {
                            answerString += bindingsArray.getJSONObject(k).getJSONObject(variables.get(l)).getString("value");
                            if (l != variables.size() - 1) {
                                answerString += ",";
                            }
                        }
                        question.addAnswer(answerString);
                    }
                }
            }
            if (question.getQuestionQuery() != null) {
                questionsList.add(question);
            }
        }
        return questionsList;
    }

    public static ArrayList<Question> parseQald7File3(String fileDirectory, String sourceString, String endpoint) throws JSONException {
        ArrayList<Question> questionsList = new ArrayList<Question>();
        String fileContents = FileManager.readWholeFile(fileDirectory);
        JSONObject obj = new JSONObject(fileContents);
        JSONArray arr = obj.getJSONArray("questions");
        for (int i = 0; i < arr.length(); i++) {
            Question question = new Question();
            // The source is predefined
            question.setQuestionSource(sourceString);
            question.setDatabase(endpoint);
            question.setFilepath(fileDirectory);
            JSONObject currQuestion = arr.getJSONObject(i).getJSONObject("question");

            for (int j = 0; j < currQuestion.getJSONArray("language").length(); j++) {
                if (currQuestion.getJSONArray("language").getJSONObject(j).getString("language").compareTo("en") == 0) {
                    try {
                        if (currQuestion.getJSONArray("language").getJSONObject(j).getString("SPARQL").isEmpty()) {
                            continue;
                        }
                    } catch (Exception e) {
                        return questionsList;
                    }
                    question.setQuestionString(currQuestion.getJSONArray("language").getJSONObject(j).getString("question").replace("\n", ""));
                }
            }
            //Get answers
            ArrayList<String> answers = new ArrayList<String>();
            if (currQuestion.getString("answertype").compareTo("resource") == 0) {
                for (int j = 0; j < currQuestion.getJSONObject("answers").getJSONObject("results").getJSONArray("bindings").length(); j++) {
                    answers.add(currQuestion.getJSONObject("answers").getJSONObject("results").getJSONArray("bindings").getJSONObject(j).getJSONObject("uri").getString("value"));
                }
            } else if (currQuestion.getString("answertype").compareTo("boolean") == 0) {
                if (currQuestion.getJSONObject("answers").getBoolean("boolean")) {
                    answers.add("true");
                } else {
                    answers.add("false");
                }
            } else if (currQuestion.getString("answertype").compareTo("date") == 0) {
                for (int j = 0; j < currQuestion.getJSONObject("answers").getJSONObject("results").getJSONArray("bindings").length(); j++) {
                    answers.add(currQuestion.getJSONObject("answers").getJSONObject("results").getJSONArray("bindings").getJSONObject(j).getJSONObject("date").getString("value"));
                }
            } else if (currQuestion.getString("answertype").compareTo("number") == 0) {
                for (int j = 0; j < currQuestion.getJSONObject("answers").getJSONObject("results").getJSONArray("bindings").length(); j++) {
                    answers.add(currQuestion.getJSONObject("answers").getJSONObject("results").getJSONArray("bindings").getJSONObject(j).getJSONObject("c").getString("value"));
                }
            } else if (currQuestion.getString("answertype").compareTo("string") == 0) {
                for (int j = 0; j < currQuestion.getJSONObject("answers").getJSONObject("results").getJSONArray("bindings").length(); j++) {
                    answers.add(currQuestion.getJSONObject("answers").getJSONObject("results").getJSONArray("bindings").getJSONObject(j).getJSONObject("string").getString("value"));
                }
            }
            question.setAnswers(answers);
            if (question.getQuestionQuery() != null) {
                questionsList.add(question);
            }
        }
        return questionsList;
    }

    public static ArrayList<Question> parseQald7File4(String fileDirectory, String sourceString, String endpoint) throws JSONException {
        ArrayList<Question> questionsList = new ArrayList<Question>();
        String fileContents = FileManager.readWholeFile(fileDirectory);
        JSONObject obj = new JSONObject(fileContents);
        JSONArray arr = obj.getJSONArray("questions");
        for (int i = 0; i < arr.length(); i++) {
            Question question = new Question();
            // The source is predefined
            question.setQuestionSource(sourceString);
            question.setDatabase(endpoint);
            question.setFilepath(fileDirectory);
            JSONArray currQuestion = arr.getJSONObject(i).getJSONArray("question");

            for (int j = 0; j < currQuestion.length(); j++) {
                try {
                    if (arr.getJSONObject(i).getJSONObject("query").getString("pseudo").isEmpty()) {
                        continue;
                    } else {
                        String queryTemp = arr.getJSONObject(i).getJSONObject("query").getString("pseudo");
                        if (queryTemp.charAt(0) == ' ') {
                            queryTemp.replaceFirst(" ", "");
                        }
                        question.setQuestionQuery(queryTemp);
                    }
                } catch (Exception e) {
                    return questionsList;
                }
                question.setQuestionString(currQuestion.getJSONObject(j).getString("string").replace("\n", ""));

            }
            //Get answers

            ArrayList<String> answers = new ArrayList<String>();
            if (arr.getJSONObject(i).getString("answertype").compareTo("resource") == 0) {
                for (int j = 0; j < arr.getJSONObject(i).getJSONArray("answers").getJSONObject(0).getJSONObject("results").getJSONArray("bindings").length(); j++) {
                    answers.add(arr.getJSONObject(i).getJSONArray("answers").getJSONObject(0).getJSONObject("results").getJSONArray("bindings").getJSONObject(j).getJSONObject("uri").getString("value"));
                }
            } else if (arr.getJSONObject(i).getString("answertype").compareTo("boolean") == 0) {
                if (arr.getJSONObject(i).getJSONArray("answers").getJSONObject(0).getBoolean("boolean")) {
                    answers.add("true");
                } else {
                    answers.add("false");
                }
            } else if (arr.getJSONObject(i).getString("answertype").compareTo("date") == 0) {
                for (int j = 0; j < arr.getJSONObject(i).getJSONArray("answers").getJSONObject(0).getJSONObject("results").getJSONArray("bindings").length(); j++) {
                    answers.add(arr.getJSONObject(i).getJSONArray("answers").getJSONObject(0).getJSONObject("results").getJSONArray("bindings").getJSONObject(j).getJSONObject("date").getString("value"));
                }
            } else if (arr.getJSONObject(i).getString("answertype").compareTo("number") == 0) {
                for (int j = 0; j < arr.getJSONObject(i).getJSONArray("answers").getJSONObject(0).getJSONObject("results").getJSONArray("bindings").length(); j++) {
                    answers.add(arr.getJSONObject(i).getJSONArray("answers").getJSONObject(0).getJSONObject("results").getJSONArray("bindings").getJSONObject(j).getJSONObject("c").getString("value"));
                }
            } else if (arr.getJSONObject(i).getString("answertype").compareTo("string") == 0) {
                for (int j = 0; j < arr.getJSONObject(i).getJSONArray("answers").getJSONObject(0).getJSONObject("results").getJSONArray("bindings").length(); j++) {
                    answers.add(arr.getJSONObject(i).getJSONArray("answers").getJSONObject(0).getJSONObject("results").getJSONArray("bindings").getJSONObject(j).getJSONObject("string").getString("value"));
                }
            }
            question.setAnswers(answers);
            if (question.getQuestionQuery() != null) {
                questionsList.add(question);
            }
        }
        return questionsList;
    }

    public static ArrayList<Question> parseQald5(String fileDirectory, String sourceString, String endpoint) throws JSONException {
        ArrayList<Question> questionsList = new ArrayList<Question>();
        String fileContents = FileManager.readWholeFile(fileDirectory);
        JSONObject obj = new JSONObject(fileContents);
        JSONArray arr = obj.getJSONArray("questions");
        for (int i = 0; i < arr.length(); i++) {
            // Skip the hybrid questions 
            if (arr.getJSONObject(i).getString("hybrid").compareTo("true") == 0) {
                continue;
            }
            Question question = new Question();
            // The source is predefined
            question.setQuestionSource(sourceString);
            question.setDatabase(endpoint);
            question.setFilepath(fileDirectory);
            JSONObject currentQuestionObject = arr.getJSONObject(i);
            String type = currentQuestionObject.getString("answertype");
            JSONArray bodyArray = currentQuestionObject.getJSONArray("body");
            for (int j = 0; j < bodyArray.length(); ++j) {
                if (bodyArray.getJSONObject(j).getString("language").compareTo("en") == 0) {
                    question.setQuestionString(bodyArray.getJSONObject(j).getString("string").replace("\n", ""));
                    if (bodyArray.getJSONObject(j).getString("language").compareTo("en") == 0) {
                        question.setKeywords(bodyArray.getJSONObject(j).getString("keywords").replace("\n", ""));

                    }
                    break;
                }
            }
            question.setQuestionQuery(currentQuestionObject.getString("query"));
            JSONArray answersArray = currentQuestionObject.getJSONArray("answers");
            for (int j = 0; j < answersArray.length(); ++j) {
                if (type.compareTo("boolean") == 0) {
                    if (answersArray.getJSONObject(j).getString("string").compareTo("true") == 0) {
                        question.addAnswer("Yes");
                    } else {
                        question.addAnswer("No");
                    }
                } else {
                    question.addAnswer(answersArray.getJSONObject(j).getString("string"));
                }
            }
            if (question.getQuestionQuery() != null) {
                questionsList.add(question);
            }
        }
        return questionsList;
    }

    public static ArrayList<Question> parseQald7File6(String fileDirectory, String sourceString, String endpoint) throws JSONException {
        ArrayList<Question> questionsList = new ArrayList<Question>();
        String fileContents = FileManager.readWholeFile(fileDirectory);
        JSONObject obj = new JSONObject(fileContents);
        JSONArray arr = obj.getJSONArray("questions");
        for (int i = 0; i < arr.length(); i++) {
            // Skip the hybrid questions 
            if (arr.getJSONObject(i).getString("hybrid").compareTo("true") == 0) {
                continue;
            }
            Question question = new Question();
            // The source is predefined
            question.setQuestionSource(sourceString);
            question.setDatabase(endpoint);
            question.setFilepath(fileDirectory);
            JSONObject currentQuestionObject = arr.getJSONObject(i);
            String type = currentQuestionObject.getString("answertype");
            JSONArray bodyArray = currentQuestionObject.getJSONArray("question");

            question.setQuestionString(bodyArray.getJSONObject(0).getString("string").replace("\n", ""));

            question.setQuestionQuery(currentQuestionObject.getJSONObject("query").getString("pseudo"));
            JSONArray answersArray = currentQuestionObject.getJSONArray("answers");
            ArrayList<String> answers = new ArrayList<String>();
            if (arr.getJSONObject(i).getString("answertype").compareTo("resource") == 0) {
                for (int j = 0; j < arr.getJSONObject(i).getJSONArray("answers").getJSONObject(0).getJSONObject("results").getJSONArray("bindings").length(); j++) {
                    answers.add(arr.getJSONObject(i).getJSONArray("answers").getJSONObject(0).getJSONObject("results").getJSONArray("bindings").getJSONObject(j).getJSONObject("uri").getString("value"));
                }
            } else if (arr.getJSONObject(i).getString("answertype").compareTo("boolean") == 0) {
                if (arr.getJSONObject(i).getJSONArray("answers").getJSONObject(0).getBoolean("boolean")) {
                    answers.add("true");
                } else {
                    answers.add("false");
                }
            } else if (arr.getJSONObject(i).getString("answertype").compareTo("date") == 0) {
                for (int j = 0; j < arr.getJSONObject(i).getJSONArray("answers").getJSONObject(0).getJSONObject("results").getJSONArray("bindings").length(); j++) {
                    answers.add(arr.getJSONObject(i).getJSONArray("answers").getJSONObject(0).getJSONObject("results").getJSONArray("bindings").getJSONObject(j).getJSONObject("date").getString("value"));
                }
            } else if (arr.getJSONObject(i).getString("answertype").compareTo("number") == 0) {
                for (int j = 0; j < arr.getJSONObject(i).getJSONArray("answers").getJSONObject(0).getJSONObject("results").getJSONArray("bindings").length(); j++) {
                    answers.add(arr.getJSONObject(i).getJSONArray("answers").getJSONObject(0).getJSONObject("results").getJSONArray("bindings").getJSONObject(j).getJSONObject("c").getString("value"));
                }
            } else if (arr.getJSONObject(i).getString("answertype").compareTo("string") == 0) {
                for (int j = 0; j < arr.getJSONObject(i).getJSONArray("answers").getJSONObject(0).getJSONObject("results").getJSONArray("bindings").length(); j++) {
                    answers.add(arr.getJSONObject(i).getJSONArray("answers").getJSONObject(0).getJSONObject("results").getJSONArray("bindings").getJSONObject(j).getJSONObject("string").getString("value"));
                }
            }

            question.setAnswers(answers);
            if (question.getQuestionQuery() != null) {
                questionsList.add(question);
            }
        }
        return questionsList;
    }
}
