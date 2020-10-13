package com.success.rabbitmq;

import com.success.bean.SysDict;
import com.success.redis.RedisService;
import com.success.service.SysDictService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


//@Service
public class MQReceiver {

		@Autowired
		private SysDictService sysDictService;
		@Autowired
		private RedisService redisService;
		@Resource
		private RedisTemplate<String, String> redisTemplate;
		/**
		 * Redis脚本
		 */
		@Resource(name = "updateDictScript")
		private RedisScript<Boolean> updateDictScript;


		private static Logger log = LoggerFactory.getLogger(MQReceiver.class);

		@RabbitListener(queues=MQConfig.DICT_QUEUE)
		public void receiveDictTopic(String category) {
			log.info(" topic  dict topic message:"+category);

			List<String> fieldValueList = new ArrayList<>();
			List<SysDict> sysDicts = sysDictService.selectByCategory(category);
			if(sysDicts != null && sysDicts.size()>0){
				for(SysDict sysDict:sysDicts){
					fieldValueList.add(sysDict.getName());
					fieldValueList.add(sysDict.getValue());
				}
			}
			Boolean result = redisTemplate.execute(updateDictScript, Arrays.asList(category), fieldValueList.toArray(new String[0]));

			log.info("redis Reload the dict named {} success, the items is {},execute result is {}", category, sysDicts.size(),result);
		}

		@PostConstruct
		public void loadAllDicts(){
			List<SysDict> sysDicts = sysDictService.selectAll();
			long start = System.currentTimeMillis();
			Map<String, List<SysDict>> groupByCategory = sysDicts.stream().collect(Collectors.groupingBy(SysDict::getCategory));
			redisService.del(groupByCategory.keySet().toArray(new String[0]));

			for (Map.Entry<String, List<SysDict>> entrySysDict : groupByCategory.entrySet()) {
				String category = entrySysDict.getKey();

				List<SysDict> entrySysDictList = entrySysDict.getValue();
				Map<String,String> dictCategoryMap = entrySysDictList.stream().collect(Collectors.toMap(SysDict::getName,SysDict::getValue));
				redisService.putHashValues(category,dictCategoryMap);
			}
			log.info("redis Load all dict success, the items is {} that cost time {}ms.", groupByCategory.size(), (System.currentTimeMillis() - start));

		}

	
//		@RabbitListener(queues=MQConfig.QUEUE)
//		public void receive(String message) {
//			log.info("receive message:"+message);
//		}
//		
//		@RabbitListener(queues=MQConfig.TOPIC_QUEUE1)
//		public void receiveTopic1(String message) {
//			log.info(" topic  queue1 message:"+message);
//		}
//		
//		@RabbitListener(queues=MQConfig.TOPIC_QUEUE2)
//		public void receiveTopic2(String message) {
//			log.info(" topic  queue2 message:"+message);
//		}
//		
//		@RabbitListener(queues=MQConfig.HEADER_QUEUE)
//		public void receiveHeaderQueue(byte[] message) {
//			log.info(" header  queue message:"+new String(message));
//		}
//		
		
}
