package gd.fintech.lms;

import org.springframework.context.ApplicationContext;

// ApplicationContext의 getBean 메서드를 통해서 빈을 받아오는 클래스

public class BeanUtils {
	 public static Object getBean(String beanName) {
        ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();
        return applicationContext.getBean(beanName);
	 }
}
