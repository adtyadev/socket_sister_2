package com.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ConnectionServer extends AdditionPrice {
	String[] data;
	DatagramSocket socketUDP = null;
	
	ConnectionServer(int port){
		try {
			socketUDP = new DatagramSocket(port);	
		} catch (Exception e) {
		}
		
	}
	
	public void connection()  {
		
		try {
			byte[] buffer = new byte[256];
			System.out.println("Server Running............... ");
			while (true) {
				DatagramPacket request = new DatagramPacket(buffer, buffer.length);
				socketUDP.receive(request);
				String dataClient = new String(request.getData());
				String data = this.hitung(this.data = dataClient.split(";"));
				byte[] dataReply = data.getBytes();
				DatagramPacket reply = new DatagramPacket(dataReply, data.length(), request.getAddress(),
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
	
	public void init () {
		this.connection();
	}
}
