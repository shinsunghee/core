package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    //이러면 안된다. 정책을 어떤걸 쓸지는 OrderServiceImpl 에서는 정할필요가 없다
    //추상대상인 DiscountPolicy만 바라보기만하고 할인정책은 OrderServiceImpl은 구현할 필요가없음
    // 할인정책에 대해서 OrderServiceImple에서 정해버린 역할이 되어버린다
    //아래 코드는 ocp를 위반한것이다.
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    //이런 ocp를 지키기 위해서 AppConfig를 사용 연결하는역할을하는별도의 class 생성


    //ocp를 지키기 위해 생성자를 통해 변수에 대한 값을 할당시킨다.
    // memberRepository, discountPolicy에는 어떤 값이 들어올진 여기서 알수없다.(알 필요가없기때문) 추상화대상만알면그만
    //알고있는 추상화 대상을 구현한 서비스가 분명 어딘가는존재할것이고 그 값이 이쪽으로 넘어올 예정이기때문
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        //회원정보찾가
        Member member = memberRepository.findById(memberId);
        //회원정보로 할인정책따로찾고
        int discountPrice = discountPolicy.discount(member, itemPrice);

        //discountpolicy의 역할과 order의 역할이 명화하게 분리돼잇다.

        return new Order(memberId,itemName,itemPrice, discountPrice);
    }
}
