import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class stopwords{
    public static void main(String[] args) {
        String GPT2 = "GPT2";
        String stopwords = "stopwords";
        List <String> listRemovedWords = removeStopwords(GPT2, stopwords);
        System.out.println("Unsorted");
        System.out.println(listRemovedWords);
        System.out.println("Insertion");
        System.out.println(insertionSort(listRemovedWords));
        System.out.println("Merge");
        System.out.println(mergeSort(listRemovedWords));
    }

    public static List<String> mergeSort(List <String> S){
        List <String> S1 = new ArrayList<>();
        List <String> S2 = new ArrayList<>();
        
        if (S.size() < 2){
            return S;
        }

        Integer i = 0;
        for (String str: S){
            if (i < S.size()/2){
                S1.add(str);
            }
            else{
                S2.add(str);
            }
            i++;
        }
        S1 = mergeSort(S1);
        S2 = mergeSort(S2);
        return merge(S1, S2);
    }

    public static List<String> merge(List<String> S1, List<String> S2){
        List <String> S = new ArrayList<>();
        while (S1.size() > 0 && S2.size() > 0){
            if (S1.get(0).toLowerCase().compareTo(S2.get(0).toLowerCase()) <= 0){
                S.add(S1.get(0));
                S1.remove(0);
            }
            else{
                S.add(S2.get(0));
                S2.remove(0);
            }
        }
        Iterator<String> iter1 = S1.iterator();
        while(iter1.hasNext()){
            String num = iter1.next();
            S.add(S1.get(0));
            iter1.remove();
        }
        Iterator<String> iter2 = S2.iterator();
        while (iter2.hasNext()){
            String num = iter2.next();
            S.add(S2.get(0));
            iter2.remove();
        }
        return S;
    }


    public static List<String> removeStopwords(String GPT2, String stopwords){
        String stopWordsFilename = stopwords + ".txt";
        String GPT2Filename = GPT2 + ".txt" ;
        List<Integer> addIndex = new ArrayList<>();
        List<String> finalList = new ArrayList<>();
        try{
            readFile stopWordsFile = new readFile(stopWordsFilename);
            readFile GPT2File = new readFile(GPT2Filename);
            List<String> listGPT2Lines = GPT2File.openFile();
            List<String> listGPT2Words = new ArrayList<>();
            for (int i = 0; i < listGPT2Lines.size(); i++){
                String[] splitLine = listGPT2Lines.get(i).split("\\s");
                for (int j = 0; j < splitLine.length; j++){
                    listGPT2Words.add(splitLine[j].replaceAll("\\W", "").toLowerCase());
                }
            }
            List<String> listSWords = stopWordsFile.openFile();
            for (int i = 0; i < listGPT2Words.size(); i++){
                Boolean found = listSWords.contains(listGPT2Words.get(i).toLowerCase());
                if (found == false){
                    if (listGPT2Words.get(i) != "\\s+"){
                        addIndex.add(i);
                    }
                }
            }
            for(int i = 0; i < addIndex.size(); i++){
                finalList.add(listGPT2Words.get(addIndex.get(i)));
            }
        }
        
        catch(IOException e){
            System.out.println("Error");
        }
        
        return finalList;
    }
    public static List<String> insertionSort(List<String> removedWordsList){
        List<String> listSortedWords = new ArrayList<>();
        listSortedWords = removedWordsList;
        for (int i = 1; i < listSortedWords.size(); i++){
            String item = listSortedWords.get(i);
            Integer j = i - 1;
            while (j >= 0 && listSortedWords.get(j).toLowerCase().compareTo(item.toLowerCase()) >= 0){
                listSortedWords.set((j + 1), (listSortedWords.get(j)));
                j = j - 1;
            }
            listSortedWords.set(j + 1, item);
        }
        return listSortedWords;   
    }
}