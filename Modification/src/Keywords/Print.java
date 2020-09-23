package Keywords;

import java.awt.Color;
import javax.swing.text.BadLocationException;
import Application.ConsoleOutput;
import Common.Information;

public class Print implements Information {

	public String getValue(String object) throws BadLocationException {
		String[] printValues = object.split("[+]");
		for(String key: printValues) {
			if(VALUE_STORAGE.containsKey(key.trim())) {
				ConsoleOutput.addText(VALUE_STORAGE.get(key.trim()), Color.BLUE);
			}
			else {
				ConsoleOutput.addText(key.trim().replace("\"", ""), Color.BLACK);
			}
		}
		ConsoleOutput.addText("\n", Color.BLACK);
		
		return INFO;
	}

	public void storeVariable(String key, String value) {
		if (VALUE_STORAGE.containsKey(key))
			VALUE_STORAGE.put(key.trim(), VALUE_STORAGE.get(key));
		else
			VALUE_STORAGE.put(key.trim(), value);
	}
}
