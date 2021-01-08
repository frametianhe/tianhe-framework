package com.tianhe.framework.commons.online.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 反射工具类
 * @author:weifeng.jiang
 * @DATE:2016年11月28日 @TIME: 上午10:52:50
 */
public abstract class ReflectUtil {
	
	private static Logger logger = LoggerFactory.getLogger(ReflectUtil.class);

	/**
	 * 反射获取子类以及父类的所有属性名和属性值
	 * @param paramObject
	 * @return
	 * @author: weifeng.jiang
	 * @DATE:2016年11月28日 @TIME: 下午12:51:05
	 */
	public static Map<String, Object> getCurrAndParentMap(Object paramObject){
		Map<String, Object> paramsMap = new HashMap<String, Object>();
			Class<? extends Object> paramClass = paramObject.getClass();
			Field[] fields = paramClass.getDeclaredFields();
			for (Field field : fields) {
				try {
					field.setAccessible(true);
					if(field.get(paramObject) instanceof Date){
						paramsMap.put(field.getName(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(field.get(paramObject)));
					}else{
						paramsMap.put(field.getName(),field.get(paramObject));
					}
					Class<?> paramsSuperclass = paramClass.getSuperclass();
					Field[] declaredFields = paramsSuperclass.getDeclaredFields();
					for (Field superField : declaredFields) {
						superField.setAccessible(true);
						if(superField.get(paramObject) instanceof Date){
							paramsMap.put(superField.getName(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(superField.get(paramObject)));
						}else{
							paramsMap.put(superField.getName(), superField.get(paramObject));
						}
							
					}
				} catch (Exception e) {
					logger.info("反射获取对象以及父类对象参数失败",e);
				} 
			}
			return paramsMap;
	}
}
