package com.web.oauth.base.model;



@Setter
@Getter
@NoArgsConstructor
@Entity
public class BaseAuthUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String email;
	
	@Column
	private String picture;
	
	Enumerated(EnumType.STRING)
	@Column(nullable = false)
	privateBaseAuthRole role;
	
	@Builder
	public BaseAuthUser(String name, String email, String picture, BaseAuthRole role) {
		
		this.name = name;
		this.email = email;
		this.picture = picture;
		this.role = role;
	}
	
	public BaseAuthUser update(String name, String picture) {
		this.name = name;
		this.picture = picture;
	}
	
	public String getRoleKey() {
		return this.role.getKey();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}	
