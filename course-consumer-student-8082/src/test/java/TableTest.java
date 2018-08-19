import com.zt.StudentApplication;
import com.zt.pojo.tcourse;
import com.zt.service.CourseService;
import javafx.application.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StudentApplication.class)
public class TableTest {
    @Resource
    private CourseService courseService;

    @Test
    public void fun(){
        List<tcourse> list = courseService.getCourseTable("s2015007");
        if(list.size()==0){
            System.out.println("null...");
        }
    }
}
