package pruebaapi;

import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Dime las coordenadas: ");
		System.out.println("longitud: ");
		double lon = sc.nextDouble();
		System.out.println("latitud: ");
		double lat = sc.nextDouble();
		
		OpenMeteoClient.cliente(lon, lat);
	}

}
