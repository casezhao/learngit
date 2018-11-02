import java.util.*;
public class P2{
	public static String maxp(String s){

	}
	public static boolean isp(String s){
		if(s.subString(0,s.length()/2).equals(StringBuffer.reverse(s.subString((s.length()+1)/2,s.length()-1)))){
			return true;
		}
		else{
			return false;
		}
	}
	public static void main(String[] args){
		Scanner input=new Scanner(System.in);
		System.out.println(isp(input.next()));
	}
}