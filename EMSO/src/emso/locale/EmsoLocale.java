package emso.locale;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Scanner;

public class EmsoLocale {
	public EmsoLocale(String fileName) throws IOException {
		
		int trans = 4000;
		Locale.setDefault(Locale.US);
		PrintWriter writer;
		writer = new PrintWriter(new FileWriter(fileName.replace(".pfd", "_translad.pfd")));

		Scanner input = new Scanner(new File(fileName));
		input.useLocale(Locale.US);
		String cur = input.nextLine();
		double subs = 0;
		while (input.hasNext()) {
			if(cur.contains("<X>")){
				cur = cur.replace("<X>", "");
				cur = cur.replace("</X>", "");
				subs = Double.parseDouble(cur);
				if (subs!=0){
					subs = subs + trans;
				}
				cur = "<X>"+subs+"</X>";
			}
			System.out.println(cur);
			writer.println(cur);
			cur = input.nextLine();
		}
		writer.close();







	}

	public static void main(String[] args) {
		try {
			new EmsoLocale("C:/Users/Guilherme/workspace/OTS/Arquivos/Com_PID/Testes_integracao/Tela_6.pfd");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
