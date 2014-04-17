import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JPanel;

public class RobotTest implements KeyListener {

	public static void main(String[] args) throws AWTException, IOException {
		new RobotTest();
	}
	public RobotTest() throws AWTException, IOException {
		Robot r = new Robot();
		ProcessBuilder pb = new ProcessBuilder("Notepad.exe", "myfile.txt");
		long startTime = System.currentTimeMillis();
		//pb.start();
		r.delay(1000);
		//r.keyPress(KeyEvent.VK_T);
		//r.keyPress(KeyEvent.VK_E);
		//r.keyPress(KeyEvent.VK_S);
		//r.keyPress(KeyEvent.VK_T);
		double hght = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		double wght = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		System.out.println(hght);
		System.out.println(wght);
		System.out.println(hght * wght);
		for (int i = 0; i < hght; i++) {
			for (int j = 0; j < wght; j++) {
				r.mouseMove(j, i);
				System.out.println(j + ", " + i);
			}
		}
		//java.awt.Desktop.getDesktop().browse(java.net.URI.create("http://www.youtube.com/watch?v=kfVsfOSbJY0"));
		/*
		while(true) {
			startTime = System.currentTimeMillis();
			r.delay(500);
			Toolkit.getDefaultToolkit().beep();
			System.out.println(System.currentTimeMillis() - startTime);
		} */
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		//System.out.println(e.getKeyChar());
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
