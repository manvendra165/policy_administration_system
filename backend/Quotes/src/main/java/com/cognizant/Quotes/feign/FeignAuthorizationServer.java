package com.cognizant.Quotes.feign;


//import com.cognizant.consumer.request.UsernamePasswordRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "authorization-service", url = "${AUTHORIZATION_SERVICE:http://localhost:8080}")
public interface FeignAuthorizationServer {

   // @PostMapping(value = "/account/login")
    //public ResponseEntity<Object> authenticateUser(@RequestBody UsernamePasswordRequest usernamePasswordRequest);
	
	@GetMapping("/authorization/validate")
	public boolean validate(@RequestHeader(name = "Authorization") String token);
}
