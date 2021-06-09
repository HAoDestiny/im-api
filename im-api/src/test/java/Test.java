import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

/**
 * @author destiny
 * @date 2021/6/4 13:02
 */
public class Test {

    public static void main(String[] args) {
        DateTime dateTime = DateUtil.offsetDay(DateUtil.date(), -30);

        System.out.println(DateUtil.formatDateTime(dateTime));
        System.out.println(dateTime.getTime());
    }
}
