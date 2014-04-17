package me.hdt80bro.simpleTimer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class simpleTimer {

	public static void main(String[] args) throws InterruptedException,
			IOException {
		new simpleTimer();
	}
	
	simpleTimer() throws InterruptedException, IOException {
		boolean yes = true;
		double choice;
		double wanted;
		long startTime;
		long startStartTime;
		long hey;
		long bye;
		int howLong;
		int how;
		int real = 0;
		int c = 1;
		int realtho = 100;
		double heyheyhey;
		String output = "";
		int[] a = new int[6];
		int[] b = new int[10000];
		Scanner s = new Scanner(System.in);
		Scanner scan = new Scanner(System.in);
		PrintWriter pw = new PrintWriter(new FileWriter(new File(
				realtho + ".txt"),true));
		startStartTime = System.currentTimeMillis();
		pw.write("\n");
		while (yes == true) {
			//how = Integer.parseInt(s.nextLine());
			how = c;
			if (how >= realtho) {
				yes = false;
			}
			System.out.println("How many rolls?");
			System.out.println("Rolling " + how + " times.");
			System.out.println("How long should I wait?");
			//howLong = Integer.parseInt(s.nextLine());
			howLong = 10;
			System.out.println(howLong + ", " + how);
			heyheyhey = how / howLong;
			System.out.println(heyheyhey);
			//Thread.sleep(1500);
			startTime = System.currentTimeMillis();
			for (int i = 0; i < how; i++) {
				hey = System.currentTimeMillis();
				choice = Math.random();
				wanted = choice * 6;
				real = (int) wanted;
				System.out.println("------------");
				System.out.println("Rolled a: " + (real + 1));
				a[real]++;
				System.out.println("One: " + a[0]);
				System.out.println("Two: " + a[1]);
				System.out.println("Three: " + a[2]);
				System.out.println("Four: " + a[3]);
				System.out.println("Five: " + a[4]);
				System.out.println("Six: " + a[5]);
				System.out
						.println("Rolled: " + (i + 1) + "/" + how + " times.");
				//Thread.sleep(howLong / how);
				bye = System.currentTimeMillis();
				System.out.println("This roll took: " + (bye - hey)
						+ " milliseconds.");
				System.out.println(bye - startTime
						+ " total milliseconds past.");

			}
			System.out.println("=============");
			long endTime = System.currentTimeMillis();
			System.out.println("Took " + (endTime - startTime)
					+ " milliseconds to roll " + how + " times.");
			System.out.println("One: " + a[0]);
			System.out.println("Two: " + a[1]);
			System.out.println("Three: " + a[2]);
			System.out.println("Four: " + a[3]);
			System.out.println("Five: " + a[4]);
			System.out.println("Six: " + a[5]);
			System.out.println("=============");
			b[how] = (int) (endTime-startTime);
			for (int i = 0; i < b.length; i++) {
				if (b[i] != 0) {
					String temp = i + ": " + b[i] + "; ";
					System.out.println(temp);
				}
			}
			output +=c +": " + b[how] + "; " + "\n";
			System.out.println("--------");
			System.out.println(output);
			System.out.println("========");
			c++;
			/*
			System.out.println("Go again? (y/n)");
			String sa = scan.nextLine();
			if (sa.equals("y")) {
				yes = true;
				c++;
			} else if (!sa.equals("y")) {
				yes = false;
			}
			*/
		}
		System.out.println(output);
		System.out.println("Took " + (System.currentTimeMillis()-startStartTime) + " milliseconds to finish.");
		output += "Took " + (System.currentTimeMillis()-startStartTime) + " milliseconds to finish." + "\n";
		pw.write(output);
		pw.close();
		s.close();
		scan.close();
	}

}
