import java.io.*;

public class Nuke2 {
	public static void main(String[] agrs) throws Exception{
		 BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		 String temp,res;
		 temp = reader.readLine();
		 res = temp.charAt(0)+temp.substring(2);
		 System.out.println(res);
	}
}
