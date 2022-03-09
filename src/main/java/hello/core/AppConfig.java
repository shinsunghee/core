package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    /***
     * 각 서비스에 대한 환경설정은 이 클래스에서 전부다 지정한다.
     * 생성자를 통해서 생성자주입을 사용한다. injection
     */
    @Bean
    public MemoryMemberRepository getMemberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy disocuntPolicy(){
        //return new FixDiscountPolicy(); //다른 할인을 정책적용하려면 여기만바꾸면 다 적용된다.
        return new RateDiscountPolicy();
    }

    //MemoryMemberRepository 말고 다른 db를 사용하게 되면 MemberRepository 구현체를 구현한 다른 db 클래스를 주입시켜주면 전체 프로젝트에 자동으로 먹힌다.
    @Bean
    public MemberService memberService(){
        return new MemberServiceImpl(getMemberRepository());
    }


    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(getMemberRepository(),disocuntPolicy());
    }

}
