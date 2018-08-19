import com.zt.util.ConvertHelper;
import org.junit.Test;

public class ConvertTest {
    @Test
    public void fun(){
        String s = "星期五 5-6";
        s = ConvertHelper.concert(s);
        System.out.println(s);
    }
}
