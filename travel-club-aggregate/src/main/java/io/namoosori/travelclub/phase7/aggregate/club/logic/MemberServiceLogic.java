package io.namoosori.travelclub.phase7.aggregate.club.logic;


import io.namoosori.travelclub.phase7.aggregate.club.service.MemberService;
import io.namoosori.travelclub.phase7.aggregate.club.store.MemberStore;
import io.namoosori.travelclub.phase7.spec.aggregate.club.CommunityMember;
import io.namoosori.travelclub.phase7.spec.facade.aggregate.club.sdo.MemberDto;
import io.namoosori.travelclub.phase7.spec.util.exception.InvalidEmailException;
import io.namoosori.travelclub.phase7.spec.util.exception.MemberDuplicationException;
import io.namoosori.travelclub.phase7.spec.util.exception.NoSuchMemberException;
import io.namoosori.travelclub.phase7.spec.util.helper.StringUtil;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MemberServiceLogic implements MemberService {
	//
	private MemberStore memberStore;

	public MemberServiceLogic() {
		// TODO: Spring DI
		// memberStore = ClubStoreMapLycler.getInstance().requestMemberStore();
	}

	@Override
	public void register(MemberDto newMemberDto) {
		//
		String email = newMemberDto.getEmail();
		Optional.ofNullable(memberStore.retrieve(email))
				.ifPresent(member->{throw new MemberDuplicationException("It is already exist the member email: " + member.getEmail());});
		memberStore.create(newMemberDto.toMember());
	}

	@Override
	public MemberDto find(String memberEmail) {
		//
		return Optional.ofNullable(memberStore.retrieve(memberEmail))
				.map(member->new MemberDto(member))
				.orElseThrow(()->new NoSuchMemberException("No such a member with email: " + memberEmail));
	}

	@Override
	public List<MemberDto> findByName(String memberName) {
		//
		List<CommunityMember> members = memberStore.retrieveByName(memberName);
		if(members.isEmpty()) {
			throw new NoSuchMemberException("No such a member with name: "+memberName);
		}

		return members.stream()
				.map(member->new MemberDto(member))
				.collect(Collectors.toList());
	}

	@Override
	public void modify(MemberDto memberDto) throws InvalidEmailException {
		//
		CommunityMember targetMember = Optional.ofNullable(memberStore.retrieve(memberDto.getEmail()))
				.orElseThrow(()->new NoSuchMemberException("No such a member with email: " + memberDto.getEmail()));
		
		// modify if only user inputs some value.
		if (StringUtil.isEmpty(memberDto.getName())) {
			memberDto.setName(targetMember.getName());
		}
		if (StringUtil.isEmpty(memberDto.getNickName())) {
			memberDto.setNickName(targetMember.getNickName());
		}
		if (StringUtil.isEmpty(memberDto.getPhoneNumber())) {
			memberDto.setPhoneNumber(targetMember.getPhoneNumber());
		}
		if (StringUtil.isEmpty(memberDto.getBirthDay())) {
			memberDto.setBirthDay(targetMember.getBirthDay());
		}
		
		memberStore.update(memberDto.toMember());
	}


	@Override
	public void remove(String memberId) {
		//
		if (!memberStore.exists(memberId)) {
			throw new NoSuchMemberException("No such a member with email: " + memberId);
		}

		memberStore.delete(memberId);
	}

}
