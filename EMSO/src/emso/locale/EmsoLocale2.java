package emso.locale;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Scanner;

public class EmsoLocale2 {

	public enum Type {
		X, Y
	};

	public String[] AXES = { "X", "Y" };
	public String[] LENTH = { "Width", "Height" };
	boolean first = true;

	public EmsoLocale2(String fileName, Type axis, int trans) throws IOException {

		Locale.setDefault(Locale.US);
		PrintWriter writer;
		writer = new PrintWriter(
				new FileWriter(fileName.replace(".pfd", "_slide_" + trans + "_" 
						+ AXES[axis.ordinal()] + ".pfd")));

		Scanner input = new Scanner(new File(fileName));
		input.useLocale(Locale.US);
		String cur = input.nextLine();
		double subs = 0;
		while (input.hasNext()) {
			if (cur.contains("<" + AXES[axis.ordinal()] + ">")) {
				cur = cur.replace("<" + AXES[axis.ordinal()] + ">", "");
				cur = cur.replace("</" + AXES[axis.ordinal()] + ">", "");
				subs = Double.parseDouble(cur);
				if (subs>1000)
					subs = subs + 100;
				
				cur = "<" + AXES[axis.ordinal()] + ">" + subs + "</" + 
						AXES[axis.ordinal()] + ">";
			}
			writer.println(cur);
			cur = input.nextLine();
		}
		input.close();
		writer.close();

	}

	public static void main(String[] args) {
		String file = "C:/Users/Guilherme/workspace/OTS/Arquivos/Com_PID/Teste_Integração/Tela_2_3_4_5_6_slide_0_X.pfd";
		try {
			new EmsoLocale2(file, Type.Y, 0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
