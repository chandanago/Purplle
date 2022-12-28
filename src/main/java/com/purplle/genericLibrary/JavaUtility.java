package com.purplle.genericLibrary;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;

public class JavaUtility {
	/**
	 * This method is used to generate and return random numbers 
	 * @return  int randomNumber
	 */
	public int getRandomNumber()
	{
		Random random=new Random();
		return random.nextInt(9999);
	}


	/**
	 * This method is used to generate and return random String 
	 * @return  String randomNumber
	 */
	public String generateRandomString() {


		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		int length = 7;

		for(int i = 0; i < length; i++) {
			int index = random.nextInt(alphabet.length());
			char randomChar = alphabet.charAt(index);
			sb.append(randomChar);
		}
		String randomString = sb.toString();
		System.out.println("Random String is: " + randomString);
		return randomString;
	}

	/**
	 * This method is used to get current date in Indian Standard Time
	 * @return DD-MM-YYYY
	 */


	public String date() {
		LocalDate now = LocalDate.now().plusDays(10);
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");  
		String formatDate = now.format(format);  
		/*now.plusDays(10);
		now.plusMonths(2);
		now.plusYears(5);
		now.plusWeeks(2);
		int day = now.getDayOfMonth();
		now.getMonth();
		now.getYear();
		 */
		return formatDate;
	}

	public String getTimeStamp()
	{
		Date date=new Date();
		String d=date.toString();
		d=d.replace(" ", "_").replace(":", "_");
		System.out.println(d);
		return d;
	}


	public void closeChildWnd() {
		Robot robot;
		try {
			robot = new Robot();
			robot.keyPress(KeyEvent.VK_ALT);
			robot.keyPress(KeyEvent.VK_F4);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void keyPress(String key) {
		Robot r;
		try {
			r = new Robot();
			String keys = key.toLowerCase();
			switch (keys) {
			case ("esc"):
				r.keyPress(KeyEvent.VK_ESCAPE);
			r.keyRelease(KeyEvent.VK_ESCAPE);
			case ("end"):
				r.keyPress(KeyEvent.VK_END);
			r.keyRelease(KeyEvent.VK_END);
			case ("home"):
				r.keyPress(KeyEvent.VK_HOME);
			r.keyRelease(KeyEvent.VK_HOME);
			case ("pagedown"):
				r.keyPress(KeyEvent.VK_PAGE_DOWN);
			r.keyRelease(KeyEvent.VK_PAGE_DOWN);
			case ("pageup"):
				r.keyPress(KeyEvent.VK_PAGE_UP);
			r.keyRelease(KeyEvent.VK_PAGE_UP);

			}
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

}



