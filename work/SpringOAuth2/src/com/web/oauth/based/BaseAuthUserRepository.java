package com.web.oauth.based;

import java.util.Optional;

public interface BaseAuthUserRepository extends JpaRepository<BaseAuthUser, Long>{

	Optional<BaseAuthUser> findByEmail(String email);
	
}
