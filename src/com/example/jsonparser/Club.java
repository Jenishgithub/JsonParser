package com.example.jsonparser;

public class Club {
	long id;
	String name, address, country, zip, url, number;
	double clat, clon;

	public Club(long id2, String name2, String address2, String country2,
			String zip2, double clat2, double clon2, String url2, String number2) {
		// TODO Auto-generated constructor stub
		this.id = id2;
		this.name = name2;
		this.address = address2;
		this.country = country2;
		this.zip = zip2;
		this.url = url2;
		this.number = number2;
		this.clat = clat2;
		this.clon = clon2;
	}

}
