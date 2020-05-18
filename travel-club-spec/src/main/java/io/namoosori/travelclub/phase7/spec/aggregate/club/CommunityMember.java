package io.namoosori.travelclub.phase7.spec.aggregate.club;

import io.namoosori.travelclub.phase7.spec.aggregate.Entity;
import io.namoosori.travelclub.phase7.spec.facade.aggregate.NameValue;
import io.namoosori.travelclub.phase7.spec.facade.aggregate.NameValueList;
import io.namoosori.travelclub.phase7.spec.util.exception.InvalidEmailException;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class CommunityMember extends Entity {
	//
	private String email;		// key
	private String name;
	private String phoneNumber;
	private String nickName;
	private String birthDay;

	private List<Address> addresses;
	
	public CommunityMember() {
		//
		super();
		this.addresses = new ArrayList<>();
	}

	public CommunityMember(String id) {
		super(id);
	}
	
	public CommunityMember(String email, String name, String phoneNumber) {
		// 
		this();
		this.email = email;
		this.name = name;
		this.phoneNumber = phoneNumber; 
	}

	@Override
	public String toString() {
		// 
		StringBuilder builder = new StringBuilder();
		
		builder.append("Name:").append(name); 
		builder.append(", email:").append(email); 
		builder.append(", nickname:").append(nickName); 
		builder.append(", phone number:").append(phoneNumber); 
		builder.append(", birthDay:").append(birthDay); 

		if (addresses != null) {
			int i = 1; 
			for(Address address : addresses) {
				builder.append(", Address[" + i + "]").append(address.toString()); 
			}
		}
		
		return builder.toString(); 
	}
	
	public static CommunityMember getSample() {
		// 
		CommunityMember member = null;
		try {
			member = new CommunityMember("mymy@nextree.co.kr", "Minsoo Lee", "010-3321-1001");
			member.setBirthDay("2001.09.23");
			member.getAddresses().add(Address.getHomeAddressSample());
		} catch (InvalidEmailException e) {
			System.out.println(e.getMessage());
		}
		return member; 
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws InvalidEmailException {
		//
		if (!this.isValidEmailAddress(email)) {
			throw new InvalidEmailException("Email is not valid. --> " + email);
		}
		
		this.email = email;
	}

    public boolean isValidEmailAddress(String email) {
    	//
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

	public void modifyValues(NameValueList nameValues) {
		//
		for (NameValue nameValue : nameValues.getNameValueList()) {
			String value = nameValue.getValue();
			switch (nameValue.getName()) {
				case "name":
					this.name = value;
					break;
				case "phoneNumber":
					this.phoneNumber = value;
					break;
				case "nickName":
					this.nickName = value;
					break;
				case "birthDay":
					this.birthDay = value;
					break;
			}
		}
	}
}
