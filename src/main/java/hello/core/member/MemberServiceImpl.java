package hello.core.member;

public class MemberServiceImpl implements MemberService{

    //private final MemberRepository memberRepository = new MemoryMemberRepository(); //ocp위반코드 AppConfig로 변경
    private final  MemberRepository memberRepository;

    //MemomryMemberRepository에 대한 주입내용은 여기에 아무것도없다
    //AppConfig에서 주입하기땜누에 ocp 를 지킨소스가된다.
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long MemberId) {
        return memberRepository.findById(MemberId);
    }
}
