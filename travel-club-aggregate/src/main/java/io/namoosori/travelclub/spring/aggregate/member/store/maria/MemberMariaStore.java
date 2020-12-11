package io.namoosori.travelclub.spring.aggregate.member.store.maria;

import io.namoosori.travelclub.spring.aggregate.club.store.maria.jpo.TravelClubJpo;
import io.namoosori.travelclub.spring.aggregate.member.store.MemberStore;
import io.namoosori.travelclub.spring.aggregate.member.store.maria.jpo.MemberJpo;
import io.namoosori.travelclub.spring.aggregate.member.store.maria.repository.MemberRepository;
import io.namoosori.travelclub.spring.spec.aggregate.club.CommunityMember;
import io.namoosori.travelclub.spring.spec.aggregate.club.TravelClub;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MemberMariaStore implements MemberStore {

    private MemberRepository memberRepository;

    public MemberMariaStore(MemberRepository memberRepository) {
        //
        this.memberRepository = memberRepository;
    }

    @Override
    public String create(CommunityMember member) {
        //
        MemberJpo memberJpo = memberRepository.save(new MemberJpo(member));
        memberRepository.save(memberJpo);

        return memberJpo.getId();
    }

    @Override
    public CommunityMember retrieve(String memberId) {
        //
        Optional<MemberJpo> memberJpo = memberRepository.findById(memberId);
        return memberJpo.map(MemberJpo::toDomain).orElse(null);
    }

    @Override
    public CommunityMember retrieveByEmail(String memberEmail) {
        //
        Optional<MemberJpo> member = memberRepository.findByEmail(memberEmail);
        return member.map(MemberJpo::toDomain).orElse(null);
    }

    @Override
    public List<CommunityMember> retrieveByName(String name) {
        //
        Sort sort = Sort.by("name").ascending();

        return memberRepository.findByNameContaining(name, sort).stream()
                .map(MemberJpo::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommunityMember> retrieveAll() {
        //
        List<CommunityMember> members = new ArrayList<>();
        Iterable<MemberJpo> memberJpos = memberRepository.findAll();

        memberJpos.forEach(memberJpo -> members.add(memberJpo.toDomain()));

        return members;
    }

    @Override
    public void update(CommunityMember member) {
        //
        memberRepository.save(new MemberJpo(member));
    }

    @Override
    public void delete(String memberId) {
        //
        memberRepository.deleteById(memberId);
    }

    @Override
    public boolean exists(String memberId) {
        //
        return memberRepository.existsById(memberId);
    }
}
