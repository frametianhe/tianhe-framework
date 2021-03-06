package com.tianhe.framework.guava.online.cache;

import com.google.common.cache.CacheStats;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Guava缓存监视和管理工具
 *
 */
public class GuavaCacheManager {
	//保存一个Map: cacheName -> cache Object，以便根据cacheName获取Guava cache对象
	private static Map<String, ? extends GuavaAbstractLoadingCache<Object, Object>> cacheNameToObjectMap = null;

	/**
	 * 获取所有GuavaAbstractLoadingCache子类的实例，即所有的Guava Cache对象
	 * @return
	 */

	@SuppressWarnings("unchecked")
	private static Map<String, ? extends GuavaAbstractLoadingCache<Object, Object>> getCacheMap(){
		if(cacheNameToObjectMap==null){
			cacheNameToObjectMap = (Map<String, ? extends GuavaAbstractLoadingCache<Object, Object>>) SpringContextUtil.getBeanOfType(GuavaAbstractLoadingCache.class);
		}
		return cacheNameToObjectMap;

	}

	/**
	 *	根据cacheName获取cache对象
	 * @param cacheName
	 * @return
	 */
	private static GuavaAbstractLoadingCache<Object, Object> getCacheByName(String cacheName){
		return (GuavaAbstractLoadingCache<Object, Object>) getCacheMap().get(cacheName);
	}

	/**
	 * 获取所有缓存的名字（即缓存实现类的名称）
	 * @return
	 */
	public static Set<String> getCacheNames() {
		return getCacheMap().keySet();
	}

	/**
	 * 返回所有缓存的统计数据
	 * @return List<Map<统计指标，统计数据>>
	 */
	public static ArrayList<Map<String, Object>> getAllCacheStats() {

		Map<String, ? extends Object> cacheMap = getCacheMap();
		List<String> cacheNameList = new ArrayList<>(cacheMap.keySet());
		Collections.sort(cacheNameList);//按照字母排序

		//遍历所有缓存，获取统计数据
		ArrayList<Map<String, Object>> list = new ArrayList<>();
		for(String cacheName : cacheNameList){
			list.add(getCacheStatsToMap(cacheName));
		}

		return list;
	}

	/**
	 * 返回一个缓存的统计数据
	 * @param cacheName
	 * @return Map<统计指标，统计数据>
	 */
	private static Map<String, Object> getCacheStatsToMap(String cacheName) {
		Map<String, Object> map =  new LinkedHashMap<>();
		GuavaAbstractLoadingCache<Object, Object> cache = getCacheByName(cacheName);
		CacheStats cs = cache.getCache().stats();
		NumberFormat percent = NumberFormat.getPercentInstance(); // 建立百分比格式化用
		percent.setMaximumFractionDigits(1); // 百分比小数点后的位数
		map.put("cacheName", cacheName);
		map.put("size", cache.getCache().size());
		map.put("maximumSize", cache.getMaximumSize());
		map.put("survivalDuration", cache.getExpireAfterWriteDuration());
		map.put("hitCount", cs.hitCount());
		map.put("hitRate", percent.format(cs.hitRate()));
		map.put("missRate", percent.format(cs.missRate()));
		map.put("loadSuccessCount", cs.loadSuccessCount());
		map.put("loadExceptionCount", cs.loadExceptionCount());
		map.put("totalLoadTime", cs.totalLoadTime()/1000000); 		//ms
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(cache.getResetTime()!=null){
			map.put("resetTime", df.format(cache.getResetTime()));
		}
		map.put("highestSize", cache.getHighestSize());
		if(cache.getHighestTime()!=null){
			map.put("highestTime", df.format(cache.getHighestTime()));
		}

		return map;
	}

	/**
	 * 根据cacheName清空缓存数据
	 * @param cacheName
	 */
	public static void resetCache(String cacheName){
		GuavaAbstractLoadingCache<Object, Object> cache = getCacheByName(cacheName);
		cache.getCache().invalidateAll();
		cache.setResetTime(new Date());
	}
}
