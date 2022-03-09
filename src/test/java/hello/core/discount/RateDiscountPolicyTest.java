package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {
    RateDiscountPolicy rateDiscountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("vip 는 10% 할인이 적용되어야한다.")
    void vip() {

            //given
        Member vip = new Member(1l, "memberVip", Grade.VIP);
        //when
        int discount = rateDiscountPolicy.discount(vip, 10000);
        //then
        assertThat(discount).isEqualTo(1000);

    }
    @Test
    @DisplayName("vip가 아니면  할인이 미적용되어야한다.")
    void vip2() {

        //given
        Member vip = new Member(2l, "memberVip", Grade.BASIC);
        //when
        int discount = rateDiscountPolicy.discount(vip, 10000);
        //then
        assertThat(discount).isEqualTo(1000);

    }
}