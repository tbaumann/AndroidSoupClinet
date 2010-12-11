package name.baumann.tilman.soupclient.lib;

import java.util.*;

public class CookieReader extends HashMap{
	private static final long serialVersionUID = -9002932050958606420L;

	public CookieReader() {
		super();
	}
	
	public void parseText(String text){
		this.clear();
		String[] stringarr;
		if(text != null){
			stringarr = text.split(";");
					Iterator<String> i = Arrays.asList(stringarr).iterator();
			while(i.hasNext()){
				String current = i.next();
				String[] value = current.split("=");
				value[0] = value[0].replaceAll("\\s", "");
				this.put(value[0], value[1]);
			}
		}
	}
}
