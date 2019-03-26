package com.client;

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class main {

	public static void main(String[] args) {

		ConnectionClient cc = new ConnectionClient("localhost", 6789);
		cc.init();
	}

}
