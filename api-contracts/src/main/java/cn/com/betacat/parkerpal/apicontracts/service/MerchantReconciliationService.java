package cn.com.betacat.parkerpal.apicontracts.service;

import cn.com.betacat.parkerpal.domain.entity.MerchantReconciliation;
import cn.com.betacat.parkerpal.domain.entity.ParkCollectCoupons;
import cn.com.betacat.parkerpal.domain.query.MerchantReconciliationQuery;
import cn.com.betacat.parkerpal.domain.base.PageInfoRespQuery;
import com.baomidou.mybatisplus.extension.service.IService;

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
