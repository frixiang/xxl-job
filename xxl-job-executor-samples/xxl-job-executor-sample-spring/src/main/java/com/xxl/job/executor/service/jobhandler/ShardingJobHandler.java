package com.xxl.job.executor.service.jobhandler;

import com.google.common.collect.Maps;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import com.xxl.job.core.util.ShardingUtil;
import com.xxl.job.model.TmsOrderInfo;
import com.xxl.job.services.TmsOrderService;
import com.xxl.job.vo.TmsOrderInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 分片广播任务
 *
 * @author xuxueli 2017-07-25 20:56:50
 */
@JobHandler(value="shardingJobHandler")
@Service
public class ShardingJobHandler extends IJobHandler {

	@Autowired
	private TmsOrderService tmsOrderService;

	private ExecutorService THREAD_LOCAL_POOL = Executors.newFixedThreadPool(5);

	@Override
	public ReturnT<String> execute(String param) throws Exception {

		// 分片参数
		ShardingUtil.ShardingVO shardingVO = ShardingUtil.getShardingVo();
		XxlJobLogger.log("分片参数：当前分片序号 = {0}, 总分片数 = {1}", shardingVO.getIndex(), shardingVO.getTotal());

		Map<String,Object> params = Maps.newHashMap();
		List<TmsOrderInfoVO> delList = tmsOrderService.queryByPage(params,shardingVO.getIndex() + 1,1000);
		if(delList != null || !delList.isEmpty()){
			XxlJobLogger.log("待处理数据--->" + delList.size());

			int count = delList.size() % 100 > 0 ? delList.size() / 100 + 1: delList.size() / 100;
			int index = 0;
			for(;count > 0;){
				index = index > delList.size() ? delList.size() : index;
				final List<TmsOrderInfoVO> subList = delList.subList(index,index + 100 > delList.size() ? delList.size() : index + 100);
				index += 100;
				count --;
				THREAD_LOCAL_POOL.submit(new Runnable() {
					@Override
					public void run() {
						dealData(subList);
					}
				});
			}

		}

		return SUCCESS;
	}

	private void dealData(List<TmsOrderInfoVO> list){
		int first = 0;
		if(first == 0) {
			TmsOrderInfoVO order = list.get(0);
			order.setIsSegmentedInteract(1);
			order.setSegmentedInteractTime(Long.valueOf(System.currentTimeMillis()/1000).intValue());
			order.setSegmentedShippingNo("124");
			tmsOrderService.updateSegmentedInteract(order);
		}
		XxlJobLogger.log(Thread.currentThread().getName() + "-----" + "跑任务啦,待处理数据长度：{}",list.size());
	}
	
}
