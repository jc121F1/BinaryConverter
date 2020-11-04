import java.util.ArrayList;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        System.out.println("""
                Input integer to select your choice\s
                1: Convert unsigned binary to decimal\s
                2. Convert signed binary to decimal\s
                3. Convert ones complement to decimal\s
                4. Convert two's complement to decimal""");
        Scanner in = new Scanner(System.in);
        boolean validated = false;
        String input = in.nextLine();
        while (!validated) {
            if (input.matches("[1234]{1}")) {
                validated = true;
            }
            else {
                System.out.println("Incorrect input. Please input a single digit");
                input = in.nextLine();
            }
        }
        int userChoice = Integer.parseInt(input);
        switch (userChoice) {
            case 1 ->
                    System.out.println(calculateUnsignedBinary(getSplit()));
            case 2 ->
                    System.out.println(calculateSignedBinary(getSplit()));
            case 3 ->
                    System.out.println(calculateOnesComplement(getSplit()));
            case 4 ->
                    System.out.println(calculateTwosComplement(getSplit()));
        }
    }
    public static ArrayList<Integer> getSplit(){
        String binaryString = getInput();
        return splitInput(binaryString);
    }

    public static String getInput(){
        boolean finished = false;
        Scanner in = new Scanner(System.in);
        String binaryNum = "";

        System.out.println("Please input your binary number");
        while(!finished){
            binaryNum = in.nextLine();
            for(int i = 0; i<binaryNum.length(); i++){
                if(binaryNum.matches("[^01]")){
                    System.out.println("Error, input contains non binary digits. Please input again");
                    binaryNum = in.nextLine();
                }
                else{
                    finished = true;
                }
            }
        }

        return binaryNum;
    }

    public static ArrayList<Integer> splitInput(String binaryIn){
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i < binaryIn.length();i++){
            list.add((Integer.parseInt(String.valueOf(binaryIn.charAt(i)))));
        }
        return list;
    }

    public static int calculateUnsignedBinary(ArrayList<Integer> binaryList){
        int sum = 0;
        double power;
        for(int i = 0; i < binaryList.size(); i++){
            power = binaryList.size()-1 - i;
            sum += binaryList.get(i) * Math.pow(2, power);

        }
        return sum;
    }

    public static int calculateSignedBinary(ArrayList<Integer> binaryList){
        int sum = 0;
        double power;
        for(int i = 1; i < binaryList.size(); i++) {
            power = binaryList.size() - 1 - i;
            sum += binaryList.get(i) * Math.pow(2, power);
        }
        if(binaryList.get(0) == 1){
            sum = -sum;
        }
        return sum;
    }

    public static int calculateOnesComplement(ArrayList<Integer> binaryList){
        int sum = 0;
        double power;
        boolean neg = false;
        if(binaryList.get(0)==1) { neg = true;}
        for (int i = 1; i < binaryList.size(); i++) {
                if (binaryList.get(i) == 1) {
                    binaryList.set(i, 0);
                } else {
                    binaryList.set(i, 1);
                }
        }

        for(int i = 1; i < binaryList.size(); i++) {
            power = binaryList.size() - 1 - i;
            sum += binaryList.get(i) * Math.pow(2, power);
        }
        if(neg) {
            sum = -sum;
        }
        return sum;
    }
    public static int calculateTwosComplement(ArrayList<Integer> binaryList){
        int sum = 0;
        double power;
        sum += -(binaryList.get(0)*(Math.pow(2, binaryList.size()-1)));
        for(int i = 1; i < binaryList.size(); i++) {
            power = binaryList.size() - 1 - i;
            sum += binaryList.get(i) * Math.pow(2, power);
        }
        return sum;
    }
}
