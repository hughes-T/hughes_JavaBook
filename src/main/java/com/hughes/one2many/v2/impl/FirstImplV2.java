package com.hughes.one2many.v2.impl;

import com.alibaba.fastjson.JSONObject;
import com.hughes.one2many.v2.IEnumOne2ManyV2;
import com.hughes.one2many.v2.pojo.FirstReq;
import com.hughes.one2many.v2.pojo.FirstResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/*
 * @Description
 * @Author hughesT
 * @Date 2022/6/6 17:01
 */
@Service
@Slf4j
public class FirstImplV2 implements IEnumOne2ManyV2<FirstReq, FirstResult> {

    @Override
    public Type getType() {
        return Type.FIRST;
    }

    @Override
    public int save(JSONObject reqParam) {
        FirstReq firstReq = convertParam(reqParam);
        log.info("业务处理请求参firstReq:{}",firstReq.getFirstReqName());
        return 1;
    }

    @Override
    public FirstResult querySelective(JSONObject po) {
        FirstResult queryParam = convertResult(po);
        log.info("业务查询参queryParam:{}",queryParam.getFirstPoName());
        return null;
    }


}
