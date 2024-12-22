import java.util.Random;
import java.util.Scanner;

public class guessNum {
    public static void main(String args[]){
    int min=1;
    int max=1000;
    Random random=new Random();
    int randomNum=random.nextInt(max-min+1);
    System.out.println("Enter a Number");
    Scanner sc= new Scanner(System.in);
    int num=sc.nextInt();
    int count=0;
    while(randomNum!=num){
        count++;
    if(num<randomNum){
        System.out.println("number is too low");
        num=sc.nextInt();
    }
    else{
        System.out.println("number is too high");
        num=sc.nextInt();
    }
    }
if(num==randomNum){
    System.out.println("random number between  "+min+"  and  "+max+"  :"+  randomNum);
}
System.out.println("your score is:"+count);
}
}
