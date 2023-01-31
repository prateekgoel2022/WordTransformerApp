package org.example;

import org.example.model.Input;
import org.example.util.WordTransformerUtil;
import java.util.*;
import java.util.stream.Collectors;


class WordTransformer {



    private class Node {
        public String value;
        public Node previousNode;
        Node(String value) { this.value = value; }
        Node(String value, Node previousNode) {
            this.value = value;
            this.previousNode = previousNode;
        }
    }

    public String findTransformations(String startWord, String endWord, List<String> wordList) {
        if(!wordList.contains(endWord)){
            return "ERROR: "+endWord+" not found in wordlist.";
        }
        List<String> resultArrayList = new ArrayList<>();
        Set<String> inputWordSet = new HashSet<>(wordList);
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(startWord));
        Outer:
        while (!queue.isEmpty()) {
            int size = queue.size();
            Inner:
            while(size >0){
                Node head = queue.poll();
                for (String connectionString : getConnectionSet(inputWordSet, head.value)) {
                    Node connection = new Node(connectionString, head);
                    if (connection.value.equals(endWord)) {
                        addNode(resultArrayList, connection);
                        break Outer;
                    }
                    queue.offer(connection);
                }
                size--;
            }
            if (resultArrayList.size() > 0) break;
        }
        return prepareResultString(resultArrayList, startWord, endWord);
    }



    private Set<String> getConnectionSet(Set<String> list, String s) {
        list.remove(s);
        Set<String> resultSet = new HashSet<>();
        char[] charArray = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            for (char ch = 'a'; ch <= 'z'; ch++) {
                if (charArray[i] == ch) continue;
                char temp = charArray[i];
                charArray[i] = ch;
                String word = new String(charArray);
                if (list.contains(word)) resultSet.add(word);
                charArray[i] = temp;
            }
        }
        return resultSet;
    }

    private void addNode(List<String> list, Node p) {
        LinkedList<String> stringLinkedList = new LinkedList<>();
        while (p != null) {
            stringLinkedList.addFirst(p.value);
            p = p.previousNode;
        }
       list.addAll(stringLinkedList);
    }



    private String prepareResultString(List<String> resultArrayList, String startWord, String endWord){

        if(resultArrayList.size() > 0) {
            return resultArrayList.stream().
                    collect(Collectors.joining(","));
        }else{
            return "Cannot transform "+startWord+" to "+endWord;

        }

    }



    public static void main(String[] args) {
        String filePath = "src/main/resources/"+args[0];
        WordTransformer transformer =new WordTransformer();
        Input input = WordTransformerUtil.readWordList(filePath);
        if(input != null) {
            System.out.println(transformer.findTransformations(input.getStartWord(), input.getEndWord(), input.getWordList()));
        }else{
            System.out.println("ERROR: File not found");
        }
    }
}

