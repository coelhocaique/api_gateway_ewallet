package com.coelhocaique.wallet.parser;

import org.apache.commons.codec.digest.Crypt;
import org.springframework.http.HttpStatus;

import com.coelhocaique.wallet.dto.WalletDTO;
import com.coelhocaique.wallet.model.Wallet;
import com.coelhocaique.wallet.utils.WalletUtils;

public class WalletParser {
	
	public static Wallet toEntity(WalletDTO dto, String userKey){
		Wallet entity = null;
		
		if(dto != null){
			String cardNumber = dto.getCardNumber();
			
			entity = Wallet.builder()
						.cardholder(dto.getCardholder())
						.expirationYear(Integer.valueOf(dto.getExpirationYear()))
						.expirationMonth(Integer.valueOf(dto.getExpirationMonth()))
						.bin(Integer.valueOf(cardNumber.substring(0,6)))
						.last4(Integer.valueOf(cardNumber.substring(cardNumber.length() - 4)))
						.userId(WalletUtils.decodeKey(userKey)[0])
						.cardNumber(Crypt.crypt(cardNumber))
						.build();
		}
		
		return entity;
	}
	
	public static WalletDTO toDTO(Wallet entity){
		WalletDTO dto = null;
		
		if(entity != null){
			dto = new WalletDTO(entity.getId(),entity.getBin(),entity.getLast4());
							
		}
		
		return dto;
	}
	
	public static WalletDTO toDTO(HttpStatus statusCode, String message){
		return WalletDTO.builder()
				.code(statusCode.value())
				.returnMessage(message)
				.build();
	}
}
