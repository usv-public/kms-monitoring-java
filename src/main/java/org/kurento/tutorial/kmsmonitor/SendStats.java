package org.kurento.tutorial.kmsmonitor;
import java.io.DataOutputStream;
import java.net.Socket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class SendStats {
	
	final static String DEFAULT_KMS_WS_URI = "ws://localhost:8888/kurento";
	final static String DEFAULT_GRAPHITE_IP = "80.96.122.69";	

	public static void main(String[] args) throws InterruptedException {
		KmsMonitor kmsMonitor = new KmsMonitor(System.getProperty("kms.ws.uri", DEFAULT_KMS_WS_URI));
		String graphite_ip = System.getProperty("graphite_ip", DEFAULT_GRAPHITE_IP);
		String hostname = System.getProperty("hostname");
		try {
			hostname = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException ex) {
			System.out.println("Hostname can not be resolved");
		}
		
		while(true){
			KmsStats kmsStats = kmsMonitor.updateStats();
			
			try {
				Socket conn = new Socket(graphite_ip, 2003);
				DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
				
				System.out.println("Sending stats for hostname: " + hostname + " to graphite... ");
				dos.writeBytes("server."+hostname+".pipelines " + kmsStats.getNumPipelines() +" "+ System.currentTimeMillis() / 1000L +"\n");
				dos.writeBytes("server."+hostname+".elements " + kmsStats.getNumElements() +" "+ System.currentTimeMillis() / 1000L +"\n");
				
				conn.close();
			} catch (Exception e) {
				System.out.println(e);
			}

			Thread.sleep(2000);
		}	
	}
}
