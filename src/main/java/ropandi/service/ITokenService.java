package ropandi.service;

import ropandi.model.TokenResponse;

public interface ITokenService {
	
	TokenResponse getToken(String[] tokenParams);

}
