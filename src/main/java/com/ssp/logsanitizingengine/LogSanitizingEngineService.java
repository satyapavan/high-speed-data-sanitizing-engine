package com.ssp.logsanitizingengine;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import org.springframework.util.StringUtils;

public class LogSanitizingEngineService {

	final String MASK_DATA_FILE="src/resources/mask.data";
	
	HashMap<String, String> objMaskedData = new HashMap<String, String>();

	boolean doSanitizeBuiltIn(String data) {
		objMaskedData.forEach((k_mask, v_unmask) -> {
			data.replace(k_mask, v_unmask);
		});
		return true;
	}

	boolean doSanitizeStringUtils(String data) {
		objMaskedData.forEach((k_mask, v_unmask) -> {
			StringUtils.replace(data, k_mask, v_unmask);
		});
		return true;
	}

	boolean loadMaskedData() throws FileNotFoundException, IOException, Exception {
		try (InputStream fis = new FileInputStream(MASK_DATA_FILE);
				InputStreamReader isr = new InputStreamReader(fis,
						StandardCharsets.UTF_8);
				BufferedReader br = new BufferedReader(isr)) {

			br.lines().forEach(line -> {
				objMaskedData.put(line, line.replaceAll("([a-z]|[A-Z]|[0-9])", "X"));
			});
		}
		
		System.out.println(objMaskedData.toString());
		return true;
	}
}