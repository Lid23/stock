import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.noodles.collectdata.CollectDataApplication;
import com.noodles.collectdata.dao.CityDao;
import com.noodles.collectdata.vo.City;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program: collectdata
 * @description: mybatis mysql 测试
 * @author: Eric
 * @create: 2019-01-07 17:24
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CollectDataApplication.class)
public class CityTest {

    private static final Logger log = LoggerFactory.getLogger(CityTest.class);

    @Autowired
    private CityDao cityDao;

    @Test
    public void testCity(){
        City city = new City();
        //city.setId(1L);
        city.setCityName("深圳");
        city.setDescription("宇宙中心");
        int rows = cityDao.insert(city);
        System.out.println("rows " + rows);

        System.out.println(cityDao.selectAll());
    }

    @Test
    public void testPagehelp(){
        // 分页 + 排序 this.cityDao.selectAll() 这一句就是我们需要写的查询，有了这两款插件无缝切换各种数据库
        final PageInfo<City> pageInfo = PageHelper.startPage(1, 10).setOrderBy("id desc").
                doSelectPageInfo(() -> this.cityDao.selectAll());
        log.info("[lambda写法] - [分页信息] - [{}]", pageInfo.toString());

        PageHelper.startPage(2, 10).setOrderBy("id desc");
        final PageInfo<City> cityPageInfo = new PageInfo<>(this.cityDao.selectAll());
        log.info("[普通写法] - [{}]", cityPageInfo);
    }

}
