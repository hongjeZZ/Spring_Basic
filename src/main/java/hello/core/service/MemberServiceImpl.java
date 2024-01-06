package hello.core.service;

import hello.core.member.Member;
import hello.core.repository.MemberRepository;
import hello.core.repository.MemoryMemberRepository;

public class MemberServiceImpl implements MemberService {

    // 현재 구현체를 의존하고 있으므로, DIP 원칙을 위반하고 있다.
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findById(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
