package javaLearn.register;

import java.time.LocalDateTime;

import javaLearn.data.Member;
import javaLearn.data.MemberDao;

public class MemberRegisterService {
	
	private MemberDao memberDao = new MemberDao();
	
	public Long regist(RegisterRequest request) throws DuplicateMemberException {
		
		Member member = memberDao.selectByEmail(request.getEmail());
		if (member != null) {
			throw new DuplicateMemberException("dup ID " + request.getEmail());
		}

		Member newMember = new Member(request.getEmail(), request.getPassword(), request.getName(), LocalDateTime.now());
		memberDao.insert(newMember);

		return newMember.getId();
	}
}
