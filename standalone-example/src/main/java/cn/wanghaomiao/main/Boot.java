package cn.wanghaomiao.main;

import cn.wanghaomiao.seimi.config.SeimiConfig;
import cn.wanghaomiao.seimi.core.Seimi;

/**
 * @author github.com/zhegexiaohuozi seimimaster@gmail.com
 * @since 2015/10/21.
 */
public class Boot {
    public static void main(String[] args){
        SeimiConfig config = new SeimiConfig();
        config.setSeimiAgentHost("112.74.45.121");
        config.setSeimiAgentPort(39004);

//        config.redisSingleServer().setAddress("redis://127.0.0.1:6379");
        Seimi s = new Seimi(config);
//        s.goRun("basic");
  //      s.goRun("BeanResolver");
          s.goRun("seimiagent");
    }
}
