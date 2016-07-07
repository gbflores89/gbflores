package emso.locale;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Scanner;


public class EmsoLocale {

	public enum Type {
		X, Y
	};

	public String[] AXES = { "X", "Y" };
	public String[] LENTH = { "Width", "Height" };
	boolean first = true;

	public EmsoLocale(String fileName, Type axis, int trans) throws IOException {

		Locale.setDefault(Locale.US);
		PrintWriter writer;
		writer = new PrintWriter(
				new FileWriter(fileName.replace(".pfd", "_slide_" + trans + "_" 
						+ AXES[axis.ordinal()] + ".pfd")));

		Scanner input = new Scanner(new File(fileName));
		input.useLocale(Locale.US);
		System.out.println(input.hasNext());
		String cur = input.nextLine();
		double subs = 0;
		while (input.hasNext()) {
			if (cur.contains("<" + AXES[axis.ordinal()] + ">")) {
				cur = cur.replace("<" + AXES[axis.ordinal()] + ">", "");
				cur = cur.replace("</" + AXES[axis.ordinal()] + ">", "");
				subs = Double.parseDouble(cur);
				subs = subs + trans;
				cur = "<" + AXES[axis.ordinal()] + ">" + subs + "</" + 
						AXES[axis.ordinal()] + ">";
			}
			if (cur.contains("<" + LENTH[axis.ordinal()] + ">") && first) {
				first = false;
				cur = cur.replace("<" + LENTH[axis.ordinal()] + ">", "");
				cur = cur.replace("</" + LENTH[axis.ordinal()] + ">", "");
				subs = Double.parseDouble(cur);
				subs = subs + trans;
				cur = "<" + LENTH[axis.ordinal()] + ">" + subs + "</" + 
						LENTH[axis.ordinal()] + ">";

			}
			writer.println(cur);
			cur = input.nextLine();
		}
		writer.println("</Diagram>");
		input.close();
		writer.close();

	}

	public static void main(String[] args) {
		String file = "/home/gbflores/workspace/OTS/Modelos/EMSO-OTS2/Arquivos/Tela_1_2_3_4.pfd";
		try {
			new EmsoLocale(file, Type.Y, 2000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
