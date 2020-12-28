package gd.fintech.lms;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

// ApplicationContext를 관리하고 제공해주는 클래스
// 빈이 아닌 클래스에서 빈 객체를 주입받기 위해 생성

@Component
public class ApplicationContextProvider implements ApplicationContextAware{
	
	private static ApplicationContext applicationContext;
	 
    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        applicationContext = ctx;
    }
 
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
