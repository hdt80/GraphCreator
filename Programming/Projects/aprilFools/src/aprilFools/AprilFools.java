package aprilFools;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.IOException;

public class AprilFools {

	public static void main(String[] args) throws AWTException, IOException, InterruptedException {
		int a = 0;
		// jimmy! you're on a roll!
		java.awt.Desktop.getDesktop().browse(java.net.URI.create("https://www.youtube.com/watch?v=hVfvQ1mhTsE"));
		//r.delay(20000);
		while (true) {
			Thread.sleep(300000);//150,000
			java.awt.Desktop.getDesktop().browse(java.net.URI.create("https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
			a++;
			System.out.println(a);
		}
	}
}
