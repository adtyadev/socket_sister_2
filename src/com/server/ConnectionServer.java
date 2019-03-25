package com.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ConnectionServer {
	String[] data;

	public byte[] hitung(String[] data) {
		double hasilJumlah = 0.0;
		int a = 0;
		for (String i : data) {
			if (i.compareTo(" ") == 0)
				i = "0.0";

			switch (a) {
			case 0:
				hasilJumlah += Double.parseDouble(i.trim())*50000;

			case 1:
				hasilJumlah += Double.parseDouble(i.trim())*50000;

			case 2:
				hasilJumlah += Double.parseDouble(i.trim())*50000;
			}
			a++;
		}

		String hasil = Double.toString(hasilJumlah);
		return hasil.getBytes();
	}

	public void connection() {
		DatagramSocket socketUDP = null;
		try {
			socketUDP = new DatagramSocket(6789);
			byte[] buffer = new byte[256];
			System.out.println("Server Running............... ");
			while (true) {
				DatagramPacket request = new DatagramPacket(buffer, buffer.length);
				socketUDP.receive(request);
				String dataClient = new String(request.getData());
				byte[] data = this.hitung(this.data = dataClient.split(";"));
				DatagramPacket reply = new DatagramPacket(data, request.getLength(), request.getAddress(),
						request.getPort());
				socketUDP.send(reply);
			}
		} catch (SocketException e) {
			System.out.println("Socket: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO: " + e.getMessage());
		} finally {
			if (socketUDP != null)
				socketUDP.close();
		}
	}
}
