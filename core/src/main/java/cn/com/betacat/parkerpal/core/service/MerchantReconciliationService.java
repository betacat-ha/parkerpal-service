package cn.com.betacat.parkerpal.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.com.betacat.parkerpal.common.domain.MerchantReconciliation;
import cn.com.betacat.parkerpal.common.domain.ParkCollectCoupons;
import cn.com.betacat.parkerpal.common.domain.query.MerchantReconciliationQuery;
import cn.com.betacat.parkerpal.common.pageutil.PageInfoRespQuery;

/**
 * 商户-对账记录(MerchantReconciliation)表服务接口
 *
 * @author 
 * @since 2024-08-20 13:13:44
 */
public interface MerchantReconciliationService extends IService<MerchantReconciliation> {

    /**
     * 商户-对账记录-分页查询列表
     *
     * @param query
     * @return
     */
    PageInfoRespQuery getPageList(MerchantReconciliationQuery query);

    /**
     * 将状态更改为已发放
     *
     * @param id
     */
    void updateStatus(String id);

    /**
     * 创建或更新商家对账记录
     *
     * @param parkCollectCoupons
     */
    void createOrUpdateEntity(ParkCollectCoupons parkCollectCoupons);
}
