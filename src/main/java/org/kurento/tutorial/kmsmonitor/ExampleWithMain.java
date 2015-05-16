package org.kurento.tutorial.kmsmonitor;
import java.io.DataOutputStream;
import java.net.Socket;

public class ExampleWithMain {
	
	final static String DEFAULT_KMS_WS_URI = "ws://localhost:8888/kurento";
	final static String DEFAULT_GRAPHITE_IP = "80.96.122.69";	

	public static void main(String[] args) throws InterruptedException {
		KmsMonitor kmsMonitor = new KmsMonitor(System.getProperty("kms.ws.uri", DEFAULT_KMS_WS_URI));
		
		while(true){
			KmsStats kmsStats = kmsMonitor.updateStats();
			System.out.println("Num.Pipelines, " + kmsStats.getNumPipelines());
			System.out.println("Num.Elements, " + kmsStats.getNumElements());
			System.out.println("Num.WebRtcEndpoints, " + kmsStats.getNumWebRtcEndpoints());
			
			System.out.println("Inbound.Byte.Count, "+ kmsStats.getWebRtcStats().getInbound().getByteCount());
			System.out.println("Inbound.Delta.Nacks, "+ kmsStats.getWebRtcStats().getInbound().getDeltaNacks());
			System.out.println("Inbound.Jitter, " + kmsStats.getWebRtcStats().getInbound().getJitter());
			
			Socket conn = new Socket(System.getProperty("graphite_ip", DEFAULT_GRAPHITE_IP), 2003);
			DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
			dos.writeBytes("server.app1tt.pipelines "+ kmsStats.getNumPipelines() +"\n");
			conn.close();
			
			Thread.sleep(3000);
		}	
	}
}
