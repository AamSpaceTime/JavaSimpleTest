/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javasimpletest;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
//import java.net.InetSocketAddress;
//import java.net.Proxy;
import java.net.URL;

//import javasimpletest.HttpsClient;
//import sun.net.www.http.HttpClient;

import org.glassfish.jersey.client.*;


/**
 *
 * @author Андрей
 */
public class JavaSimpleTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
    
        try {
        Scanner scan = new Scanner(System.in);
        String s = "MIET";
        
	System.out.println("Введите запрос");			
        s = scan.nextLine();
	//Pattern pattern = Pattern.compile(scan.nextLine()); 

        //URL url = new URL("http://yandex.ru/search/?text="+s+"&lr=216");
        URL url = new URL("http://postyplenie.ru/");
        
        System.out.println(url);
        LineNumberReader lineReader = null;
        lineReader = new LineNumberReader(
                new InputStreamReader(
                     url.openStream()
                )
            );
            String line;
            while ((line = lineReader.readLine()) != null) {
                //System.out.println(line);
            }        
        /*
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(url.openConnection().getInputStream(), "UTF-8"));
        while (true) {
            String line = reader.readLine();
            if (line == null)
                break;
            System.out.println(line);
        }
        */
        
            HttpsClient hts = new HttpsClient("https://yandex.ru/search/?text=MIET&lr=216");
            //hts.testIt();
            
        //Пробуем Rest
        url = new URL("http://emp.test.edc.altarix.org/index.php");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json");

		String input = "{ \"controller\": \"userManager\" , \"action\": \"login\", \"user\":\"test_as_zelao\",\"password\":\"1234\",\"sessiontime\":86400 }";

		OutputStream os = conn.getOutputStream();
		os.write(input.getBytes());
		os.flush();

		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
				+ conn.getResponseCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));

		String output;
		System.out.println("Output from Server .... \n");
                String Result = "";
		while ((output = br.readLine()) != null) {
			Result+=output;
		}
                System.out.println(Result);

		conn.disconnect();
            
    } catch(Exception e) {
        System.out.println(e.getMessage());
    }
        
    }

    public static boolean Test(String x) {
        System.out.println(x);
        return true;
    }
    
}
