package testdriven.byexample;

import org.apache.commons.lang3.StringUtils;

public class IndentingWriter {
	StringBuilder contents = new StringBuilder();
	int indentTabs = 0;
	boolean newLine = false;

	public String contents() {
		return contents.toString();
	}

	public void print(String str) {
		if (newLine) {	//如果是新的一行，先打印TAB
			contents.append(StringUtils.repeat("\t", indentTabs));
		}
		contents.append(str);
		newLine = false;
	}
	
	public void println() {
		contents.append("\n");
		//		.append(StringUtils.repeat("\t", indentTabs));
		newLine = true;
	}
	
	public void println(String str) {
		//contents.append(str);
		print(str);
		println();
	}

	public void indent() {
		indentTabs++;
	}

	public void exdent() {
		indentTabs--;		
	}


}
