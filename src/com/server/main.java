package com.server;

import java.net.*;
import java.io.*;

public class main {
	
	
	public static void main(String[] args) {
		
		ConnectionServer cs = new ConnectionServer(6789);
		cs.init();

	}

}
