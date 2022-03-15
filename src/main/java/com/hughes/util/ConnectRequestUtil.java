package com.hughes.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * 通讯工具类
 * @author hughes-T
 * @since 2021/10/12 9:28
 */

@Component
@Slf4j
public class ConnectRequestUtil {

	public enum Method{
		GET,POST;
	}

	private static RestTemplate restTemplate ;

//	@Resource
	public static void setRestTemplate(RestTemplate restTemplate) {
		ConnectRequestUtil.restTemplate = restTemplate;
	}

	/**
	 * 请求消息
	 * @author hughes-T 2021/10/12 9:42
	 *
	 * @param url 全路径
	 * @param request 请求头
	 * @param method 请求方式
	 * @param responseType 响应体类型
	 * @exception Exception 失败返回
	 *
	 */
	public static <T> T sendMsg(String url, Object request, Method method , Class<T> responseType) throws Exception{
		log.debug("请求讯息, url:{},request:{},method:{},responseType:{}",url,request,method,responseType.getSimpleName());
		ResponseEntity<T> responseEntity;
		switch (method) {
			case GET:
				responseEntity = restTemplate.getForEntity(url, responseType);
				break;
			case POST:
				responseEntity = restTemplate.postForEntity(url, request, responseType);
				break;
			default:
				throw new Exception("未知的请求方式");
		}
		log.debug("请求结果, responseEntity:{}",responseEntity);
		if (responseEntity.getStatusCode() != HttpStatus.OK) {
			throw new Exception("请求消息失败,失败码:{}" + responseEntity.getStatusCode().toString());
		}
		return responseEntity.getBody();
	}


}
