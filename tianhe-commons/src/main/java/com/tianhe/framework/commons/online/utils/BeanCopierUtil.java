package com.tianhe.framework.commons.online.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.beans.BeanCopier;

import java.util.*;
import java.util.Map.Entry;

/**
 * bean utils
 * @author:weifeng.jiang
 * @DATE:2016年12月2日 @TIME: 上午11:04:12
 */
public class BeanCopierUtil {

	private static Logger logger = LoggerFactory.getLogger(BeanCopierUtil.class);

	/**
	 * bean copy
	 * @param inputObj
	 * @param outputType
	 * @return
	 * @author: weifeng.jiang
	 * @DATE:2016年12月2日 @TIME: 上午11:02:32
	 */
	public static <I, O> O convert(I inputObj, Class<O> outputType) {
		try {
			if(inputObj == null){
				return null;
			}
			O k = outputType.newInstance();
			
			BeanCopier copier = BeanCopier.create(inputObj.getClass(), outputType, false);
			copier.copy(inputObj, k, null);
			return k;
		} catch (Exception e) {
			logger.info(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * list copy
	 * @param inputList
	 * @param outputType
	 * @return
	 * @author: weifeng.jiang
	 * @DATE:2016年12月2日 @TIME: 上午11:03:00
	 */
	public static <I, O> List<O> convert(List<I> inputList, Class<O> outputType) {
		if(inputList == null){
			return null;
		}
		List<O> outputList = new ArrayList<>(inputList.size());
		for(I i: inputList){
			O convert = convert(i, outputType);
			outputList.add(convert);
		}
		return outputList;
	}
	
	/**
	 * map copy
	 * @param inputMap
	 * @param outputType
	 * @return
	 * @author: weifeng.jiang
	 * @DATE:2016年12月2日 @TIME: 上午11:03:29
	 */
	public static <I, O> Map<String, List<O>> convert(Map<String, List<I>> inputMap,  Class<O> outputType) {
		if(inputMap == null){
			return null;
		}
		Map<String, List<O>> map = new HashMap<String, List<O>>();
		Set<Entry<String, List<I>>> entrySet = inputMap.entrySet();
		for(Entry<String, List<I>> entry: entrySet){
			map.put(entry.getKey(), convert(entry.getValue(), outputType));
		}
		return map;
	}
	
	
}
