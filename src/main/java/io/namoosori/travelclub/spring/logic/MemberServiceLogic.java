package io.namoosori.travelclub.spring.logic;

import io.namoosori.travelclub.spring.aggregate.club.CommunityMember;
import io.namoosori.travelclub.spring.service.MemberService;
import io.namoosori.travelclub.spring.service.sdo.MemberCdo;
import io.namoosori.travelclub.spring.shared.NameValueList;
import io.namoosori.travelclub.spring.store.MemberStore;
import io.namoosori.travelclub.spring.util.exception.MemberDuplicationException;
import io.namoosori.travelclub.spring.util.exception.NoSuchMemberException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MemberServiceLogic implements MemberService {
	//
	@Autowired
	private MemberStore memberStore;

	public MemberServiceLogic() {
		//
	}

	@Override
	public String registerMember(MemberCdo newMemberCdo) {
		//
		String email = newMemberCdo.getEmail();
		CommunityMember member = memberStore.retrieveByEmail(email);

		if (member != null) {
			throw new MemberDuplicationException("Member already exists with email: " + member.getEmail());
		}

		member = new CommunityMember(
				newMemberCdo.getEmail(),
				newMemberCdo.getName(),
				newMemberCdo.getPhoneNumber()
		);
		member.setNickName(newMemberCdo.getNickName());
		member.setBirthDay(newMemberCdo.getBirthDay());

		member.checkValidation();

		memberStore.create(member);

		return member.getId();
	}

	@Override
	public CommunityMember findMemberById(String memberId) {
		//
		return memberStore.retrieve(memberId);
	}

	@Override
	public CommunityMember findMemberByEmail(String memberEmail) {
		//
		return memberStore.retrieveByEmail(memberEmail);
	}

	@Override
	public List<CommunityMember> findMembersByName(String name) {
		//
		return memberStore.retrieveByName(name);
	}

	@Override
	public void modifyMember(String memberId, NameValueList nameValueList) {
		//
		CommunityMember targetMember = memberStore.retrieve(memberId);

		if (targetMember == null) {
			throw new NoSuchMemberException("No such member with id " + memberId);
		}

		targetMember.modifyValues(nameValueList);

		memberStore.update(targetMember);
	}


	@Override
	public void removeMember(String memberId) {
		//
		if (!memberStore.exists(memberId)) {
			throw new NoSuchMemberException("No such member with id " + memberId);
		}

		memberStore.delete(memberId);
	}
}