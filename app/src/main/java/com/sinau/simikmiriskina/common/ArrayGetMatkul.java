package com.sinau.simikmiriskina.common;

import java.util.ArrayList;
import java.util.List;

public class ArrayGetMatkul {
	public static List<String> idMatkul = new ArrayList<String>();
	
	public static List<String> getGarage(String param) {
		idMatkul.add(param);
		
		return idMatkul;
	}
}
