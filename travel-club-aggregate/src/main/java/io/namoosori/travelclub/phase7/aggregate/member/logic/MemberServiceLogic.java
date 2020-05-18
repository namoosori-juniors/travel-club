package io.namoosori.travelclub.phase7.aggregate.member.logic;

import io.namoosori.travelclub.phase7.aggregate.member.service.MemberService;
import io.namoosori.travelclub.phase7.aggregate.member.store.MemberStore;
import io.namoosori.travelclub.phase7.spec.aggregate.club.CommunityMember;
import io.namoosori.travelclub.phase7.spec.facade.aggregate.NameValueList;
import io.namoosori.travelclub.phase7.spec.facade.aggregate.club.sdo.MemberCdo;
import io.namoosori.travelclub.phase7.spec.util.exception.InvalidEmailException;
import io.namoosori.travelclub.phase7.spec.util.exception.MemberDuplicationException;
import io.namoosori.travelclub.phase7.spec.util.exception.NoSuchClubException;
import io.namoosori.travelclub.phase7.spec.util.exception.NoSuchMemberException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceLogic implements MemberService {
	//
	private MemberStore memberStore;

	public MemberServiceLogic(MemberStore memberStore) {
		//
		this.memberStore = memberStore;
	}

	@Override
	public String registerMember(MemberCdo newMemberCdo) {
		//
		String email = newMemberCdo.getEmail();
		Optional.ofNullable(memberStore.retrieveByEmail(email))
				.ifPresent(member->{throw new MemberDuplicationException("It is already exist the member email: " + member.getEmail());});

		CommunityMember communityMember = new CommunityMember(newMemberCdo.getEmail(), newMemberCdo.getName(),newMemberCdo.getPhoneNumber());
		communityMember.setNickName(newMemberCdo.getNickName());
		communityMember.setBirthDay(newMemberCdo.getBirthDay());

		memberStore.create(communityMember);

		return communityMember.getId();
	}

	@Override
	public CommunityMember findMemberById(String memberId) {
		//
		return Optional.ofNullable(memberStore.retrieve(memberId))
				.orElseThrow(()->new NoSuchMemberException("No such a member with id: " + memberId));
	}

	@Override
	public CommunityMember findMemberByEmail(String memberEmail) {
		//
		return Optional.ofNullable(memberStore.retrieveByEmail(memberEmail))
				.orElseThrow(()->new NoSuchMemberException("No such a member with email: " + memberEmail));
	}

	@Override
	public List<CommunityMember> findMembersByName(String name, boolean descending) {
		//
		return Optional.ofNullable(memberStore.retrieveByName(name, descending))
				.orElseThrow(() -> new NoSuchClubException("No members"));
	}

	@Override
	public void modifyMember(String memberEmail, NameValueList nameValueList) throws InvalidEmailException {
		//
		CommunityMember targetMember = memberStore.retrieveByEmail(memberEmail);
		targetMember.modifyValues(nameValueList);

		memberStore.update(targetMember);
	}


	@Override
	public void removeMember(String memberId) {
		//
		if (!memberStore.exists(memberId)) {
			throw new NoSuchMemberException("No such a member with id: " + memberId);
		}

		memberStore.delete(memberId);
	}

}
