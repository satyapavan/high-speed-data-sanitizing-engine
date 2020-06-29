package com.ssp.logsanitizingengine;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StopWatch;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@SpringBootApplication
public class LogSanitizingEngineApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LogSanitizingEngineApplication.class, args).close();
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hello World");
		System.out.println(args);
		LogSanitizingEngineService objService = new LogSanitizingEngineService();
		
		/*
		 *  http://zetcode.com/java/inputstream/
		 */
			
		String fileName = "src/resources/generated.log";

		StopWatch sw = new org.springframework.util.StopWatch();
		sw.start("Method-1"); // Start a named task
		
        try (InputStream fis = new FileInputStream(fileName);
                InputStreamReader isr = new InputStreamReader(fis,
                        StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr)) {

            br.lines().forEach(line -> objService.doSanitize(line));
        }
        
        sw.stop();
        
        System.out.println("Total time in milliseconds for all tasks :"+sw.getTotalTimeMillis());
        System.out.println("Table describing all tasks performed :\n"+sw.prettyPrint());
		
	}
}
