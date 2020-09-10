import java.util.ArrayList;
import java.util.Arrays;

public class test1 {
	
	public static void main(String[] args) {
		ArrayList<String> a = new ArrayList<>();
		ArrayList<String> b = new ArrayList<>();
		String[] str = {"a", "b"};
		String[] str2 = {"c", "d"};
		a.addAll(Arrays.asList(str));
		b = a;
		b.add("FUck");
		System.out.println(a);
		
	}
}
