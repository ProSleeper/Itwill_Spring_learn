package com.web.oauth.base.model;


@Getter
@Require
public enum BaseAuthRole {

	GUES("ROLE_GUEST", "손님"),
	USER("ROLE_USER", "일반 사용자")
	
	private final String key;
	private final String title;
	
}
