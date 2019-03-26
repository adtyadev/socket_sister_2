package com.server;

public class AdditionPrice {
	
	public String filterInput(String input) {
		return input.replaceAll("([^0-9 ])", "");
	}

	public String hitung(String[] data) {
		double hasilJumlah = 0.0;
		int a = 0;
		for (String i : data) {
			String count = this.filterInput(i);
			
			if(count.isEmpty()) return "null";
			
			if (count.compareTo(" ") == 0)
				i = "0.0";
			switch (a) {
			case 0:
				hasilJumlah += Double.parseDouble(count.trim()) * 50000.0;
				break;

			case 1:
				hasilJumlah += Double.parseDouble(count.trim()) * 100000.0;
				break;

			case 2:
				hasilJumlah += Double.parseDouble(count.trim()) * 85000.0;
				break;
			}
			a++;
		}

		String hasil = Double.toString(hasilJumlah);
		return hasil;
	}
	
}
