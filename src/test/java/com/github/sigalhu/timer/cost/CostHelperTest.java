package com.github.sigalhu.timer.cost;

import com.github.sigalhu.utils.Try;
import org.junit.Test;

/**
 * @author huxujun
 * @date 2019-08-15
 */
public class CostHelperTest {

    @Test
    public void test() {
        CostHelper helper = new CostHelper(true);

        CostTimer timer1 = helper.createCostTimer("timer1");
        timer1.cost(Try.of(() -> {
            Thread.sleep(500);
            CostTimer timer11 = timer1.childCostTimer("time11");
            for (int i = 0; i < 10; i++) {
                timer11.start();
                Thread.sleep(10);
                timer11.end();
            }
        }));
        try (CostTimer timer12 = timer1.childCostTimer("timer12")) {
            Thread.sleep(300);
        } catch (Exception e) {

        }
        System.out.println(helper.getDetails());
    }
}