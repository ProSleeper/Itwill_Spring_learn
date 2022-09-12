package javaLearn.data;

import java.util.HashMap;
import java.util.Map;
import java.util.SimpleTimeZone;

public class MemberDao {

	private Map<String, Member> memberDB = new HashMap<String, Member>();

//	public Map<String, Member> getMemberDB() {
//		return memberDB;
//	}
//
//	public void setMemberDB(Map<String, Member> memberDB) {
//		this.memberDB = memberDB;
//	}

//	public Member findById(String id) {
//		return memberDB.get(id);
//	}

	public Member selectByEmail(String email){
		return memberDB.get(email);
	}

	public void insert(Member member) {
		member.setId(getMaxNum() + 1);
		memberDB.put(member.getEmail(), member);
	}

	public void update(Member member) {
		memberDB.put(member.getEmail(), member);
	}

	public Long getMaxNum()
	{
		return (long) memberDB.size();
	}
}
