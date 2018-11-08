package roomalaisetnumerot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class RomanNumerals {
    HashMap<Character, Integer> basicChars;
    ArrayList<Character> values;
    
    public RomanNumerals() {
        this.values = new ArrayList<>(Arrays.asList('I', 'V', 'X', 'L', 'C', 'D', 'M'));
        this.basicChars = new HashMap<>();

        for (int i = 0; i < values.size(); i++) {
            if (this.basicChars.isEmpty()) {
                this.basicChars.put(values.get(i), 1);
            } else if (i % 2 == 0) {
                this.basicChars.put(values.get(i), this.basicChars.get(values.get(i-1)) * 2);
            } else {
                this.basicChars.put(values.get(i), this.basicChars.get(values.get(i-1)) * 5);
            }
        }
    }
    
    public Integer getNumber(String input) {
        if (!validate(input)) {
            return -1;
        }
        
        ArrayList<String> subInputs = new ArrayList<>();
        int startAt = 0;
        String reversedInput = new StringBuilder(input).reverse().toString();

        for (int i = 0; i < reversedInput.length(); i++) {
            if (i+1 <= reversedInput.length()-1 && (basicChars.get(reversedInput.charAt(i)) <= basicChars.get(reversedInput.charAt(i+1)))) {
                subInputs.add(reversedInput.substring(startAt, i+1));
                startAt = i+1;            
            } else if (i == reversedInput.length() - 1) {
                subInputs.add(reversedInput.substring(startAt, i+1));
            }
        }
        
        return calculateSum(subInputs);
    }

    private Integer calculateSum(ArrayList<String> subInputs) {
        int total = 0;
        for (String subInput : subInputs) {
            if (subInput.length() == 2) {
                total += basicChars.get(subInput.charAt(0))-basicChars.get(subInput.charAt(1));
            } else {
                total += basicChars.get(subInput.charAt(0));  
            }           
        }
 
        return total;
    }
    
    private boolean validate(String input) {
        // validointi ei toimi kovin hyvin
        
        HashMap<String, ArrayList<String>> accepted = new HashMap<>();

        for (int i = 0; i < values.size(); i++) {   
            if(i % 2 == 0) {
                ArrayList<String> multiplyByTwo = new ArrayList<>();
                for (int j = 0; j < i; j++) {
                    if (j % 2 == 0) {
                        multiplyByTwo.add(values.get(j) + "");
                        multiplyByTwo.add(values.get(j) + "" + values.get(j));
                        multiplyByTwo.add(values.get(j) + "" + values.get(j) + values.get(j));       
                    } else {
                        multiplyByTwo.add(values.get(j) + "");
                    }
                }
            
                if (values.size() >= i+1) {
                    multiplyByTwo.add(values.get(i) + "");
                    multiplyByTwo.add(values.get(i) + "" + values.get(i));
                }
                if (values.size() >= i+2) {
                    multiplyByTwo.add(values.get(i + 1) + "");
                }
                if (values.size() >= i+3) {
                    multiplyByTwo.add(values.get(i + 2) + "");
                }
                accepted.put(values.get(i) + "", multiplyByTwo);
            } else {
                ArrayList<String> multiplyByFive = new ArrayList<>();
                for (int j = 0; j < i-1; j++) {
                    multiplyByFive.add(values.get(j) + "");                        
                }
                multiplyByFive.add(values.get(i-1) + "");
                multiplyByFive.add(values.get(i-1) + "" + values.get(i-1));
                multiplyByFive.add(values.get(i-1) + "" + values.get(i-1) + values.get(i-1));
                accepted.put(values.get(i) + "", multiplyByFive);
            }
        }
                            
        for (int i = 0; i < input.length(); i++) {
            if (!accepted.containsKey(input.charAt(i) + "")) {
                return false;
            }
            if (i < input.length()-1 && !accepted.get(input.charAt(i) + "").contains(input.charAt(i+1) + "")) {
                return false;
            }
        }

        return true;
    }
   
}
