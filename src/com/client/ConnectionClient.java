package com.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class ConnectionClient {
	DatagramSocket socketUDP = null;
	int serverPort;
	InetAddress hostName;

	ConnectionClient(String hostName, int serverPort) {
		try {

			this.hostName = InetAddress.getByName(hostName);
			this.serverPort = serverPort;
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void connection() {
		try {
			this.socketUDP = new DatagramSocket();
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
			String data = baju + ";" + celana + ";" + rok;

			byte[] messageSend = data.getBytes();

			DatagramPacket request = new DatagramPacket(messageSend, data.length(), hostName, serverPort);
			this.socketUDP.send(request);

			byte[] buffer = new byte[256];
			DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
			this.socketUDP.receive(reply);
			
			if (new String(reply.getData()).trim().equals("null")) {
				System.out.println("Input must be an integer ( 0 - 9 )");
			}
			else {
			System.out.println("\n----- I N V O I C E -------");
			System.out.println("TOTAL PEMBAYARAN = Rp " + new String (reply.getData())+",-");
			System.out.println("\nTerimakasih telah bertransaksi");
			}
			input.close();
		} catch (SocketException e) {
			System.out.println("Socket: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO: " + e.getMessage());

			if (this.socketUDP != null)
				this.socketUDP.close();

		}
	}
	
	public void init() {
		this.connection();
	}

}
