package javaLearn.assembler;

import javaLearn.data.CachedMemberDAO;
import javaLearn.data.MemberDao;
import javaLearn.pwdchange.ChangePasswordService;
import javaLearn.register.MemberRegisterService;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

//의존성 주입을 관리해주는 부분
public class Assembler {

    private MemberDao memberDao = null;
    private MemberRegisterService memberRegisterService = null;
    private ChangePasswordService changePasswordService = null;

    public Assembler() {
        memberDao = new CachedMemberDAO();
        memberRegisterService = new MemberRegisterService();
        changePasswordService = new ChangePasswordService();
        changePasswordService.setMemberDao(memberDao);

    }

    public MemberDao getMemberDao() {

        return memberDao;
    }

    public MemberRegisterService getMemberRegisterService() {
        return memberRegisterService;
    }

    public ChangePasswordService getChangePasswordService() {
        return changePasswordService;
    }
}
