package ropandi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ropandi.model.TokenResponse;
import ropandi.service.ITokenService;


@RestController
@RequestMapping("auth")
public class TokenController {

	@Autowired
	private ITokenService tokenService;
	
	
	
	@GetMapping("/getToken")
	public ResponseEntity<TokenResponse> getToken(
			@RequestParam("clientId") String clientId,
			@RequestParam("secret") String secret){
		final String clientIdParam = clientId==null?"":clientId;
		final String secretParam = secret==null?"":secret;
		final String[] params = {clientIdParam,secretParam};
		TokenResponse response = tokenService.getToken(params);
		return new ResponseEntity<TokenResponse>(response,HttpStatus.OK);
		
		
		
		
	}
}
