package org.kms.client;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
public class EmployeeConsumerClient {
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	public void getAllEmp(){
		RestTemplate restTemplate=new RestTemplate();
		ResponseEntity<String> entity=null;
		String serviceURI="/api/getAllEmployes";
		try {
			List<ServiceInstance> instances=discoveryClient.getInstances("employee-producer");
			ServiceInstance serviceInstance=instances.get(0);
			
			String baseUrl=serviceInstance.getUri().toString();
			serviceURI=baseUrl+serviceURI;
			entity=restTemplate.exchange(serviceURI,
					HttpMethod.GET, getHeaders(),String.class);
			System.out.println(entity.getBody());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static HttpEntity<?> getHeaders() throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<>(headers);
	}
	public DiscoveryClient getDiscoveryClient() {
		return discoveryClient;
	}
	public void setDiscoveryClient(DiscoveryClient discoveryClient) {
		this.discoveryClient = discoveryClient;
	}
	
	
}
