package io.namoosori.travelclub.spring.aggregate.membership.store.maria;

import io.namoosori.travelclub.spring.aggregate.membership.store.MembershipStore;
import io.namoosori.travelclub.spring.aggregate.membership.store.maria.jpo.MembershipJpo;
import io.namoosori.travelclub.spring.aggregate.membership.store.maria.repository.MembershipRepository;
import io.namoosori.travelclub.spring.spec.aggregate.club.Membership;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MembershipMariaStore implements MembershipStore {
    //
    private MembershipRepository membershipRepository;

    public MembershipMariaStore(MembershipRepository membershipRepository) {
        //
        this.membershipRepository = membershipRepository;
    }

    @Override
    public String create(Membership membership) {
        //
        MembershipJpo membershipJpo = membershipRepository.save(new MembershipJpo(membership));
        return membershipJpo.getId();
    }

    @Override
    public Membership retrieve(String membershipId) {
        //
        Optional<MembershipJpo> membershipJpo = membershipRepository.findById(membershipId);
        return membershipJpo.map(MembershipJpo::toDomain).orElse(null);
    }

    @Override
    public Membership retrieveByClubIdAndMemberId(String clubId, String memberId) {
        //
        Optional<MembershipJpo> membershipJpo = membershipRepository.findByClubIdAndMemberId(clubId, memberId);
        return membershipJpo.map(MembershipJpo::toDomain).orElse(null);
    }

    @Override
    public List<Membership> retrieveByClubId(String clubId) {
        //
        Sort sort = Sort.by("joinDate").ascending();

        return membershipRepository.findAllByClubId(clubId, sort).stream()
                .map(MembershipJpo::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Membership> retrieveByMemberId(String memberId) {
        //
        Sort sort = Sort.by("joinDate").ascending();

        return membershipRepository.findAllByMemberId(memberId, sort).stream()
                .map(MembershipJpo::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void update(Membership membership) {
        //
        membershipRepository.save(new MembershipJpo(membership));
    }

    @Override
    public void delete(String membershipId) {
        //
        membershipRepository.deleteById(membershipId);
    }

    @Override
    public boolean exists(String membershipId) {
        //
        return membershipRepository.existsById(membershipId);
    }
}
