package com.ssp.logsanitizingengine;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StopWatch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
public class LogSanitizingEngineApplication implements CommandLineRunner {

	final String RPT_FILE = "src/resources/report_" + ProcessHandle.current().pid() + ".log";
	final String DATA_FILE = "src/resources/generated.logx";
	final int NO_OF_ITERATIONS = 3;

	public static void main(String[] args) {
		SpringApplication.run(LogSanitizingEngineApplication.class, args).close();
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hello World");
		LogSanitizingEngineService objService = new LogSanitizingEngineService();
		objService.loadMaskedData();

		/*
		 *  http://zetcode.com/java/inputstream/
		 */

		NumberFormat nf = NumberFormat.getNumberInstance();
		nf.setMinimumIntegerDigits(6);
		nf.setGroupingUsed(false);
		
		StopWatch sw = new org.springframework.util.StopWatch();
		StringBuilder sbReport = new StringBuilder();
		AtomicInteger lineCounter = new AtomicInteger(1); 

		sbReport.append("Record No,Data Length,");
		for(int itr=1; itr <= NO_OF_ITERATIONS; itr++) {
			sbReport.append("BuiltIn Pass-" + itr + ",");
		}

		for(int itr=1; itr <= NO_OF_ITERATIONS; itr++) {
			sbReport.append("StringUtils Pass-" + itr + ",");
		}

		try (InputStream fis = new FileInputStream(DATA_FILE);
				InputStreamReader isr = new InputStreamReader(fis,
						StandardCharsets.UTF_8);
				BufferedReader br = new BufferedReader(isr)) {

			br.lines().forEach(line -> {
				int passCounter = 1;
				String tempLineNumber = "Line-" + lineCounter.getAndIncrement();
				sbReport.append(tempLineNumber).append(",").append(line.length()).append(",");

				do {
					sw.start(tempLineNumber + "-Pass-" + Integer.toString(passCounter) + "-BuiltIn");
					objService.doSanitizeBuiltIn(line);
					sw.stop();

					sbReport.append(nf.format(sw.getLastTaskTimeMillis())).append(",");

					passCounter++;
				} while(passCounter <= NO_OF_ITERATIONS);

				passCounter = 1;
				do {
					sw.start(tempLineNumber + "-Pass-" + Integer.toString(passCounter)  + "-StringUtils");
					objService.doSanitizeStringUtils(line);
					sw.stop();

					sbReport.append(nf.format(sw.getLastTaskTimeMillis())).append(",");

					passCounter++;
				} while(passCounter <= NO_OF_ITERATIONS);

				sbReport.append("\n");
			});
		}

		System.out.println("Table describing all tasks performed :\n"+sw.prettyPrint());
		System.out.println("Total time in milliseconds for all tasks :"+sw.getTotalTimeMillis());
		System.out.println("Report available at: " + RPT_FILE);
		
		BufferedWriter objBW = new BufferedWriter(new FileWriter(RPT_FILE));
		objBW.write(sbReport.toString());
		objBW.close();
	}
}
