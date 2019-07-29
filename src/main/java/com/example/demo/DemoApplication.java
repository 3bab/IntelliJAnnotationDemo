package com.example.demo;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	private Logger logger = Logger.getLogger("logger");


	// --------------------------------------------------
	// not null

	@NotNull // this variable cannot be set to null
	private Boolean a = false;

	@NotNull
	private Boolean getA() {
		a = null; // it complains because we try to set null to @NotNull variable
		return a;
	}

	private void setA(@NotNull Boolean a /*complains because of code line 59*/) {
		this.a = a;
	}

	// --------------------------------------------------
	// nullable

	@Nullable // this variable can be set to null and we have to take care of possible NPE
	private String s;

	@Nullable
	private String getS() {
		return s;
	}

	public void setS(@Nullable String s) {
		this.s = s;
	}

	// ---------------------------------------------------
	// tests

	@Override
	public void run(String... args) {
		// --------------------------------
		// not null tests
		Boolean resultA = this.getA();

		logger.info(resultA.toString()); // this will throw NPE (because of line 25)

		this.setA(null); // complains because we try to set null to @NotNull variable
		this.setA(true); // ok

		// --------------------------------
		// nullable tests
		String resultS = this.getS();
		resultS = resultS.toLowerCase(); // complains because getS() might return null and NPE can occur. When run, NPE thrown

		resultS = this.getS();

		if (resultS != null) {
			resultS = resultS.toLowerCase(); // ok
		}

		resultS = resultS.toLowerCase(); // complains because resultS can be null

		logger.info(resultS);
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
