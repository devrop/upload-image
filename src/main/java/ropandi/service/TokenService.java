package ropandi.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ropandi.model.TokenResponse;
import ropandi.security.jwt.JwtProvider;
import ropandi.utility.Konstanta;
import ropandi.utility.Utility;



@Service
public class TokenService implements ITokenService {
	
	@Value("${keys.clientId}")
	private String clientIdParam;
	@Value("${keys.secret}")
	private String SecretkeyParam;

	
	
	@Value("${keys.jwtSecret}")
	private String jwtSecret;

    
	@Value("${keys.regex1}")
    private String regex1;
 
	@Value("${keys.regex2}")
    private String regex2;

	@Autowired
    private JwtProvider jwtProvider;
	
	@Override
	public TokenResponse getToken(String[] tokenParams) {
		// TODO Auto-generated method stub
		final String clientId = tokenParams[0] ==null?"":tokenParams[0];
		final String secretKey = tokenParams[1] ==null?"":tokenParams[1];
		TokenResponse tokenResponse = new TokenResponse();
		
	    //jika sesuai generate Token
		if(clientIdParam.equals(clientId) && SecretkeyParam.equals(secretKey)) {
			List<String> roleString = Arrays.asList("ROLE_ADMIN","ROLE_USER");
			String bodyToken = Utility.buildingBodyTokenUsernameAndRole(clientId, regex1, roleString, regex2);
			String tokenData = jwtProvider.generateJwtTokenCustom(bodyToken);
			tokenResponse.setStatus(Konstanta.SUCCESSHTTPOK);
			tokenResponse.setToken(tokenData);
	    }	
		
		
		return tokenResponse;
	}

}
