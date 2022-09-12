package javaLearn.pwdchange;

import javaLearn.data.Member;
import javaLearn.data.MemberDao;
import javaLearn.data.WrongIdPasswordException;

public class ChangePasswordService {

    private MemberDao memberDao = null;

    public void changePassword(String email, String oldPassword, String newPassword) throws MemberNotFoundException, WrongIdPasswordException {
        Member member = memberDao.selectByEmail(email);

        if (member == null){
            throw new MemberNotFoundException();
        }
        member.changePassword(oldPassword, newPassword);

        memberDao.update(member);
    }

    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }
}
