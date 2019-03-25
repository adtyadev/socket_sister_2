package com.client;

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class main {

	public static void main(String[] args) {

		DatagramSocket socketUDP = null;
		try {
			socketUDP = new DatagramSocket();
			Scanner input = new Scanner(System.in);
			System.out.println("========= Price List Sister Collection ======== \n");
			System.out.println("1. Baju -------- Rp 50.000,-");
			System.out.println("2. Celana ------ Rp 100.000,-");
			System.out.println("3. Rok --------- Rp 85.000,-");
			System.out.println("\n=================*******========================\n");
			
			System.out.print("Input Jumlah Baju : ");
			String baju = input.nextLine().trim();
			System.out.print("Input Jumlah Celana : ");
			String celana = input.nextLine().trim();
			System.out.print("Input Jumlah Rok : ");
			String rok = input.nextLine().trim();
			String data = baju+";"+celana+";"+rok;
			
			byte[] messageSend = data.getBytes();
			InetAddress hostName = InetAddress.getByName("127.0.0.1");
			int serverPort = 6789;
			DatagramPacket request = new DatagramPacket(messageSend, data.length(), hostName, serverPort);
			socketUDP.send(request);
			
			byte[] buffer = new byte[messageSend.length];
			DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
			socketUDP.receive(reply);
			System.out.println("Reply: " + new String(reply.getData()));
			input.close();
		} catch (SocketException e) {
			System.out.println("Socket: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO: " + e.getMessage());

			if (socketUDP != null)
				socketUDP.close();
				
		}
	}

}
